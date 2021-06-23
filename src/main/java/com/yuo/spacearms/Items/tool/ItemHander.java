package com.yuo.spacearms.Items.tool;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

/**
 * 范围挖掘工具类
 */
public class ItemHander 
{
	private ToolItem tool;

    public ItemHander(ToolItem tool) {
        this.tool = tool;
    }
	
	public void onBlockStartBreak(ItemStack stack, World world, BlockState state, BlockPos pos,
			PlayerEntity player, Integer lv, ToolType type) {
        int harvestLevel = stack.getHarvestLevel(type, player, state);
        int level = state.getHarvestLevel();
        if (harvestLevel < level){
            return; //工具无法挖掘此块
        }
        Vec3d vec = player.getLookVec();
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
     * @param y
     * @param z
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
        stack.damageItem(1, player, (e) ->{
            e.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        if (stack.getCount() > 0){
            world.destroyBlock(poslv, true, player); //破坏方块，并且掉落
        }
    }

    /**
     * 工具模式切换
     * @param worldIn
     * @param playerIn
     * @param handIn
     */
    public static void changeMode(World worldIn, PlayerEntity playerIn, Hand handIn){
        if (!worldIn.isRemote){
            ItemStack stack = playerIn.getHeldItem(handIn);
            CompoundNBT tag = stack.getTag();
            boolean mode;
            if (tag == null){
                mode = false;
            }else mode = tag.getBoolean("mode");
            if (playerIn.isSneaking()){
                if (!mode){
                    CompoundNBT nbt = new CompoundNBT();
                    nbt.putBoolean("mode", true);
                    stack.setTag(nbt);
                    playerIn.sendMessage(new TranslationTextComponent("spacearms.text.info.aoeBlock"));
                }else {
                    CompoundNBT nbt = new CompoundNBT();
                    nbt.putBoolean("mode", false);
                    stack.setTag(nbt);
                    playerIn.sendMessage(new TranslationTextComponent("spacearms.text.info.unAoeBlock"));
                }

            }
        }
    }

    /**
     * 范围挖掘调用
     * @param itemstack
     * @param player
     * @param pos
     * @param hander
     * @param lv 范围挖掘等级 1:3*3；2:5*5 。。。。
     * @return
     */
    public static boolean toolBreakBlock(ItemStack itemstack, PlayerEntity player, BlockPos pos, ItemHander hander, int lv){
        CompoundNBT tag = itemstack.getTag();
        if (tag == null) return false;
        boolean mode = tag.getBoolean("mode");
        if (mode){
            BlockState state = player.world.getBlockState(pos);
            hander.onBlockStartBreak(itemstack, player.world, state, pos, player, lv, ToolType.PICKAXE);
        }
        return false;
    }
}
