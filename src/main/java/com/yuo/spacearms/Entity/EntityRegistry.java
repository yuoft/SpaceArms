package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Spacearms;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 实体注册
 */
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Spacearms.MODID);
    //投掷物
    public static RegistryObject<EntityType<DragonCrystalEntity>> DRAGON_CRYSTAL = ENTITY_TYPES.register("dragon_crystal", () -> {
        return EntityType.Builder.<DragonCrystalEntity>create(DragonCrystalEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("dragon_crystal");
    });
    public static RegistryObject<EntityType<GoldTntEntity>> GOLD_TNT = ENTITY_TYPES.register("gold_tnt", () -> {
        return EntityType.Builder.<GoldTntEntity>create(GoldTntEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("gold_tnt");
    });
    public static RegistryObject<EntityType<IronArrowEntity>> IRON_ARROW = ENTITY_TYPES.register("iron_arrow", () -> {
        return EntityType.Builder.<IronArrowEntity>create(IronArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("iron_arrow");
    });
    public static RegistryObject<EntityType<GoldArrowEntity>> GOLD_ARROW = ENTITY_TYPES.register("gold_arrow", () -> {
        return EntityType.Builder.<GoldArrowEntity>create(GoldArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("gold_arrow");
    });
    public static RegistryObject<EntityType<DiamondArrowEntity>> DIAMOND_ARROW = ENTITY_TYPES.register("diamond_arrow", () -> {
        return EntityType.Builder.<DiamondArrowEntity>create(DiamondArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("diamond_arrow");
    });
    public static RegistryObject<EntityType<NetheriteArrowEntity>> NETHERITE_ARROW = ENTITY_TYPES.register("netherite_arrow", () -> {
        return EntityType.Builder.<NetheriteArrowEntity>create(NetheriteArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("netherite_arrow");
    });
    public static RegistryObject<EntityType<DragonArrowEntity>> DRAGON_ARROW = ENTITY_TYPES.register("dragon_arrow", () -> {
        return EntityType.Builder.<DragonArrowEntity>create(DragonArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("dragon_arrow");
    });
    public static RegistryObject<EntityType<SpaceArrowEntity>> SPACE_ARROW = ENTITY_TYPES.register("space_arrow", () -> {
        return EntityType.Builder.<SpaceArrowEntity>create(SpaceArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("space_arrow");
    });
    public static RegistryObject<EntityType<EnderArrowEntity>> ENDER_ARROW = ENTITY_TYPES.register("ender_arrow", () -> {
        return EntityType.Builder.<EnderArrowEntity>create(EnderArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("ender_arrow");
    });
    public static RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> {
        return EntityType.Builder.<FireArrowEntity>create(FireArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("fire_arrow");
    });
    public static RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> {
        return EntityType.Builder.<IceArrowEntity>create(IceArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("ice_arrow");
    });
    public static RegistryObject<EntityType<AmosiArrowEntity>> AMOSI_ARROW = ENTITY_TYPES.register("amosi_arrow", () -> {
        return EntityType.Builder.<AmosiArrowEntity>create(AmosiArrowEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("amosi_arrowv");
    });

}
