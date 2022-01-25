package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ComplexTool extends ToolItem {

    private static final Set<Block> blockSet = new HashSet<>();
    private static final Set<ToolType> toolTypes = new HashSet<>(Arrays.asList(ToolType.PICKAXE, ToolType.AXE, ToolType.SHOVEL, ToolType.HOE));
    static {
        blockSet.addAll(PickaxeItem.BLOCK_TO_ITEM.keySet());
        blockSet.addAll( ShovelItem.BLOCK_TO_ITEM.keySet());
        blockSet.addAll( AxeItem.BLOCK_TO_ITEM.keySet());
        blockSet.addAll( HoeItem.BLOCK_TO_ITEM.keySet());
    }
    private final IItemTier itemTier;

    public ComplexTool(IItemTier tier) {
        super(tier.getAttackDamage(), -2.4f, tier, blockSet, new Properties().group(ModGroup.myGroup).maxDamage(tier.getMaxUses()));
        this.itemTier = tier;
    }

    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return toolTypes;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        return itemTier != null ? itemTier.getHarvestLevel() : 1;
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return super.canHarvestBlock(stack, state);
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return super.canHarvestBlock(blockIn);
    }

    @Override
    public float getAttackDamage() {
        if (this == ItemRegistry.woodComplexTool.get()){
            return 2.5f;
        }else if (this == ItemRegistry.stoneComplexTool.get()){
            return 3.5f;
        }else if (this == ItemRegistry.goldComplexTool.get()){
            return 2.5f;
        }else if (this == ItemRegistry.ironComplexTool.get()){
            return 4.5f;
        }else if (this == ItemRegistry.diamondComplexTool.get()){
            return 5.5f;
        }else if (this == ItemRegistry.emeraldComplexTool.get()){
            return 6f;
        }else if (this == ItemRegistry.rubyComplexTool.get()){
            return 5f;
        }else if (this == ItemRegistry.netheriteComplexTool.get()){
            return 6.5f;
        }else if (this == ItemRegistry.dragonComplexTool.get()){
            return 10.5f;
        }
        return super.getAttackDamage();
    }
}
