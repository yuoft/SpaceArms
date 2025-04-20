package com.yuo.spacearms.Items;

import com.yuo.spacearms.Arms.*;
import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Items.Bow.*;
import com.yuo.spacearms.Items.tool.*;
import com.yuo.spacearms.tab.ModGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SAItems {
	//创建注册器。ForgeRegistries.ITEMS代表了我们要注册的是物品，第二个参数填入的应该是你的modId。
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, com.yuo.spacearms.SpaceArms.MOD_ID);
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
	public static RegistryObject<Item> spaceString = ITEMS.register("space_string", OrdinaryItem::new);
	public static RegistryObject<Item> superString = ITEMS.register("super_string", OrdinaryItem::new);
	public static RegistryObject<Item> dragonString = ITEMS.register("dragon_string", OrdinaryItem::new);
	public static RegistryObject<Item> ironStick = ITEMS.register("iron_stick", OrdinaryItem::new);
	public static RegistryObject<Item> goldStick = ITEMS.register("gold_stick", OrdinaryItem::new);
	public static RegistryObject<Item> diamondStick = ITEMS.register("diamond_stick", OrdinaryItem::new);
	public static RegistryObject<Item> netheriteStick = ITEMS.register("netherite_stick", OrdinaryItem::new);
	public static RegistryObject<Item> emeraldPowder = ITEMS.register("emerald_powder", OrdinaryItem::new);
	public static RegistryObject<Item> complexPowder = ITEMS.register("complex_powder", OrdinaryItem::new);
	public static RegistryObject<Item> xrayIngot = ITEMS.register("xray_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> superXrayIngot = ITEMS.register("super_xray_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> superIngot = ITEMS.register("super_ingot", OrdinaryItem::new);
	public static RegistryObject<Item> ultraIngot = ITEMS.register("ultra_ingot", OrdinaryItem::new);

	//食物
	public static RegistryObject<Item> cookedSuperApple = ITEMS.register("cooked_super_apple",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO_LONG));
	public static RegistryObject<Item> cookedSuperBeef = ITEMS.register("cooked_super_beef",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> cookedSuperBeetroot = ITEMS.register("cooked_super_beetroot",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO_LONG));
	public static RegistryObject<Item> cookedSuperBread = ITEMS.register("cooked_super_bread",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO_LONG));
	public static RegistryObject<Item> cookedSuperCarrot = ITEMS.register("cooked_super_carrot",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO_LONG));
	public static RegistryObject<Item> cookedSuperChicken = ITEMS.register("cooked_super_chicken",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> cookedSuperCod = ITEMS.register("cooked_super_cod",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> cookedSuperPotato = ITEMS.register("cooked_super_potato",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO_LONG));
	public static RegistryObject<Item> cookedSuperMutton = ITEMS.register("cooked_super_mutton",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> cookedSuperPork = ITEMS.register("cooked_super_pork",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> cookedSuperRabbit = ITEMS.register("cooked_super_rabbit",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE_LONG));
	public static RegistryObject<Item> superApple = ITEMS.register("super_apple",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superBeef = ITEMS.register("super_beef",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superBeetroot = ITEMS.register("super_beetroot",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superBeetrootSoup = ITEMS.register("super_beetroot_soup",
			() -> new SuperFood(SuperFoodEffect.SUPER_THREE));
	public static RegistryObject<Item> superBread = ITEMS.register("super_bread",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superCarrot = ITEMS.register("super_carrot",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superChicken = ITEMS.register("super_chicken",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superCod = ITEMS.register("super_cod",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superCookie = ITEMS.register("super_cookie",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superMutton = ITEMS.register("super_mutton",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superPork = ITEMS.register("super_pork",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superPotato = ITEMS.register("super_potato",
			() -> new SuperFood(SuperFoodEffect.SUPER_TWO));
	public static RegistryObject<Item> superRabbit = ITEMS.register("super_rabbit",
			() -> new SuperFood(SuperFoodEffect.SUPER_ONE));
	public static RegistryObject<Item> superRabbitStew = ITEMS.register("super_rabbit_stew",
			() -> new SuperFood(SuperFoodEffect.SUPER_THREE));
	public static RegistryObject<Item> superPumpkinPie = ITEMS.register("super_pumpkin_pie",
			() -> new SuperFood(SuperFoodEffect.SUPER_THREE));

	//武器和工具
	public static RegistryObject<Item> wolfSword = ITEMS.register("wolf_sword", WolfSword::new);
	public static RegistryObject<Item> tianhuo = ITEMS.register("tianhuo", BH3Sword::new);
	public static RegistryObject<Item> hengshuang = ITEMS.register("hengshuang", BH3Sword::new);
	public static RegistryObject<Item> rubySword = ITEMS.register("ruby_sword", () -> new OrdinarySword(SAItemTiers.RUBY));
	public static RegistryObject<Item> rubyPickaxe = ITEMS.register("ruby_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.RUBY));
	public static RegistryObject<Item> rubyAxe = ITEMS.register("ruby_axe", () -> new OrdinaryAxe(SAItemTiers.RUBY));
	public static RegistryObject<Item> rubyHoe = ITEMS.register("ruby_hoe", () -> new OrdinaryHoe(SAItemTiers.RUBY));
	public static RegistryObject<Item> rubyShovel = ITEMS.register("ruby_shovel", () -> new OrdinaryShovel(SAItemTiers.RUBY));
	public static RegistryObject<Item> emeraldSword = ITEMS.register("emerald_sword", () -> new OrdinarySword(SAItemTiers.EMERALD));
	public static RegistryObject<Item> emeraldPickaxe = ITEMS.register("emerald_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.EMERALD));
	public static RegistryObject<Item> emeraldAxe = ITEMS.register("emerald_axe", () -> new OrdinaryAxe(SAItemTiers.EMERALD));
	public static RegistryObject<Item> emeraldHoe = ITEMS.register("emerald_hoe", () -> new OrdinaryHoe(SAItemTiers.EMERALD));
	public static RegistryObject<Item> emeraldShovel = ITEMS.register("emerald_shovel", () -> new OrdinaryShovel(SAItemTiers.EMERALD));
	public static RegistryObject<Item> spaceSword = ITEMS.register("space_sword", SpaceSword::new);
	public static RegistryObject<Item> spacePickaxe = ITEMS.register("space_pickaxe", SpacePickaxe::new);
	public static RegistryObject<Item> spaceAxe = ITEMS.register("space_axe", SpaceAxe::new);
	public static RegistryObject<Item> spaceHoe = ITEMS.register("space_hoe", SpaceHoe::new);
	public static RegistryObject<Item> spaceShovel = ITEMS.register("space_shovel", SpaceShovel::new);
	public static RegistryObject<Item> superSpacePickaxe = ITEMS.register("super_space_pickaxe", SuperSpacePickaxe::new);
	public static RegistryObject<Item> opSword = ITEMS.register("op_sword", OpSword::new);
	public static RegistryObject<Item> opPickaxe = ITEMS.register("op_pickaxe", OpPickaxe::new);
	public static RegistryObject<Item> dragonSword = ITEMS.register("dragon_sword", DragonSword::new);
	public static RegistryObject<Item> dragonPickaxe = ITEMS.register("dragon_pickaxe", DragonPickaxe::new);
	public static RegistryObject<Item> dragonAxe = ITEMS.register("dragon_axe", DragonAxe::new);
	public static RegistryObject<Item> dragonHoe = ITEMS.register("dragon_hoe", DragonHoe::new);
	public static RegistryObject<Item> dragonShovel = ITEMS.register("dragon_shovel", DragonShovel::new);
	public static RegistryObject<Item> beheadSword = ITEMS.register("behead_sword", BeheadSword::new);
	public static RegistryObject<Item> totemSword = ITEMS.register("totem_sword", () -> new OrdinarySword(ItemTier.DIAMOND));
	public static RegistryObject<Item> glowstoneSword = ITEMS.register("glowstone_sword", () -> new OrdinarySword(ItemTier.IRON));

	public static RegistryObject<Item> xraySword = ITEMS.register("xray_sword", () -> new OrdinarySword(SAItemTiers.XRAY));
	public static RegistryObject<Item> xrayPickaxe = ITEMS.register("xray_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.XRAY));
	public static RegistryObject<Item> xrayAxe = ITEMS.register("xray_axe", () -> new OrdinaryAxe(SAItemTiers.XRAY));
	public static RegistryObject<Item> xrayHoe = ITEMS.register("xray_hoe", () -> new OrdinaryHoe(SAItemTiers.XRAY));
	public static RegistryObject<Item> xrayShovel = ITEMS.register("xray_shovel", () -> new OrdinaryShovel(SAItemTiers.XRAY));
	public static RegistryObject<Item> superXraySword = ITEMS.register("super_xray_sword", () -> new OrdinarySword(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superXrayPickaxe = ITEMS.register("super_xray_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superXrayAxe = ITEMS.register("super_xray_axe", () -> new OrdinaryAxe(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superXrayHoe = ITEMS.register("super_xray_hoe", () -> new OrdinaryHoe(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superXrayShovel = ITEMS.register("super_xray_shovel", () -> new OrdinaryShovel(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superSword = ITEMS.register("super_sword", () -> new OrdinarySword(SAItemTiers.SUPER));
	public static RegistryObject<Item> superPickaxe = ITEMS.register("super_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.SUPER));
	public static RegistryObject<Item> superAxe = ITEMS.register("super_axe", () -> new OrdinaryAxe(SAItemTiers.SUPER));
	public static RegistryObject<Item> superHoe = ITEMS.register("super_hoe", () -> new OrdinaryHoe(SAItemTiers.SUPER));
	public static RegistryObject<Item> superShovel = ITEMS.register("super_shovel", () -> new OrdinaryShovel(SAItemTiers.SUPER));
	public static RegistryObject<Item> ultraSword = ITEMS.register("ultra_sword", () -> new OrdinarySword(SAItemTiers.ULTRA));
	public static RegistryObject<Item> ultraPickaxe = ITEMS.register("ultra_pickaxe", () -> new OrdinaryPickaxe(SAItemTiers.ULTRA));
	public static RegistryObject<Item> ultraAxe = ITEMS.register("ultra_axe", () -> new OrdinaryAxe(SAItemTiers.ULTRA));
	public static RegistryObject<Item> ultraHoe = ITEMS.register("ultra_hoe", () -> new OrdinaryHoe(SAItemTiers.ULTRA));
	public static RegistryObject<Item> ultraShovel = ITEMS.register("ultra_shovel", () -> new OrdinaryShovel(SAItemTiers.ULTRA));

	public static RegistryObject<Item> woodComplexTool = ITEMS.register("wood_complex_tool", () -> new ComplexTool(ItemTier.WOOD));
	public static RegistryObject<Item> stoneComplexTool = ITEMS.register("stone_complex_tool", () -> new ComplexTool(ItemTier.STONE));
	public static RegistryObject<Item> ironComplexTool = ITEMS.register("iron_complex_tool", () -> new ComplexTool(ItemTier.IRON));
	public static RegistryObject<Item> goldComplexTool = ITEMS.register("gold_complex_tool", () -> new ComplexTool(ItemTier.GOLD));
	public static RegistryObject<Item> diamondComplexTool = ITEMS.register("diamond_complex_tool", () -> new ComplexTool(ItemTier.DIAMOND));
	public static RegistryObject<Item> netheriteComplexTool = ITEMS.register("netherite_complex_tool", () -> new ComplexTool(ItemTier.NETHERITE));
	public static RegistryObject<Item> emeraldComplexTool = ITEMS.register("emerald_complex_tool", () -> new ComplexTool(SAItemTiers.EMERALD));
	public static RegistryObject<Item> rubyComplexTool = ITEMS.register("ruby_complex_tool", () -> new ComplexTool(SAItemTiers.RUBY));
	public static RegistryObject<Item> dragonComplexTool = ITEMS.register("dragon_complex_tool", () -> new ComplexTool(SAItemTiers.DRAGON));
	public static RegistryObject<Item> xrayComplexTool = ITEMS.register("xray_complex_tool", () -> new ComplexTool(SAItemTiers.XRAY));
	public static RegistryObject<Item> superXrayComplexTool = ITEMS.register("super_xray_complex_tool", () -> new ComplexTool(SAItemTiers.SUPER_XRAY));
	public static RegistryObject<Item> superComplexTool = ITEMS.register("super_complex_tool", () -> new ComplexTool(SAItemTiers.SUPER));
	public static RegistryObject<Item> ultraComplexTool = ITEMS.register("ultra_complex_tool", () -> new ComplexTool(SAItemTiers.ULTRA));

	public static RegistryObject<Item> ironShield = ITEMS.register("iron_shield", () -> new ModShield(ShieldType.IRON));
	public static RegistryObject<Item> goldShield = ITEMS.register("gold_shield", () -> new ModShield(ShieldType.GOLD));
	public static RegistryObject<Item> diamondShield = ITEMS.register("diamond_shield", () -> new ModShield(ShieldType.DIAMOND));
	public static RegistryObject<Item> netheriteShield = ITEMS.register("netherite_shield", () -> new ModShield(ShieldType.NETHERITE));
	public static RegistryObject<Item> obsidianShield = ITEMS.register("obsidian_shield", () -> new ModShield(ShieldType.OBSIDIAN));

	//弓 箭
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
	public static RegistryObject<BowItem> tntBow = ITEMS.register("tnt_bow", TntBow::new);

	//盔甲
	public static RegistryObject<ArmorItem> rubyHead = ITEMS.register("ruby_head", () -> new OrdinaryArms(SAArmorMaterials.RUBY, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> rubyChest = ITEMS.register("ruby_chest", () -> new OrdinaryArms(SAArmorMaterials.RUBY, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> rubyLegs = ITEMS.register("ruby_legs", () -> new OrdinaryArms(SAArmorMaterials.RUBY, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> rubyFeet = ITEMS.register("ruby_feet", () -> new OrdinaryArms(SAArmorMaterials.RUBY, EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> emeraldHead = ITEMS.register("emerald_head", () -> new OrdinaryArms(SAArmorMaterials.EMERALD, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> emeraldChest = ITEMS.register("emerald_chest", () -> new OrdinaryArms(SAArmorMaterials.EMERALD, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> emeraldLegs = ITEMS.register("emerald_legs", () -> new OrdinaryArms(SAArmorMaterials.EMERALD, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> emeraldFeet = ITEMS.register("emerald_feet", () -> new OrdinaryArms(SAArmorMaterials.EMERALD, EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> spaceHead = ITEMS.register("space_head", () -> new com.yuo.spacearms.Arms.SpaceArms(EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> spaceChest = ITEMS.register("space_chest", () -> new com.yuo.spacearms.Arms.SpaceArms(EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> spaceLegs = ITEMS.register("space_legs", () -> new com.yuo.spacearms.Arms.SpaceArms(EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> spaceFeet = ITEMS.register("space_feet", () -> new com.yuo.spacearms.Arms.SpaceArms(EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> opHead = ITEMS.register("op_head", () -> new OpArms(EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> opChest = ITEMS.register("op_chest", () -> new OpArms(EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> opLegs = ITEMS.register("op_legs", () -> new OpArms(EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> opFeet = ITEMS.register("op_feet", () -> new OpArms(EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> dragonHead = ITEMS.register("dragon_head", () -> new DragonArms(EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> dragonChest = ITEMS.register("dragon_chest", () -> new DragonArms(EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> dragonLegs = ITEMS.register("dragon_legs", () -> new DragonArms(EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> dragonFeet = ITEMS.register("dragon_feet", () -> new DragonArms(EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> totemHead = ITEMS.register("totem_head", () -> new TotemArms(EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> totemChest = ITEMS.register("totem_chest", () -> new TotemArms(EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> totemLegs = ITEMS.register("totem_legs", () -> new TotemArms(EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> totemFeet = ITEMS.register("totem_feet", () -> new TotemArms(EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> glowstoneHead = ITEMS.register("glowstone_head", () -> new GlowstoneArms(EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> glowstoneChest = ITEMS.register("glowstone_chest", () -> new GlowstoneArms(EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> glowstoneLegs = ITEMS.register("glowstone_legs", () -> new GlowstoneArms(EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> glowstoneFeet = ITEMS.register("glowstone_feet", () -> new GlowstoneArms(EquipmentSlotType.FEET));

	public static RegistryObject<ArmorItem> xrayHead = ITEMS.register("xray_head", () -> new OrdinaryArms(SAArmorMaterials.XRAY, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> xrayChest = ITEMS.register("xray_chest", () -> new OrdinaryArms(SAArmorMaterials.XRAY, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> xrayLegs = ITEMS.register("xray_legs", () -> new OrdinaryArms(SAArmorMaterials.XRAY, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> xrayFeet = ITEMS.register("xray_feet", () -> new OrdinaryArms(SAArmorMaterials.XRAY, EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> superXrayHead = ITEMS.register("super_xray_head", () -> new OrdinaryArms(SAArmorMaterials.SUPER_XRAY, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> superXrayChest = ITEMS.register("super_xray_chest", () -> new OrdinaryArms(SAArmorMaterials.SUPER_XRAY, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> superXrayLegs = ITEMS.register("super_xray_legs", () -> new OrdinaryArms(SAArmorMaterials.SUPER_XRAY, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> superXrayFeet = ITEMS.register("super_xray_feet", () -> new OrdinaryArms(SAArmorMaterials.SUPER_XRAY, EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> superHead = ITEMS.register("super_head", () -> new OrdinaryArms(SAArmorMaterials.SUPER, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> superChest = ITEMS.register("super_chest", () -> new OrdinaryArms(SAArmorMaterials.SUPER, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> superLegs = ITEMS.register("super_legs", () -> new OrdinaryArms(SAArmorMaterials.SUPER, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> superFeet = ITEMS.register("super_feet", () -> new OrdinaryArms(SAArmorMaterials.SUPER, EquipmentSlotType.FEET));
	public static RegistryObject<ArmorItem> ultraHead = ITEMS.register("ultra_head", () -> new OrdinaryArms(SAArmorMaterials.ULTRA, EquipmentSlotType.HEAD));
	public static RegistryObject<ArmorItem> ultraChest = ITEMS.register("ultra_chest", () -> new OrdinaryArms(SAArmorMaterials.ULTRA, EquipmentSlotType.CHEST));
	public static RegistryObject<ArmorItem> ultraLegs = ITEMS.register("ultra_legs", () -> new OrdinaryArms(SAArmorMaterials.ULTRA, EquipmentSlotType.LEGS));
	public static RegistryObject<ArmorItem> ultraFeet = ITEMS.register("ultra_feet", () -> new OrdinaryArms(SAArmorMaterials.ULTRA, EquipmentSlotType.FEET));

	
	//注册方块物品
	public static RegistryObject<BlockItem> rubyOre = ITEMS.register("ruby_ore",
			() -> new SABlockItem(SABlocks.rubyOre.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> rubyBlock = ITEMS.register("ruby_block",
			() -> new BlockItem(SABlocks.rubyBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> emeraldIngotBlock = ITEMS.register("emerald_ingot_block",
			() -> new BlockItem(SABlocks.emeraldIngotBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> emeraldIngotOre = ITEMS.register("emerald_ingot_ore",
			() -> new BlockItem(SABlocks.emeraldIngotOre.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> spaceBlock = ITEMS.register("space_block",
			() -> new BlockItem(SABlocks.spaceBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> spaceOre = ITEMS.register("space_ore",
			() -> new BlockItem(SABlocks.spaceOre.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> fragileBedrock = ITEMS.register("fragile_bedrock",
			() -> new BlockItem(SABlocks.fragileBedrock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> dragonBlock = ITEMS.register("dragon_block",
			() -> new BlockItem(SABlocks.dragonBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> dragonOre = ITEMS.register("dragon_ore",
			() -> new BlockItem(SABlocks.dragonOre.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> endSpaceOre = ITEMS.register("end_space_ore",
			() -> new BlockItem(SABlocks.endSpaceOre.get(), new Item.Properties().group(ModGroup.spaceArms)));

	public static RegistryObject<BlockItem> xrayBlock = ITEMS.register("xray_block",
			() -> new BlockItem(SABlocks.xrayBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> superXrayBlock = ITEMS.register("super_xray_block",
			() -> new BlockItem(SABlocks.superXrayBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> superOre = ITEMS.register("super_ore",
			() -> new BlockItem(SABlocks.superOre.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> superBlock = ITEMS.register("super_block",
			() -> new BlockItem(SABlocks.superBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
	public static RegistryObject<BlockItem> ultraBlock = ITEMS.register("ultra_block",
			() -> new BlockItem(SABlocks.ultraBlock.get(), new Item.Properties().group(ModGroup.spaceArms)));
}
