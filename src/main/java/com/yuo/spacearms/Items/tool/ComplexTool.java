package com.yuo.spacearms.Items.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;

public class ComplexTool extends DiggerItem {
    private final TagKey<Block> blocks = BlockTags.MINEABLE_WITH_PICKAXE;
    private final Tier itemTier;

    public ComplexTool(Tier tier) {
        super( 3, -2.6f, tier, BlockTags.MINEABLE_WITH_PICKAXE, new Properties().durability(tier.getUses()));
        this.itemTier = tier;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return true;//ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.getBlock().defaultDestroyTime() >= 0 ? this.itemTier.getSpeed() : super.getDestroySpeed(stack, state);
    }

    @Deprecated
    public boolean isCorrectToolForDrops(BlockState state) {
        if (TierSortingRegistry.isTierSorted(this.getTier())) {
            return TierSortingRegistry.isCorrectTierForDrops(this.getTier(), state) && state.is(this.blocks);
        } else {
            int i = this.getTier().getLevel();
            if (i < 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                return false;
            } else if (i < 2 && state.is(BlockTags.NEEDS_IRON_TOOL)) {
                return false;
            } else {
                return (i >= 1 || !state.is(BlockTags.NEEDS_STONE_TOOL)) && state.is(this.blocks);
            }
        }
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
        return state.is(this.blocks) && TierSortingRegistry.isCorrectTierForDrops(this.getTier(), state);
    }
}
