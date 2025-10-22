package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.SpaceArms;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//方块注册
public class SABlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpaceArms.MOD_ID);
    public static RegistryObject<Block> rubyOre = BLOCKS.register("ruby_ore",
            () -> new OrdinaryOre(MapColor.STONE, 2, 5, 5, UniformInt.of(0, 0)));
    public static RegistryObject<Block> rubyBlock = BLOCKS.register("ruby_block",
            () -> new OrdinaryBlock(MapColor.COLOR_RED, 2,7, 7));
    public static RegistryObject<Block> emeraldIngotBlock = BLOCKS.register("jade_block",
            () -> new OrdinaryBlock(MapColor.EMERALD, 2,9, 9));

    public static RegistryObject<Block> emeraldIngotOre = BLOCKS.register("jade_ore",
            () -> new OrdinaryOre(MapColor.EMERALD, 2,7, 7, UniformInt.of(3, 6)));
    public static RegistryObject<Block> spaceOre = BLOCKS.register("space_ore",
            () -> new OrdinaryOre(MapColor.COLOR_BLACK, 5,45, 100, UniformInt.of(5, 10)));
    public static RegistryObject<Block> spaceBlock = BLOCKS.register("space_block", SpaceBlock::new);
    public static RegistryObject<Block> fragileBedrock = BLOCKS.register("fragile_bedrock",
            () -> new OrdinaryBlock(MapColor.COLOR_BLACK, 2,9, 9));
    public static RegistryObject<Block> dragonOre = BLOCKS.register("dragon_ore",
            () -> new OrdinaryOre(MapColor.COLOR_PURPLE, 4,30, 80, UniformInt.of(4, 8)));
    public static RegistryObject<Block> dragonBlock = BLOCKS.register("dragon_block",
            () -> new OrdinaryBlock(MapColor.COLOR_PURPLE, 5, 35, 100));
    public static RegistryObject<Block> endSpaceOre = BLOCKS.register("end_space_ore",
            () -> new OrdinaryOre(MapColor.COLOR_BLACK, 5,50, 100, UniformInt.of(6, 11)));

    public static RegistryObject<Block> xrayBlock = BLOCKS.register("xray_block",
            () -> new XrayBlock(3, 10,30));
    public static RegistryObject<Block> superXrayBlock = BLOCKS.register("super_xray_block",
            () -> new XrayBlock(4, 25,40));
    public static RegistryObject<Block> superOre = BLOCKS.register("super_ore",
            () -> new OrdinaryOre(MapColor.STONE,3, 15,60, UniformInt.of(3, 7)));
    public static RegistryObject<Block> superBlock = BLOCKS.register("super_block",
            () -> new OrdinaryBlock(MapColor.STONE,3,16,70));
    public static RegistryObject<Block> ultraBlock = BLOCKS.register("ultra_block",
            () -> new OrdinaryBlock(MapColor.STONE,4, 30,80));
}
