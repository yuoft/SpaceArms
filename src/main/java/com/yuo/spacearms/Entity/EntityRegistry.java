package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Spacearms;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 实体注册
 */
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Spacearms.MODID);
    //投掷物
    public static RegistryObject<EntityType<DragonCrystalEntity>> DRAGON_CRYSTAL = ENTITY_TYPES.register("dragon_crystal", () -> {
        return EntityType.Builder.<DragonCrystalEntity>create(DragonCrystalEntity::new, EntityClassification.MISC)
                .size(0.5f, 0.5F).build("dragon_crystal");
    });

}
