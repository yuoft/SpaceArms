package com.yuo.spacearms.Items.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

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
     */
	public void onBlockStartBreak(ItemStack stack, Level world, BlockState state, BlockPos pos,
                                  Player player, Integer lv) {
        Vec3 vec = player.getLookAngle();
        Direction facing = Direction.getNearest(vec.x, vec.y, vec.z);
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
    private void destroyBlocks(int x, int y, int z, Level world, BlockState state, ItemStack stack, Player player){
        BlockPos poslv = new BlockPos(x, y, z);
        //排除空气方块和非同类型方块
        if (world.getBlockState(poslv).isAir() || !world.getBlockState(poslv).equals(state)){
            return;
        }
        //消耗工具耐久
        stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
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
    public static InteractionResultHolder<ItemStack> changeMode(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide && playerIn.isCrouching()){
            CompoundTag tag = stack.getOrCreateTag();
            tag.putBoolean("mode", !tag.getBoolean("mode")); //切换
            playerIn.swing(handIn); //摆臂
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    /**
     * 物品描述
     * @param stack 物品
     * @param tooltip 描述
     */
    public static void addInfo(ItemStack stack, List<Component> tooltip) {
        tooltip.add(Component.translatable("spacearms.text.itemInfo.aoeBlock"));
        tooltip.add(Component.translatable("spacearms.text.itemInfo.space_tool"));
        if (stack.hasTag() && stack.getOrCreateTag().contains("mode")){
            if (stack.getOrCreateTag().getBoolean("mode"))
                tooltip.add(Component.translatable("spacearms.text.itemInfo.aoe"));
            else tooltip.add(Component.translatable("spacearms.text.itemInfo.unAoe"));
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
    public static boolean toolBreakBlock(ItemStack itemstack, Player player, BlockPos pos, ItemHander hander, int lv){
        CompoundTag tag = itemstack.getTag();
        if (tag == null) return false;
        boolean mode = tag.getBoolean("mode");
        if (mode){
            BlockState state = player.level().getBlockState(pos);
            if (!itemstack.isCorrectToolForDrops(state)) return false;
            hander.onBlockStartBreak(itemstack, player.level(), state, pos, player, lv);
            return true;
        }
        return false;
    }


}
