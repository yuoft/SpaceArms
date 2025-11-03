package com.yuo.spacearms.Items;

import com.yuo.spacearms.RlUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class SAItemTiers {
    public static final TagKey<Block> NEEDS_XRAY_TOOL = BlockTags.create(RlUtils.fa("needs_xray_tool"));
    public static final TagKey<Block> NEEDS_DRAGON_TOOL = BlockTags.create(RlUtils.fa("needs_dragon_tool"));
    public static final TagKey<Block> NEEDS_ULTRA_TOOL = BlockTags.create(RlUtils.fa("needs_ultra_tool"));
    public static final TagKey<Block> NEEDS_SPACE_TOOL = BlockTags.create(RlUtils.fa("needs_space_tool"));
    public static final TagKey<Block> NEEDS_SUPER_SPACE_TOOL = BlockTags.create(RlUtils.fa("needs_super_space_tool"));
    public static final TagKey<Block> NEEDS_OP_TOOL = BlockTags.create(RlUtils.fa("needs_op_tool"));

    public static Tier WOLF = TierSortingRegistry.registerTier(new ForgeTier(3, 233, 20, 52.8f, 0, BlockTags.NEEDS_DIAMOND_TOOL,
                    () -> Ingredient.of(Items.NETHER_STAR)), RlUtils.fa( "wolf"),
            List.of(Tiers.IRON), List.of(Tiers.NETHERITE));
    public static Tier BH3 = TierSortingRegistry.registerTier(new ForgeTier(3, 233, 20, 15, 10, BlockTags.NEEDS_DIAMOND_TOOL,
                    () -> Ingredient.of(Items.NETHER_STAR)), RlUtils.fa( "bh3"),
            List.of(Tiers.IRON), List.of(Tiers.NETHERITE));
    public static Tier RUBY = TierSortingRegistry.registerTier(new ForgeTier(3, 1456, 11, 4, 10, BlockTags.NEEDS_DIAMOND_TOOL,
                    () -> Ingredient.of(SAItems.ruby.get())), RlUtils.fa( "ruby"),
            List.of(Tiers.IRON), List.of(Tiers.NETHERITE));
    public static Tier JADE = TierSortingRegistry.registerTier(new ForgeTier(3, 1589, 15, 5, 11, BlockTags.NEEDS_DIAMOND_TOOL,
                    () -> Ingredient.of(SAItems.jade.get())), RlUtils.fa( "jade"),
            List.of(Tiers.IRON), List.of(Tiers.NETHERITE));
    public static Tier XRAY = TierSortingRegistry.registerTier(new ForgeTier(4, 2179, 19, 12, 12, NEEDS_XRAY_TOOL,
                    () -> Ingredient.of(SAItems.xrayIngot.get())), RlUtils.fa( "xray"),
            List.of(Tiers.DIAMOND), List.of());
    public static Tier SUPER = TierSortingRegistry.registerTier(new ForgeTier(4, 2605, 22, 15, 14, NEEDS_XRAY_TOOL,
                    () -> Ingredient.of(SAItems.superIngot.get())), RlUtils.fa( "super"),
            List.of(Tiers.DIAMOND), List.of());
    public static Tier DRAGON = TierSortingRegistry.registerTier(new ForgeTier(5, 3465, 25, 18, 15, NEEDS_DRAGON_TOOL,
                    () -> Ingredient.of(SAItems.dragonCrystal.get())), RlUtils.fa( "dragon"),
            List.of(Tiers.NETHERITE), List.of());
    public static Tier SUPER_XRAY = TierSortingRegistry.registerTier(new ForgeTier(5, 3947, 30, 23, 16, NEEDS_DRAGON_TOOL,
                    () -> Ingredient.of(SAItems.superXrayIngot.get())), RlUtils.fa( "super_xray"),
            List.of(Tiers.NETHERITE), List.of());
    public static Tier ULTRA = TierSortingRegistry.registerTier(new ForgeTier(6, 4467, 35, 29, 20, NEEDS_ULTRA_TOOL,
                    () -> Ingredient.of(SAItems.ultraIngot.get())), RlUtils.fa( "ultra"),
            List.of(DRAGON), List.of());
    public static Tier SPACE = TierSortingRegistry.registerTier(new ForgeTier(7, 5924, 50, 50, 30, NEEDS_SPACE_TOOL,
                    () -> Ingredient.of(SAItems.spaceIngot.get())), RlUtils.fa( "space"),
            List.of(ULTRA), List.of());
    public static Tier SUPER_SPACE = TierSortingRegistry.registerTier(new ForgeTier(9, 1024, 50, 12, 16, NEEDS_SUPER_SPACE_TOOL,
                    () -> Ingredient.of(SAItems.spaceCore.get())), RlUtils.fa( "super_space"),
            List.of(SPACE), List.of());
    public static Tier OP = TierSortingRegistry.registerTier(new ForgeTier(99, 9999, 999, 10, 0, NEEDS_OP_TOOL,
                    () -> Ingredient.EMPTY), RlUtils.fa( "op"),
            List.of(SUPER_SPACE), List.of());
}
