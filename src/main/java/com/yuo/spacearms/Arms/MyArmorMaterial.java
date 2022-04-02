package com.yuo.spacearms.Arms;

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

import javax.annotation.Nonnull;
import java.util.function.Supplier;

//盔甲材料类
public enum MyArmorMaterial implements IArmorMaterial {
	//---------材质---耐久值----------护甲值-------附魔能力--------音效----------------------盔甲韧性- 击退抗性-修复材料
	RUBY(Spacearms.MODID + ":" + "ruby", 30, new int[] { 3, 6, 8, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, 0,() -> {
		return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
	}),
	TOTEM(Spacearms.MODID + ":" + "totem", 18, new int[] { 3, 6, 8, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> {
		return Ingredient.fromItems(Items.TOTEM_OF_UNDYING);
	}),
	GLOWSTONE(Spacearms.MODID + ":" + "glowstone", 13, new int[] { 2, 5, 6, 2 }, 8, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0,0, () -> {
		return Ingredient.fromItems(Items.GLOWSTONE);
	}),
	EMERALD(Spacearms.MODID + ":" + "emerald", 33, new int[] { 4, 7, 9, 4 }, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0,() -> {
		return Ingredient.fromItems(ItemRegistry.emeraldIngot.get());
	}),
	SPACE(Spacearms.MODID + ":" + "space", 49, new int[] { 15, 18, 20, 15 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F, 0.3f,() -> {
		return Ingredient.fromItems(ItemRegistry.spaceIngot.get());
	}),
	DRAGON(Spacearms.MODID + ":" + "dragon", 38, new int[] { 8, 11, 13, 8 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5F, 0.1f,() -> {
		return Ingredient.fromItems(ItemRegistry.dragonCrystal.get());
	}),
	XRAY(Spacearms.MODID + ":" + "xray", 33, new int[] { 5, 8, 10, 5 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, 0,() -> {
		return Ingredient.fromItems(ItemRegistry.xrayIngot.get());
	}),
	SUPER_XRAY(Spacearms.MODID + ":" + "xray", 37, new int[] { 10, 13, 15, 10 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5F, 0.05f,() -> {
		return Ingredient.fromItems(ItemRegistry.superXrayIngot.get());
	}),
	SUPER(Spacearms.MODID + ":" + "super", 35, new int[] { 7, 10, 12, 7 }, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3F, 0,() -> {
		return Ingredient.fromItems(ItemRegistry.superIngot.get());
	}),
	ULTRA(Spacearms.MODID + ":" + "ultra", 39, new int[] { 12, 15, 17, 12 }, 16, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4F, 0.15f,() -> {
		return Ingredient.fromItems(ItemRegistry.ultraIngot.get());
	}),
	SLIME(Spacearms.MODID + ":" + "slime", 10, new int[] { 1, 3, 4, 2 }, 10, SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 0.5F, 0,() -> {
		return Ingredient.fromItems(ItemRegistry.slimeCrystal.get());
	}),
	OP(Spacearms.MODID + ":" + "op", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0, 10,() -> Ingredient.EMPTY);

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
