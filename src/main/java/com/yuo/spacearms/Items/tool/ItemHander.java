package com.yuo.spacearms.Items.tool;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.List;

/**
 * 范围挖掘工具类
 */
public class ItemHander 
{

    public ItemHander() {
    }

    /**
     * 根据玩家朝向来破坏方块
     * @param stack 工具
     * @param world 世界
     * @param state 方块
     * @param pos 方块坐标
     * @param player 玩家
     * @param lv 范围等级
     * @param type 类型
     */
	public void onBlockStartBreak(ItemStack stack, World world, BlockState state, BlockPos pos,
			PlayerEntity player, Integer lv, ToolType type) {
        int harvestLevel = stack.getHarvestLevel(type, player, state);
        int level = state.getHarvestLevel();
        if (harvestLevel < level ){
            return; //工具无法挖掘此块
        }
        Vector3d vec = player.getLookVec();
        Direction facing = Direction.getFacingFromVector(vec.x, vec.y, vec.z);
        switch (facing){
            case UP:
                for (int x = pos.getX() - lv; x <= pos.getX() + lv; x ++){
                    for (int y = pos.getY(); y <= pos.getY() + (2 * lv); y ++){
                        for (int z = pos.getZ() - lv; z <= pos.getZ() + lv; z ++){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
            case DOWN:
                for (int x = pos.getX() - lv; x <= pos.getX() + lv; x ++){
                    for (int y = pos.getY(); y >= pos.getY() - (2 * lv); y --){
                        for (int z = pos.getZ() - lv; z <= pos.getZ() + lv; z ++){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
            case EAST:
                for (int x = pos.getX(); x <= pos.getX() + (2 * lv); x ++){
                    for (int y = pos.getY() - lv; y <= pos.getY() + lv; y ++){
                        for (int z = pos.getZ() - lv; z <= pos.getZ() + lv; z ++){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
            case WEST:
                for (int x = pos.getX(); x >= pos.getX() - (2 * lv); x --){
                    for (int y = pos.getY() - lv; y <= pos.getY() + lv; y ++){
                        for (int z = pos.getZ() - lv; z <= pos.getZ() + lv; z ++){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
            case NORTH:
                for (int x = pos.getX() - lv; x <= pos.getX() + lv; x ++){
                    for (int y = pos.getY() - lv; y <= pos.getY() + lv; y ++){
                        for (int z = pos.getZ(); z >= pos.getZ() - (2 * lv); z --){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
            case SOUTH:
                for (int x = pos.getX() - lv; x <= pos.getX() + lv; x ++){
                    for (int y = pos.getY() - lv; y <= pos.getY() + lv; y ++){
                        for (int z = pos.getZ(); z <= pos.getZ() + (2 * lv); z ++){
                            destroyBlocks(x, y, z, world, state, stack, player);
                        }
                    }
                }
                break;
        }
    }

    /**
     * 破坏方块
     * @param x 要破坏的方块坐标
     * @param y 坐标
     * @param z 坐标
     * @param world 世界
     * @param state 基准方块状态
     * @param stack 工具
     */
    private void destroyBlocks(int x, int y, int z, World world, BlockState state, ItemStack stack, PlayerEntity player){
        BlockPos poslv = new BlockPos(x, y, z);
        //排除空气方块和非同类型方块
        if (world.isAirBlock(poslv) || !world.getBlockState(poslv).equals(state)){
            return;
        }
        //消耗工具耐久
        stack.damageItem(1, player, (e) -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        if (stack.getCount() > 0){
            world.destroyBlock(poslv, true, player); //破坏方块，并且掉落
        }
    }

    /**
     * 工具模式切换
     * @param worldIn 世界
     * @param playerIn 玩家
     * @param handIn 活动手
     */
    public static ActionResult<ItemStack> changeMode(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && playerIn.isSneaking()){
            CompoundNBT tag = stack.getOrCreateTag();
//            if (tag.isEmpty() || !tag.contains("mode")){
//                tag.putBoolean("mode", false); //添加tag
//            }
            tag.putBoolean("mode", !tag.getBoolean("mode")); //切换
            playerIn.swingArm(handIn); //摆臂
            return ActionResult.resultSuccess(stack);
        }
        return ActionResult.resultPass(stack);
    }

    /**
     * 物品描述
     * @param stack 物品
     * @param tooltip 描述
     */
    public static void addInfo(ItemStack stack, List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.aoeBlock"));
        tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_tool"));
        if (stack.hasTag() && stack.getOrCreateTag().contains("mode")){
            if (stack.getOrCreateTag().getBoolean("mode"))
                tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.aoe"));
            else tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.unAoe"));
        }
    }

    /**
     * 范围挖掘调用
     * @param itemstack 工具
     * @param player 玩家
     * @param pos 坐标
     * @param hander 挖掘类
     * @param lv 范围挖掘等级 1:3*3；2:5*5 。。。。
     * @return 是否成功
     */
    public static boolean toolBreakBlock(ItemStack itemstack, PlayerEntity player, BlockPos pos, ItemHander hander, int lv, ToolType type){
        CompoundNBT tag = itemstack.getTag();
        if (tag == null) return false;
        boolean mode = tag.getBoolean("mode");
        if (mode){
            BlockState state = player.world.getBlockState(pos);
            if (!itemstack.canHarvestBlock(state)) return false;
            hander.onBlockStartBreak(itemstack, player.world, state, pos, player, lv, type);
            return true;
        }
        return false;
    }


}
