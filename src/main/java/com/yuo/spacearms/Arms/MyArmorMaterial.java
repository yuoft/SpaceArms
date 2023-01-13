package com.yuo.spacearms.Arms;

import com.yuo.spacearms.Items.SAItems;
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

import javax.annotation.Nonnull;
import java.util.function.Supplier;

//盔甲材料类
public enum MyArmorMaterial implements IArmorMaterial {
	//---------材质---耐久值----------护甲值-------附魔能力--------音效----------------------盔甲韧性- 击退抗性-修复材料
	RUBY(Spacearms.MOD_ID + ":" + "ruby", 30, new int[] { 4, 7, 9, 4 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, 0,() -> {
		return Ingredient.fromItems(SAItems.rubyIngot.get());
	}),
	TOTEM(Spacearms.MOD_ID + ":" + "totem", 18, new int[] { 3, 6, 8, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> {
		return Ingredient.fromItems(Items.TOTEM_OF_UNDYING);
	}),
	GLOWSTONE(Spacearms.MOD_ID + ":" + "glowstone", 13, new int[] { 2, 5, 6, 2 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0,0, () -> {
		return Ingredient.fromItems(Items.GLOWSTONE);
	}),
	EMERALD(Spacearms.MOD_ID + ":" + "emerald", 33, new int[] { 5, 8, 10, 5 }, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> {
		return Ingredient.fromItems(SAItems.emeraldIngot.get());
	}),
	SPACE(Spacearms.MOD_ID + ":" + "space", 49, new int[] { 16, 20, 25, 16 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F, 0.3f,() -> {
		return Ingredient.fromItems(SAItems.spaceIngot.get());
	}),
	DRAGON(Spacearms.MOD_ID + ":" + "dragon", 38, new int[] { 8, 11, 13, 8 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5F, 0.1f,() -> {
		return Ingredient.fromItems(SAItems.dragonCrystal.get());
	}),
	XRAY(Spacearms.MOD_ID + ":" + "xray", 33, new int[] { 6, 9, 11, 6 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, 0,() -> {
		return Ingredient.fromItems(SAItems.xrayIngot.get());
	}),
	SUPER_XRAY(Spacearms.MOD_ID + ":" + "xray", 37, new int[] { 11, 14, 16, 11 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5F, 0.05f,() -> {
		return Ingredient.fromItems(SAItems.superXrayIngot.get());
	}),
	SUPER(Spacearms.MOD_ID + ":" + "super", 35, new int[] { 8, 11, 13, 8 }, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3F, 0,() -> {
		return Ingredient.fromItems(SAItems.superIngot.get());
	}),
	ULTRA(Spacearms.MOD_ID + ":" + "ultra", 39, new int[] { 13, 16, 18, 13 }, 16, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4F, 0.15f,() -> {
		return Ingredient.fromItems(SAItems.ultraIngot.get());
	}),
	SLIME(Spacearms.MOD_ID + ":" + "slime", 10, new int[] { 1, 3, 4, 2 }, 10, SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 0.5F, 0,() -> {
		return Ingredient.fromItems(SAItems.slimeCrystal.get());
	}),
	OP(Spacearms.MOD_ID + ":" + "op", 1, new int[] { 3, 6, 8, 3 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0, 10,() -> Ingredient.EMPTY);

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyValue<Ingredient> repairMaterial;
	private final float knockbackResistance;

	MyArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = new LazyValue<>(repairMaterial);
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

	@Nonnull
	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	@Nonnull
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
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
