package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Entity.Arrow.*;
import com.yuo.spacearms.Entity.Mob.*;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 实体注册
 */
public class SAEntitys {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SpaceArms.MOD_ID);
    //投掷物
    public static RegistryObject<EntityType<DragonCrystal>> DRAGON_CRYSTAL = ENTITY_TYPES.register("dragon_crystal",
            () -> EntityType.Builder.<DragonCrystal>of(DragonCrystal::new, MobCategory.MISC)
                    .sized(0.5f, 0.5F).build("dragon_crystal"));
    public static RegistryObject<EntityType<IronArrow>> IRON_ARROW = ENTITY_TYPES.register("iron_arrow",
            () -> EntityType.Builder.<IronArrow>of(IronArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("iron_arrow"));
    public static RegistryObject<EntityType<GoldArrow>> GOLD_ARROW = ENTITY_TYPES.register("gold_arrow",
            () -> EntityType.Builder.<GoldArrow>of(GoldArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("gold_arrow"));
    public static RegistryObject<EntityType<DiamondArrow>> DIAMOND_ARROW = ENTITY_TYPES.register("diamond_arrow",
            () -> EntityType.Builder.<DiamondArrow>of(DiamondArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("diamond_arrow"));
    public static RegistryObject<EntityType<NetheriteArrow>> NETHERITE_ARROW = ENTITY_TYPES.register("netherite_arrow",
            () -> EntityType.Builder.<NetheriteArrow>of(NetheriteArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("netherite_arrow"));
    public static RegistryObject<EntityType<DragonArrow>> DRAGON_ARROW = ENTITY_TYPES.register("dragon_arrow",
            () -> EntityType.Builder.<DragonArrow>of(DragonArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("dragon_arrow"));
    public static RegistryObject<EntityType<SpaceArrow>> SPACE_ARROW = ENTITY_TYPES.register("space_arrow",
            () -> EntityType.Builder.<SpaceArrow>of(SpaceArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("space_arrow"));
    public static RegistryObject<EntityType<EnderArrow>> ENDER_ARROW = ENTITY_TYPES.register("ender_arrow",
            () -> EntityType.Builder.<EnderArrow>of(EnderArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("ender_arrow"));
    public static RegistryObject<EntityType<FireArrow>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow",
            () -> EntityType.Builder.<FireArrow>of(FireArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("fire_arrow"));
    public static RegistryObject<EntityType<IceArrow>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow",
            () -> EntityType.Builder.<IceArrow>of(IceArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("ice_arrow"));
    public static RegistryObject<EntityType<AmosiArrow>> AMOSI_ARROW = ENTITY_TYPES.register("amosi_arrow",
            () -> EntityType.Builder.<AmosiArrow>of(AmosiArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("amosi_arrow"));
    public static RegistryObject<EntityType<AmosiBowArrow>> AMOSI_BOW_ARROW = ENTITY_TYPES.register("amosi_bow_arrow",
            () -> EntityType.Builder.<AmosiBowArrow>of(AmosiBowArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5F).build("amosi_bow_arrow"));

    //怪物
    public static final RegistryObject<EntityType<GreenZombie>> GREEN_ZOMBIE = ENTITY_TYPES.register("green_zombie",
            () -> EntityType.Builder.of(GreenZombie::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_zombie"));
    public static final RegistryObject<EntityType<RedZombie>> RED_ZOMBIE = ENTITY_TYPES.register("red_zombie",
            () -> EntityType.Builder.of(RedZombie::new, MobCategory.MONSTER)
                    //防火，碰撞大小（宽，高），跟踪范围，更新时间，免疫方块
                    .immuneToFire().sized(0.6F, 1.95F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_zombie"));
    public static final RegistryObject<EntityType<GreenSkeleton>> GREEN_SKELETON = ENTITY_TYPES.register("green_skeleton",
            () -> EntityType.Builder.of(GreenSkeleton::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.99F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_skeleton"));
    public static final RegistryObject<EntityType<RedSkeleton>> RED_SKELETON = ENTITY_TYPES.register("red_skeleton",
            () -> EntityType.Builder.of(RedSkeleton::new, MobCategory.MONSTER)
                    .immuneToFire().sized(0.6F, 1.99F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_skeleton"));
    public static final RegistryObject<EntityType<GreenSpider>> GREEN_SPIDER = ENTITY_TYPES.register("green_spider",
            () -> EntityType.Builder.of(GreenSpider::new, MobCategory.MONSTER)
                    .sized(1.4F, 0.9F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_spider"));
    public static final RegistryObject<EntityType<RedSpider>> RED_SPIDER = ENTITY_TYPES.register("red_spider",
            () -> EntityType.Builder.of(RedSpider::new, MobCategory.MONSTER)
                    .immuneToFire().sized(1.4F, 0.9F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_spider"));
    public static final RegistryObject<EntityType<GreenCreeper>> GREEN_CREEPER = ENTITY_TYPES.register("green_creeper",
            () -> EntityType.Builder.of(GreenCreeper::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.7F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_creeper"));
    public static final RegistryObject<EntityType<RedCreeper>> RED_CREEPER = ENTITY_TYPES.register("red_creeper",
            () -> EntityType.Builder.of(RedCreeper::new, MobCategory.MONSTER)
                    .immuneToFire().sized(0.6F, 1.7F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_creeper"));
    public static final RegistryObject<EntityType<GreenEnderman>> GREEN_ENDERMAN = ENTITY_TYPES.register("green_enderman",
            () -> EntityType.Builder.of(GreenEnderman::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).trackingRange(10).updateInterval(2).func_233607_a_(
                            Blocks.SWEET_BERRY_BUSH, Blocks.COBWEB).build("green_enderman"));
    public static final RegistryObject<EntityType<RedEnderman>> RED_ENDERMAN = ENTITY_TYPES.register("red_enderman",
            () -> EntityType.Builder.of(RedEnderman::new, MobCategory.MONSTER)
                    .immuneToFire().sized(0.6F, 1.95F).trackingRange(12).updateInterval(2).func_233607_a_(Blocks.WITHER_ROSE,
                            Blocks.SWEET_BERRY_BUSH, Blocks.SOUL_SAND, Blocks.COBWEB).build("red_enderman"));

}
