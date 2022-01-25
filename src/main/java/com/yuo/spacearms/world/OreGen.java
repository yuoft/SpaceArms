package com.yuo.spacearms.world;

import com.yuo.spacearms.Blocks.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

/**
 * 矿物生成
 */
public class OreGen {
    private static int topOffset = 0;

    public static void generateOres(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        //主世界
        if (!(event.getCategory().equals(Biome.Category.THEEND) || event.getCategory().equals(Biome.Category.NETHER))){
            addFeatureOverWorld(generation, BlockRegistry.emeraldIngotOre.get().getDefaultState(),
                    8, 32, 64, 5);
            addFeatureOverWorld(generation, BlockRegistry.rubyOre.get().getDefaultState(),
                    6, 5, 20, 5);
            addFeatureOverWorld(generation, BlockRegistry.spaceOre.get().getDefaultState(),
                    4, 2, 10, 10);
            addFeatureOverWorld(generation, BlockRegistry.fragileBedrock.get().getDefaultState(),
                    3, 0, 5, 50);
        }
        //下届
        if (event.getCategory().equals(Biome.Category.NETHER)){
            addFeatureNether(generation, BlockRegistry.fragileBedrock.get().getDefaultState(),
                    3, 0, 5, 100);
            addFeatureNether(generation, BlockRegistry.fragileBedrock.get().getDefaultState(),
                    3, 123, 128, 100);
            addFeatureNether(generation, BlockRegistry.rubyOre.get().getDefaultState(),
                    3, 16, 96, 50);
        }
        //末地
        if (event.getCategory().equals(Biome.Category.THEEND)){
            RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
            if (key.equals(Biomes.THE_END)){
                addFeatureTheend(generation, BlockRegistry.endSpaceOre.get().getDefaultState(),
                        6, 32, 64, 10);
            }
            addFeatureTheend(generation, BlockRegistry.dragonOre.get().getDefaultState(),
                    4, 32, 64, 10);
        }
    }

    /**
     * 主世界矿物生成规则
     */
    private static void addFeatureOverWorld(BiomeGenerationSettingsBuilder builder, BlockState state, int maxSize,
                                            int minHeight, int maxHeight, int genCount){
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, state, maxSize)) // 替换方块, 生成矿物， 最大生成数量
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, topOffset , maxHeight))) //最低高度, 0,最高高度
                        .square().func_242731_b(genCount)); //生成次数
    }
    /**
     * 下届矿物生成规则
     */
    private static void addFeatureNether(BiomeGenerationSettingsBuilder builder, BlockState state, int maxSize,
                                         int minHeight, int maxHeight, int genCount){
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, state, maxSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, topOffset , maxHeight)))
                        .square().func_242731_b(genCount));
    }
    /**
     * 末地矿物生成规则
     */
    private static void addFeatureTheend(BiomeGenerationSettingsBuilder builder, BlockState state, int maxSize,
                                         int minHeight, int maxHeight, int genCount){
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), state, maxSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, topOffset , maxHeight)))
                        .square().func_242731_b(genCount));
    }
}
