package com.yuo.spacearms.world;

import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class OreBiomeModifiers {
    // 注册的key
    public static final ResourceKey<BiomeModifier> ADD_ORE_JADE = registerKey("ore_jade");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RUBY_NETHER = registerKey("ore_ruby_nether");
    public static final ResourceKey<BiomeModifier> ADD_ORE_SPACE = registerKey("ore_space");
    public static final ResourceKey<BiomeModifier> ADD_ORE_SPACE_END = registerKey("ore_space_end");
    public static final ResourceKey<BiomeModifier> ADD_ORE_FRAGILE_BEDROCK = registerKey("ore_fragile_bedrock");
    public static final ResourceKey<BiomeModifier> ADD_ORE_FRAGILE_BEDROCK_NETHER_TOP = registerKey("ore_fragile_bedrock_nether_top");
    public static final ResourceKey<BiomeModifier> ADD_ORE_FRAGILE_BEDROCK_NETHER_DOWN = registerKey("ore_fragile_bedrock_nether_down");
    public static final ResourceKey<BiomeModifier> ADD_ORE_XRAY = registerKey("ore_xray");
    public static final ResourceKey<BiomeModifier> ADD_ORE_DRAGON_END = registerKey("ore_dragon_end");
    public static final ResourceKey<BiomeModifier> ADD_ORE_SUPER_END = registerKey("ore_super_end");
    // BootstapContext 数据生成的上下文
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        // 通过上下文获得PLACED_FEATURE的注册HolderGetter
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        // 通过上下文获得BIOME的HolderGetter
        var biomes = context.lookup(Registries.BIOME);
        // 生成json文件，第一个参数是key，第二个参数是BiomeModifiers，
        // 我们使用了子类AddFeaturesBiomeModifier，是指添加feature给biome
        // 第一个参数是holderset的biome ，这里是否是主世界的生物群系。即返回了主世界的生物群系
        // 第二个holdlerSet是指所有的feature，我们通过placedFeatures获得
        // 丢三个参数要求给出在世界生成的什么阶段加你的feature，我们这里是地下矿物生成的时候，你可以到该类下面看看，这是个枚举，
        context.register(ADD_ORE_JADE, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_JADE)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RUBY, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_RUBY)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RUBY_NETHER, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_RUBY_NETHER)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_SPACE, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_SPACE)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_SPACE_END, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_SPACE_END)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_FRAGILE_BEDROCK, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_FRAGILE_BEDROCK)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_FRAGILE_BEDROCK_NETHER_TOP, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_FRAGILE_BEDROCK_NETHER_TOP)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_FRAGILE_BEDROCK_NETHER_DOWN, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_FRAGILE_BEDROCK_NETHER_DOWN)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_XRAY, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_XRAY)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_DRAGON_END, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_DRAGON_END)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_SUPER_END, new AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(OrePlacements.ORE_SUPER_END)), GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, RlUtils.fa(SpaceArms.MOD_ID, name));
    }
}
