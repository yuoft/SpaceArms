package com.yuo.spacearms.Arms;

import java.util.function.Supplier;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Spacearms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * 盔甲材料类
 */
public enum MyArmorMaterial implements IArmorMaterial {
	//---------材质---耐久值----------护甲值-------附魔能力--------音效----------------------盔甲韧性--修复材料
	RUBY(Spacearms.MODID + ":" + "ruby", 30, new int[] { 3, 6, 8, 3 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
		return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
	}),
	TOTEM(Spacearms.MODID + ":" + "totem", 25, new int[] { 3, 6, 8, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.5F, () -> {
		return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
	}),
	GLOWSTONE(Spacearms.MODID + ":" + "glowstone", 13, new int[] { 2, 5, 6, 2 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5F, () -> {
		return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
	}),
	EMERALD(Spacearms.MODID + ":" + "emerald", 35, new int[] { 4, 7, 9, 4 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, () -> {
		return Ingredient.fromItems(ItemRegistry.emeraldIngot.get());
	}),
	SPACE(Spacearms.MODID + ":" + "space", 55, new int[] { 10, 13, 15, 10 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F, () -> {
		return Ingredient.fromItems(ItemRegistry.spaceIngot.get());
	}),
	NETHER(Spacearms.MODID + ":" + "netherite", 40, new int[] { 5, 8, 10, 5 }, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, () -> {
		return Ingredient.fromItems(ItemRegistry.netheriteIngot.get());
	}),
	DRAGON(Spacearms.MODID + ":" + "dragon", 45, new int[] { 7, 10, 12, 7 }, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5F, () -> {
		return Ingredient.fromItems(ItemRegistry.dragonCrystal.get());
	}),
	OP(Spacearms.MODID + ":" + "op", 10000, new int[] { 300, 600, 800, 300 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 99.0f, () -> {
		return Ingredient.EMPTY;
	});

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyValue<Ingredient> repairMaterial;

	MyArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier) {
	      this.name = nameIn;
	      this.maxDamageFactor = maxDamageFactorIn;
	      this.damageReductionAmountArray = damageReductionAmountsIn;
	      this.enchantability = enchantabilityIn;
	      this.soundEvent = equipSoundIn;
	      this.toughness = toughnessIn;
	      this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
	   }

	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

}
