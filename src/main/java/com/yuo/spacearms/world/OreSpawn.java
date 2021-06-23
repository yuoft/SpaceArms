package com.yuo.spacearms.world;

import com.mojang.datafixers.Dynamic;
import com.yuo.spacearms.Blocks.BlockRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.EndGatewayConfig;
import net.minecraft.world.gen.feature.EndGatewayFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

/**
 * 矿物生成
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreSpawn {
	@SubscribeEvent
    public static void oreGenerate(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) { //主世界
			biome.addFeature(Decoration.UNDERGROUND_DECORATION, //下届矿物生成
					Feature.ORE.withConfiguration(
							new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
									BlockRegistry.netheriteOre.get().getDefaultState(), 3)) //生成矿物， 最大生成数量
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 5, 0, 32))) //次数，最低高度, 最高高度，范围
			);
			biome.addFeature(Decoration.UNDERGROUND_DECORATION,
					Feature.ORE.withConfiguration(
							new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
									BlockRegistry.fragileBedrock.get().getDefaultState(), 3))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(100, 0, 0, 5))) //次数，最低高度, 最高高度，范围
			);
			biome.addFeature(Decoration.UNDERGROUND_DECORATION,
					Feature.ORE.withConfiguration(
							new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
									BlockRegistry.fragileBedrock.get().getDefaultState(), 3))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(100, 123, 0, 128))) //次数，最低高度, 最高高度，范围
			);
			if (biome.equals(Biomes.THE_END) || biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.END_HIGHLANDS) ||
					biome.equals(Biomes.END_MIDLANDS) || biome.equals(Biomes.SMALL_END_ISLANDS)){
				biome.addFeature(Decoration.UNDERGROUND_STRUCTURES, //末地矿物生成
						Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("END_STONE","endstone", new BlockMatcher(Blocks.END_STONE)),
								BlockRegistry.endSpaceOre.get().getDefaultState(), 6))
								.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 32, 0, 64)))
				);

			}
			if (biome.equals(Biomes.END_BARRENS) || biome.equals(Biomes.END_HIGHLANDS) ||
					biome.equals(Biomes.END_MIDLANDS) || biome.equals(Biomes.SMALL_END_ISLANDS)){
				biome.addFeature(Decoration.UNDERGROUND_STRUCTURES, //末地外岛矿物生成
						Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("END_STONE","endstone", new BlockMatcher(Blocks.END_STONE)),
								BlockRegistry.dragonOre.get().getDefaultState(), 4))
								.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(15, 32, 0, 64)))
				);

			}
            biome.addFeature(Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    BlockRegistry.emeraldIngotOre.get().getDefaultState(), 10))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 32, 0, 64)))
            		);
            biome.addFeature(Decoration.UNDERGROUND_ORES,
            		Feature.ORE.withConfiguration(
            				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            						BlockRegistry.rubyOre.get().getDefaultState(), 8))
            				.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 5, 0, 20)))
            		);
            biome.addFeature(Decoration.UNDERGROUND_ORES,
            		Feature.ORE.withConfiguration(
            				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            						BlockRegistry.spaceOre.get().getDefaultState(), 3))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 2, 0, 10)))
            		);
            biome.addFeature(Decoration.UNDERGROUND_ORES,
            		Feature.ORE.withConfiguration(
            				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
            						BlockRegistry.fragileBedrock.get().getDefaultState(), 3))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(50, 0, 0, 5)))
            		);
        }
    }
}
