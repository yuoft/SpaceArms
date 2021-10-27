package com.yuo.spacearms.Items;

import com.yuo.spacearms.Arms.*;
import com.yuo.spacearms.Blocks.BlockRegistry;
import com.yuo.spacearms.Items.Bow.*;
import com.yuo.spacearms.Items.tool.*;
import com.yuo.spacearms.Spacearms;
import com.yuo.spacearms.tab.ModGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//物品注册管理器
public class ItemRegistry {
	//创建注册器。ForgeRegistries.ITEMS代表了我们要注册的是物品，第二个参数填入的应该是你的modId。
	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Spacearms.MODID);
	//注册物品。一个是「注册名」，还有一个就是你要注册对象的实例
	public static RegistryObject<Item> rubyIngot = ITEMS.register("ruby_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> spacePath = ITEMS.register("space_path", OrdinaryItem::new);
	public static RegistryObject<Item> spaceIngot = ITEMS.register("space_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> emeraldIngot = ITEMS.register("emerald_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> spaceCore = ITEMS.register("space_core", SpaceCore::new);
	public static RegistryObject<Item> dragonCrystal = ITEMS.register("dragon_crystal", OrdinaryItem::new);
	public static RegistryObject<Item> blazeBone = ITEMS.register("blaze_bone", OrdinaryItem::new);
	public static RegistryObject<Item> witherBone = ITEMS.register("wither_bone", OrdinaryItem::new);
	public static RegistryObject<Item> bedrockPowder = ITEMS.register("bedrock_powder", NetheriteItem::new);
	public static RegistryObject<Item> bedrockIngot = ITEMS.register("bedrock_ingot", NetheriteItem::new);
	public static RegistryObject<Item> bedrockNugget = ITEMS.register("bedrock_nugget", NetheriteItem::new);
	public static RegistryObject<Item> slimeCrystal = ITEMS.register("slime_crystal", OrdinaryItem::new);
	public static RegistryObject<Item> yuanshi = ITEMS.register("yuanshi", OrdinaryItem::new);
	public static RegistryObject<Item> jiejing = ITEMS.register("jiejing", OrdinaryItem::new);
	public static RegistryObject<Item> spaceString = ITEMS.register("space_string", OrdinaryItem::new);
	public static RegistryObject<Item> superString = ITEMS.register("super_string", OrdinaryItem::new);
	public static RegistryObject<Item> dragonString = ITEMS.register("dragon_string", OrdinaryItem::new);
	public static RegistryObject<Item> goldTnt = ITEMS.register("gold_tnt", GoldTnt::new);
	public static RegistryObject<Item> ironStick = ITEMS.register("iron_stick", OrdinaryItem::new);
	public static RegistryObject<Item> goldStick = ITEMS.register("gold_stick", OrdinaryItem::new);
	public static RegistryObject<Item> diamondStick = ITEMS.register("diamond_stick", OrdinaryItem::new);
	public static RegistryObject<Item> netheriteStick = ITEMS.register("netherite_stick", OrdinaryItem::new);

	//武器和工具
	public static RegistryObject<Item> slimeSlingshot = ITEMS.register("slime_slingshot", SlimeSlingshot::new);
	public static RegistryObject<Item> rubySword = ITEMS.register("ruby_sword", () -> {
		return new OrdinarySword(MyItemTier.RUBY, 3, -2.4f);
	});
	public static RegistryObject<Item> wolfSword = ITEMS.register("wolf_sword", () -> {
		return new WolfSword();
	});
	public static RegistryObject<Item> emeraldSword = ITEMS.register("emerald_sword", () -> {
		return new OrdinarySword(MyItemTier.EMERALD, 3, -2.4f);
	});
	public static RegistryObject<Item> spaceSword = ITEMS.register("space_sword", SpaceSword::new);
	public static RegistryObject<Item> opSword = ITEMS.register("op_sword", OpSword::new);
	public static RegistryObject<Item> rubyPickaxe = ITEMS.register("ruby_pickaxe", () -> {
		return new OrdinaryPickaxe(MyItemTier.RUBY, 1, -2.4f);
	});
	public static RegistryObject<Item> emeraldPickaxe = ITEMS.register("emerald_pickaxe", () -> {
		return new OrdinaryPickaxe(MyItemTier.EMERALD, 1, -2.4f);
	});
	public static RegistryObject<Item> spacePickaxe = ITEMS.register("space_pickaxe", SpacePickaxe::new);
	public static RegistryObject<Item> opPickaxe = ITEMS.register("op_pickaxe", OpPickaxe::new);
	public static RegistryObject<Item> rubyAxe = ITEMS.register("ruby_axe", () -> {
		return new OrdinaryAxe(MyItemTier.RUBY, 5, -3.0f);
	});
	public static RegistryObject<Item> emeraldAxe = ITEMS.register("emerald_axe", () -> {
		return new OrdinaryAxe(MyItemTier.EMERALD, 5, -3.0f);
	});
	public static RegistryObject<Item> dragonSword = ITEMS.register("dragon_sword", DragonPickaxe::new);
	public static RegistryObject<Item> dragonPickaxe = ITEMS.register("dragon_pickaxe", DragonSword::new);
	public static RegistryObject<Item> beheadSword = ITEMS.register("behead_sword", BeheadSword::new);
	public static RegistryObject<Item> superSpacePickaxe = ITEMS.register("super_space_pickaxe", SuperSpacePickaxe::new);
	public static RegistryObject<Item> totemSword = ITEMS.register("totem_sword", () -> {
		return new OrdinarySword(ItemTier.DIAMOND, 3, -2.4f);
	});
	public static RegistryObject<Item> glowstoneSword = ITEMS.register("glowstone_sword", () -> {
		return new OrdinarySword(ItemTier.IRON, 3, -2.4f);
	});

	//弓 箭
	public static RegistryObject<BowItem> ironBow = ITEMS.register("iron_bow", IronBow::new);
	public static RegistryObject<BowItem> goldBow = ITEMS.register("gold_bow", GoldBow::new);
	public static RegistryObject<BowItem> diamondBow = ITEMS.register("diamond_bow", DiamondBow::new);
	public static RegistryObject<BowItem> netheriteBow = ITEMS.register("netherite_bow", NetheriteBow::new);
	public static RegistryObject<BowItem> dragonBow = ITEMS.register("dragon_bow", DragonBow::new);
	public static RegistryObject<BowItem> spaceBow = ITEMS.register("space_bow", SpaceBow::new);
	public static RegistryObject<BowItem> enderBow = ITEMS.register("ender_bow", EnderBow::new);
	public static RegistryObject<BowItem> fireBow = ITEMS.register("fire_bow", FireBow::new);
	public static RegistryObject<BowItem> iceBow = ITEMS.register("ice_bow", IceBow::new);
	public static RegistryObject<BowItem> amosiBow = ITEMS.register("amosi_bow", AmosiBow::new);
	public static RegistryObject<Item> ironArrow = ITEMS.register("iron_arrow", ModArrow::new);
	public static RegistryObject<Item> goldArrow = ITEMS.register("gold_arrow", ModArrow::new);
	public static RegistryObject<Item> diamondArrow = ITEMS.register("diamond_arrow", ModArrow::new);
	public static RegistryObject<Item> netheriteArrow = ITEMS.register("netherite_arrow", ModArrow::new);
	public static RegistryObject<Item> dragonArrow = ITEMS.register("dragon_arrow", ModArrow::new);
	public static RegistryObject<Item> spaceArrow = ITEMS.register("space_arrow", ModArrow::new);
	public static RegistryObject<Item> enderArrow = ITEMS.register("ender_arrow", ModArrow::new);
	public static RegistryObject<Item> fireArrow = ITEMS.register("fire_arrow", ModArrow::new);
	public static RegistryObject<Item> iceArrow = ITEMS.register("ice_arrow", ModArrow::new);
	public static RegistryObject<Item> amosiArrow = ITEMS.register("amosi_arrow", ModArrow::new);

	//盔甲
	public static RegistryObject<ArmorItem> rubyHead = ITEMS.register("ruby_head", () -> {
		return new OrdinaryArms(MyArmorMaterial.RUBY, EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> rubyChest = ITEMS.register("ruby_chest", () -> {
		return new OrdinaryArms(MyArmorMaterial.RUBY, EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> rubyLegs = ITEMS.register("ruby_legs", () -> {
		return new OrdinaryArms(MyArmorMaterial.RUBY, EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> rubyFeet = ITEMS.register("ruby_feet", () -> {
		return new OrdinaryArms(MyArmorMaterial.RUBY, EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> emeraldHead = ITEMS.register("emerald_head", () -> {
		return new OrdinaryArms(MyArmorMaterial.EMERALD, EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> emeraldChest = ITEMS.register("emerald_chest", () -> {
		return new OrdinaryArms(MyArmorMaterial.EMERALD, EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> emeraldLegs = ITEMS.register("emerald_legs", () -> {
		return new OrdinaryArms(MyArmorMaterial.EMERALD, EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> emeraldFeet = ITEMS.register("emerald_feet", () -> {
		return new OrdinaryArms(MyArmorMaterial.EMERALD, EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> spaceHead = ITEMS.register("space_head", () -> {
		return new SpaceArms(EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> spaceChest = ITEMS.register("space_chest", () -> {
		return new SpaceArms(EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> spaceLegs = ITEMS.register("space_legs", () -> {
		return new SpaceArms(EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> spaceFeet = ITEMS.register("space_feet", () -> {
		return new SpaceArms(EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> opHead = ITEMS.register("op_head", () -> {
		return new OpArms(EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> opChest = ITEMS.register("op_chest", () -> {
		return new OpArms(EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> opLegs = ITEMS.register("op_legs", () -> {
		return new OpArms(EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> opFeet = ITEMS.register("op_feet", () -> {
		return new OpArms(EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> dragonHead = ITEMS.register("dragon_head", () -> {
		return new DragonArms(EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> dragonChest = ITEMS.register("dragon_chest", () -> {
		return new DragonArms(EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> dragonLegs = ITEMS.register("dragon_legs", () -> {
		return new DragonArms(EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> dragonFeet = ITEMS.register("dragon_feet", () -> {
		return new DragonArms(EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> totemHead = ITEMS.register("totem_head", () -> {
		return new TotemArms(EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> totemChest = ITEMS.register("totem_chest", () -> {
		return new TotemArms(EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> totemLegs = ITEMS.register("totem_legs", () -> {
		return new TotemArms(EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> totemFeet = ITEMS.register("totem_feet", () -> {
		return new TotemArms(EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> glowstoneHead = ITEMS.register("glowstone_head", () -> {
		return new GlowstoneArms(EquipmentSlotType.HEAD);
	});
	public static RegistryObject<ArmorItem> glowstoneChest = ITEMS.register("glowstone_chest", () -> {
		return new GlowstoneArms(EquipmentSlotType.CHEST);
	});
	public static RegistryObject<ArmorItem> glowstoneLegs = ITEMS.register("glowstone_legs", () -> {
		return new GlowstoneArms(EquipmentSlotType.LEGS);
	});
	public static RegistryObject<ArmorItem> glowstoneFeet = ITEMS.register("glowstone_feet", () -> {
		return new GlowstoneArms(EquipmentSlotType.FEET);
	});
	public static RegistryObject<ArmorItem> slimeFeet = ITEMS.register("slime_feet", () -> {
		return new SlimeArms(EquipmentSlotType.FEET);
	});
	
	
	//注册方块物品
	public static RegistryObject<BlockItem> rubyOre = ITEMS.register("ruby_ore", () ->{
		return new BlockItem(BlockRegistry.rubyOre.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> rubyBlock = ITEMS.register("ruby_block", () ->{
		return new BlockItem(BlockRegistry.rubyBlock.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> emeraldIngotBlock = ITEMS.register("emerald_ingot_block", () ->{
		return new BlockItem(BlockRegistry.emeraldIngotBlock.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> emeraldIngotOre = ITEMS.register("emerald_ingot_ore", () ->{
		return new BlockItem(BlockRegistry.emeraldIngotOre.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> spaceBlock = ITEMS.register("space_block", () ->{
		return new BlockItem(BlockRegistry.spaceBlock.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> spaceOre = ITEMS.register("space_ore", () ->{
		return new BlockItem(BlockRegistry.spaceOre.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> tree = ITEMS.register("tree", () ->{
		return new BlockItem(BlockRegistry.tree.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> fragileBedrock = ITEMS.register("fragile_bedrock", () ->{
		return new BlockItem(BlockRegistry.fragileBedrock.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> dragonBlock = ITEMS.register("dragon_block", () ->{
		return new BlockItem(BlockRegistry.dragonBlock.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> dragonOre = ITEMS.register("dragon_ore", () ->{
		return new BlockItem(BlockRegistry.dragonOre.get(), new Item.Properties().group(ModGroup.myGroup));
	});
	public static RegistryObject<BlockItem> endSpaceOre = ITEMS.register("end_space_ore", () ->{
		return new BlockItem(BlockRegistry.endSpaceOre.get(), new Item.Properties().group(ModGroup.myGroup));
	});
}
