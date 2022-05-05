package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Spacearms;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//方块注册
public class BlockRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Spacearms.MODID);
    public static RegistryObject<Block> rubyOre = BLOCKS.register("ruby_ore",
            () -> new OrdinaryOre(Material.ROCK, 2, ToolType.PICKAXE, 5, 5));
    public static RegistryObject<Block> rubyBlock = BLOCKS.register("ruby_block",
            () -> new OrdinaryBlock((new Material.Builder(MaterialColor.RED)).build(), 2, ToolType.PICKAXE, 7, 7));
    public static RegistryObject<Block> emeraldIngotBlock = BLOCKS.register("emerald_ingot_block",
            () -> new OrdinaryBlock((new Material.Builder(MaterialColor.EMERALD)).build(), 2, ToolType.PICKAXE, 9, 9));
    public static RegistryObject<Block> emeraldIngotOre = BLOCKS.register("emerald_ingot_ore", EmeraldIngotOre::new);
    public static RegistryObject<Block> spaceBlock = BLOCKS.register("space_block", SpaceBlock::new);
    public static RegistryObject<Block> spaceOre = BLOCKS.register("space_ore", SpaceOre::new);
    public static RegistryObject<Block> tree = BLOCKS.register("tree",
            () -> new OrdinaryBlock(Material.WOOD, 2, ToolType.AXE, 3, 3));
    public static RegistryObject<Block> fragileBedrock = BLOCKS.register("fragile_bedrock", FraglieBedrock::new);
    public static RegistryObject<Block> dragonBlock = BLOCKS.register("dragon_block",
            () -> new OrdinaryBlock(Material.ROCK, 4, ToolType.PICKAXE, 30, 100));
    public static RegistryObject<Block> dragonOre = BLOCKS.register("dragon_ore", DragonOre::new);
    public static RegistryObject<Block> endSpaceOre = BLOCKS.register("end_space_ore", SpaceOre::new);
    public static RegistryObject<Block> xrayBlock = BLOCKS.register("xray_block",
            () -> new XrayBlock(3, ToolType.PICKAXE,10,30));
    public static RegistryObject<Block> superXrayBlock = BLOCKS.register("super_xray_block",
            () -> new XrayBlock(4, ToolType.PICKAXE,25,40));
    public static RegistryObject<Block> superOre = BLOCKS.register("super_ore",
            () -> new OrdinaryOre(Material.ROCK,3, ToolType.PICKAXE,15,60));
    public static RegistryObject<Block> superBlock = BLOCKS.register("super_block",
            () -> new OrdinaryBlock(Material.ROCK,3, ToolType.PICKAXE,16,70));
    public static RegistryObject<Block> ultraBlock = BLOCKS.register("ultra_block",
            () -> new OrdinaryBlock(Material.ROCK,4, ToolType.PICKAXE,30,80));
}
