package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.SpaceArms;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//方块注册
public class SABlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceArms.MOD_ID);
    public static RegistryObject<Block> rubyOre = BLOCKS.register("ruby_ore",
            () -> new OrdinaryOre(MapColor.COLOR_RED, 3,5, 5, UniformInt.of(0, 0)));
    public static RegistryObject<Block> netherRubyOre = BLOCKS.register("nether_ruby_ore",
            () -> new OrdinaryOre(MapColor.COLOR_RED, 3,5, 7, UniformInt.of(0, 0)));
    public static RegistryObject<Block> rubyBlock = BLOCKS.register("ruby_block",
            () -> new OrdinaryBlock(MapColor.COLOR_RED, 3,7, 7));
    public static RegistryObject<Block> jadeBlock = BLOCKS.register("jade_block",
            () -> new OrdinaryBlock(MapColor.EMERALD, 3,9, 9));

    public static RegistryObject<Block> jadeOre = BLOCKS.register("jade_ore",
            () -> new OrdinaryOre(MapColor.EMERALD, 3,7, 7, UniformInt.of(3, 6)));
    public static RegistryObject<Block> spaceOre = BLOCKS.register("space_ore",
            () -> new OrdinaryOre(MapColor.COLOR_BLACK, 6,45, 100, UniformInt.of(5, 10)));
    public static RegistryObject<Block> endSpaceOre = BLOCKS.register("end_space_ore",
            () -> new OrdinaryOre(MapColor.COLOR_BLACK, 6,50, 100, UniformInt.of(7, 14)));
    public static RegistryObject<Block> deepslateSpaceOre = BLOCKS.register("deepslate_space_ore",
            () -> new OrdinaryOre(MapColor.COLOR_BLACK, 6,47, 105, UniformInt.of(5, 12)));
    public static RegistryObject<Block> spaceBlock = BLOCKS.register("space_block", SpaceBlock::new);
    public static RegistryObject<Block> fragileBedrock = BLOCKS.register("fragile_bedrock",
            () -> new OrdinaryBlock(MapColor.COLOR_BLACK, 9,999, 99999));
    public static RegistryObject<Block> dragonOre = BLOCKS.register("dragon_ore",
            () -> new OrdinaryOre(MapColor.COLOR_PURPLE, 4,30, 80, UniformInt.of(4, 8)));
    public static RegistryObject<Block> dragonBlock = BLOCKS.register("dragon_block",
            () -> new OrdinaryBlock(MapColor.COLOR_PURPLE, 4,35, 100));

    public static RegistryObject<Block> xrayBlock = BLOCKS.register("xray_block",
            () -> new XrayBlock(3,10,30));
    public static RegistryObject<Block> superXrayBlock = BLOCKS.register("super_xray_block",
            () -> new XrayBlock(4,25,40));
    public static RegistryObject<Block> superOre = BLOCKS.register("super_ore",
            () -> new OrdinaryOre(MapColor.STONE, 3,15,60, UniformInt.of(3, 7)));
    public static RegistryObject<Block> superBlock = BLOCKS.register("super_block",
            () -> new OrdinaryBlock(MapColor.COLOR_ORANGE, 3,16,70));
    public static RegistryObject<Block> ultraBlock = BLOCKS.register("ultra_block",
            () -> new OrdinaryBlock(MapColor.COLOR_ORANGE, 5,30,80));
}
