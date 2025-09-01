package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Entity.Mob.*;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 实体注册
 */
public class SAEntitys {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SpaceArms.MOD_ID);
    //投掷物
    public static RegistryObject<EntityType<DragonCrystalEntity>> DRAGON_CRYSTAL = ENTITY_TYPES.register("dragon_crystal",
            () -> EntityType.Builder.<DragonCrystalEntity>create(DragonCrystalEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("dragon_crystal"));
    public static RegistryObject<EntityType<IronArrowEntity>> IRON_ARROW = ENTITY_TYPES.register("iron_arrow",
            () -> EntityType.Builder.<IronArrowEntity>create(IronArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("iron_arrow"));
    public static RegistryObject<EntityType<GoldArrowEntity>> GOLD_ARROW = ENTITY_TYPES.register("gold_arrow",
            () -> EntityType.Builder.<GoldArrowEntity>create(GoldArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("gold_arrow"));
    public static RegistryObject<EntityType<DiamondArrowEntity>> DIAMOND_ARROW = ENTITY_TYPES.register("diamond_arrow",
            () -> EntityType.Builder.<DiamondArrowEntity>create(DiamondArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("diamond_arrow"));
    public static RegistryObject<EntityType<NetheriteArrowEntity>> NETHERITE_ARROW = ENTITY_TYPES.register("netherite_arrow",
            () -> EntityType.Builder.<NetheriteArrowEntity>create(NetheriteArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("netherite_arrow"));
    public static RegistryObject<EntityType<DragonArrowEntity>> DRAGON_ARROW = ENTITY_TYPES.register("dragon_arrow",
            () -> EntityType.Builder.<DragonArrowEntity>create(DragonArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("dragon_arrow"));
    public static RegistryObject<EntityType<SpaceArrowEntity>> SPACE_ARROW = ENTITY_TYPES.register("space_arrow",
            () -> EntityType.Builder.<SpaceArrowEntity>create(SpaceArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("space_arrow"));
    public static RegistryObject<EntityType<EnderArrowEntity>> ENDER_ARROW = ENTITY_TYPES.register("ender_arrow",
            () -> EntityType.Builder.<EnderArrowEntity>create(EnderArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("ender_arrow"));
    public static RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow",
            () -> EntityType.Builder.<FireArrowEntity>create(FireArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("fire_arrow"));
    public static RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow",
            () -> EntityType.Builder.<IceArrowEntity>create(IceArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("ice_arrow"));
    public static RegistryObject<EntityType<AmosiArrowEntity>> AMOSI_ARROW = ENTITY_TYPES.register("amosi_arrow",
            () -> EntityType.Builder.<AmosiArrowEntity>create(AmosiArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("amosi_arrow"));
    public static RegistryObject<EntityType<AmosiBowArrowEntity>> AMOSI_BOW_ARROW = ENTITY_TYPES.register("amosi_bow_arrow",
            () -> EntityType.Builder.<AmosiBowArrowEntity>create(AmosiBowArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5F).build("amosi_bow_arrow"));

    //怪物
    public static final RegistryObject<EntityType<GreenZombie>> GREEN_ZOMBIE = ENTITY_TYPES.register("green_zombie",
            () -> EntityType.Builder.create(GreenZombie::new, EntityClassification.MONSTER)
                    .size(0.6F, 1.95F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_zombie"));
    public static final RegistryObject<EntityType<RedZombie>> RED_ZOMBIE = ENTITY_TYPES.register("red_zombie",
            () -> EntityType.Builder.create(RedZombie::new, EntityClassification.MONSTER)
                    //防火，碰撞大小（宽，高），跟踪范围，更新时间，免疫方块
                    .immuneToFire().size(0.6F, 1.95F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_zombie"));
    public static final RegistryObject<EntityType<GreenSkeleton>> GREEN_SKELETON = ENTITY_TYPES.register("green_skeleton",
            () -> EntityType.Builder.create(GreenSkeleton::new, EntityClassification.MONSTER)
                    .size(0.6F, 1.99F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_skeleton"));
    public static final RegistryObject<EntityType<RedSkeleton>> RED_SKELETON = ENTITY_TYPES.register("red_skeleton",
            () -> EntityType.Builder.create(RedSkeleton::new, EntityClassification.MONSTER)
                    .immuneToFire().size(0.6F, 1.99F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_skeleton"));
    public static final RegistryObject<EntityType<GreenSpider>> GREEN_SPIDER = ENTITY_TYPES.register("green_spider",
            () -> EntityType.Builder.create(GreenSpider::new, EntityClassification.MONSTER)
                    .size(1.4F, 0.9F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_spider"));
    public static final RegistryObject<EntityType<RedSpider>> RED_SPIDER = ENTITY_TYPES.register("red_spider",
            () -> EntityType.Builder.create(RedSpider::new, EntityClassification.MONSTER)
                    .immuneToFire().size(1.4F, 0.9F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_spider"));
    public static final RegistryObject<EntityType<GreenCreeper>> GREEN_CREEPER = ENTITY_TYPES.register("green_creeper",
            () -> EntityType.Builder.create(GreenCreeper::new, EntityClassification.MONSTER)
                    .size(0.6F, 1.7F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_creeper"));
    public static final RegistryObject<EntityType<RedCreeper>> RED_CREEPER = ENTITY_TYPES.register("red_creeper",
            () -> EntityType.Builder.create(RedCreeper::new, EntityClassification.MONSTER)
                    .immuneToFire().size(0.6F, 1.7F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_creeper"));
    public static final RegistryObject<EntityType<GreenEnderman>> GREEN_ENDERMAN = ENTITY_TYPES.register("green_enderman",
            () -> EntityType.Builder.create(GreenEnderman::new, EntityClassification.MONSTER)
                    .size(0.6F, 1.95F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_enderman"));
    public static final RegistryObject<EntityType<RedEnderman>> RED_ENDERMAN = ENTITY_TYPES.register("red_enderman",
            () -> EntityType.Builder.create(RedEnderman::new, EntityClassification.MONSTER)
                    .immuneToFire().size(0.6F, 1.95F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_enderman"));

}
