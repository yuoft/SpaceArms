package com.yuo.spacearms.world;

import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.RlUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class OreFeatures {
    // 创建OreFeature对应的ResourceKey
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE = createKey("ore_jade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = createKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_NETHER = createKey("ore_ruby_nether");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPACE = createKey("ore_space");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPACE_DEEP = createKey("ore_space_deep");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPACE_END = createKey("ore_space_end");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FRAGILE_BEDROCK = createKey("ore_fragile_bedrock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FRAGILE_BEDROCK_NETHER_TOP = createKey("ore_fragile_bedrock_nether_top");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FRAGILE_BEDROCK_NETHER_DOWN = createKey("ore_fragile_bedrock_nether_down");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_XRAY = createKey("ore_xray");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DRAGON_END = createKey("ore_dragon_end");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SUPER_END = createKey("ore_super_end");

    //BootstapContext 是我们datagen的上下文，等会我们使用数据生成的时候说。
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        //  创建对应的tag，如果有多个就创建多个
        RuleTest stoneOreReplaceRuleTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateOreReplaceRuleTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherOreReplaceRuleTest = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endOreReplaceRuleTest = new BlockMatchTest(Blocks.END_STONE);

        // 创建一个list
        List<TargetBlockState> jadeOre = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, SABlocks.jadeOre.get().defaultBlockState())
        );
        List<TargetBlockState> rubyOre = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, SABlocks.rubyOre.get().defaultBlockState())
        );
        List<TargetBlockState> rubyOreNether = List.of(
                OreConfiguration.target(netherOreReplaceRuleTest, SABlocks.rubyOre.get().defaultBlockState())
        );
        List<TargetBlockState> spaceOre = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, SABlocks.spaceOre.get().defaultBlockState()),
                OreConfiguration.target(deepSlateOreReplaceRuleTest, SABlocks.spaceOre.get().defaultBlockState())
        );
        List<TargetBlockState> spaceOreEnd = List.of(
                OreConfiguration.target(endOreReplaceRuleTest, SABlocks.spaceOre.get().defaultBlockState())
        );
        List<TargetBlockState> fragileBedrockOre = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, SABlocks.fragileBedrock.get().defaultBlockState())
        );
        List<TargetBlockState> fragileBedrockOreNetherTop = List.of(
                OreConfiguration.target(netherOreReplaceRuleTest, SABlocks.fragileBedrock.get().defaultBlockState())
        );
        List<TargetBlockState> fragileBedrockOreNetherDown = List.of(
                OreConfiguration.target(netherOreReplaceRuleTest, SABlocks.fragileBedrock.get().defaultBlockState())
        );
        List<TargetBlockState> xrayOre = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, SABlocks.xrayBlock.get().defaultBlockState())
        );
        List<TargetBlockState> dragonOre = List.of(
                OreConfiguration.target(endOreReplaceRuleTest, SABlocks.dragonOre.get().defaultBlockState())
        );
        List<TargetBlockState> superOre = List.of(
                OreConfiguration.target(endOreReplaceRuleTest, SABlocks.superOre.get().defaultBlockState())
        );

        // 注册对应orefeature，使用listOreConfiguration，9 上文提到的size
        FeatureUtils.register(pContext, ORE_JADE, Feature.ORE, new OreConfiguration(jadeOre, 6));
        FeatureUtils.register(pContext, ORE_RUBY, Feature.ORE, new OreConfiguration(rubyOre, 4));
        FeatureUtils.register(pContext, ORE_RUBY_NETHER, Feature.ORE, new OreConfiguration(rubyOreNether, 5));
        FeatureUtils.register(pContext, ORE_SPACE, Feature.ORE, new OreConfiguration(spaceOre, 3));
        FeatureUtils.register(pContext, ORE_SPACE_END, Feature.ORE, new OreConfiguration(spaceOreEnd, 5));
        FeatureUtils.register(pContext, ORE_FRAGILE_BEDROCK, Feature.ORE, new OreConfiguration(fragileBedrockOre, 2));
        FeatureUtils.register(pContext, ORE_FRAGILE_BEDROCK_NETHER_TOP, Feature.ORE, new OreConfiguration(fragileBedrockOreNetherTop, 3));
        FeatureUtils.register(pContext, ORE_FRAGILE_BEDROCK_NETHER_DOWN, Feature.ORE, new OreConfiguration(fragileBedrockOreNetherDown, 3));
        FeatureUtils.register(pContext, ORE_XRAY, Feature.ORE, new OreConfiguration(xrayOre, 4));
        FeatureUtils.register(pContext, ORE_DRAGON_END, Feature.ORE, new OreConfiguration(dragonOre, 3));
        FeatureUtils.register(pContext, ORE_SUPER_END, Feature.ORE, new OreConfiguration(superOre, 4));
    }

    // 创建ResourceKey的方法
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, RlUtils.fa(pName));
    }
}
