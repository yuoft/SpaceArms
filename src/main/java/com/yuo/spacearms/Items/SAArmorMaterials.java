package com.yuo.spacearms.Items;

import com.yuo.spacearms.SpaceArms;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * 盔甲材料类
 */
public enum SAArmorMaterials implements ArmorMaterial {
	//---------材质---耐久值----------护甲值-------附魔能力--------音效----------------------盔甲韧性- 击退抗性-修复材料
	TOTEM(SpaceArms.MOD_ID + ":" + "totem", 15, new int[] { 3, 6, 8, 3 }, 15,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> Ingredient.of(Items.TOTEM_OF_UNDYING)),
	GLOWSTONE(SpaceArms.MOD_ID + ":" + "glowstone", 21, new int[] { 2, 5, 6, 2 }, 8,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 0,0, () -> Ingredient.of(Items.GLOWSTONE)),
	RUBY(SpaceArms.MOD_ID + ":" + "ruby", 43, new int[] { 4, 7, 9, 4 }, 10,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 2F, 0,() -> Ingredient.of(SAItems.ruby.get())),
	JADE(SpaceArms.MOD_ID + ":" + "emerald", 56, new int[] { 5, 8, 10, 5 }, 11,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> Ingredient.of(SAItems.jade.get())),
	XRAY(SpaceArms.MOD_ID + ":" + "xray", 61, new int[] { 7, 10, 12, 7 }, 12,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 2F, 0,() -> Ingredient.of(SAItems.xrayIngot.get())),
	SUPER(SpaceArms.MOD_ID + ":" + "super", 69, new int[] { 12, 15, 17, 12 }, 13,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, 0,() -> Ingredient.of(SAItems.superIngot.get())),
	DRAGON(SpaceArms.MOD_ID + ":" + "dragon", 78, new int[] { 14, 17, 19, 14 }, 15,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 3.5F, 0.1f,() -> Ingredient.of(SAItems.dragonCrystal.get())),
	SUPER_XRAY(SpaceArms.MOD_ID + ":" + "xray", 85, new int[] { 19, 22, 24, 19 }, 15,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 3.5F, 0.05f,() -> Ingredient.of(SAItems.superXrayIngot.get())),
	ULTRA(SpaceArms.MOD_ID + ":" + "ultra", 94, new int[] { 23, 26, 28, 23 }, 16,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 4F, 0.15f,() -> Ingredient.of(SAItems.ultraIngot.get())),
	SPACE(SpaceArms.MOD_ID + ":" + "space", 111, new int[] { 29, 36, 39, 29 }, 30,
			SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0F, 0.3f,() -> Ingredient.of(SAItems.spaceIngot.get())),
	OP(SpaceArms.MOD_ID + ":" + "op", 999, new int[] { 3, 6, 8, 3 }, 0,
			SoundEvents.ARMOR_EQUIP_NETHERITE, 0, 10,() -> Ingredient.EMPTY);

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 23, 25, 27, 21 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyLoadedValue<Ingredient> repairMaterial;
	private final float knockbackResistance;

	SAArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
	   }

	@Override
	public int getDurabilityForType(Type type) {
		return MAX_DAMAGE_ARRAY[type.getSlot().getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForType(Type type) {
		return this.damageReductionAmountArray[type.getSlot().getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Nonnull
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
