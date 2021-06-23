package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Spacearms;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.FileReader;

//方块注册
public class BlockRegistry {
	//同物品
	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Spacearms.MODID);//DeferredRegister.create(ForgeRegistries.BLOCKS, "spacearms");
    public static RegistryObject<Block> rubyOre = BLOCKS.register("ruby_ore", () -> {
        return new OrdinaryBlock(Material.ROCK, 2, ToolType.PICKAXE, 5, 5);
    });
    public static RegistryObject<Block> rubyBlock = BLOCKS.register("ruby_block", () -> {
        return new OrdinaryBlock(Material.ROCK, 2, ToolType.PICKAXE, 7, 7);
    });
    public static RegistryObject<Block> emeraldIngotBlock = BLOCKS.register("emerald_ingot_block", () -> {
        return new OrdinaryBlock(Material.ROCK, 2, ToolType.PICKAXE, 9, 9);
    });
    public static RegistryObject<Block> emeraldIngotOre = BLOCKS.register("emerald_ingot_ore", () -> {
    	return new EmeraldIngotOre();
    });
    public static RegistryObject<Block> spaceBlock = BLOCKS.register("space_block", () -> {
    	return new SpaceBlock();
    });
    public static RegistryObject<Block> spaceOre = BLOCKS.register("space_ore", () -> {
    	return new SpaceOre();
    });
    public static RegistryObject<Block> tree = BLOCKS.register("tree", () -> {
        return new OrdinaryBlock(Material.WOOD, 2, ToolType.AXE, 3, 3);
    });
    public static RegistryObject<Block> netheriteOre = BLOCKS.register("netherite_ore", () -> {
        return new NetheriteOre();
    });
    public static RegistryObject<Block> netheriteBlock = BLOCKS.register("netherite_block", () -> {
        return new NetheriteBlock(Material.ROCK, 3, ToolType.PICKAXE, 30, 100);
    });
    public static RegistryObject<Block> fragileBedrock = BLOCKS.register("fragile_bedrock", () -> {
        return new FraglieBedrock();
    });
    public static RegistryObject<Block> dragonBlock = BLOCKS.register("dragon_block", () -> {
        return new OrdinaryBlock(Material.ROCK, 4, ToolType.PICKAXE, 35, 100);
    });
    public static RegistryObject<Block> dragonOre = BLOCKS.register("dragon_ore", () -> {
        return new DragonOre();
    });
    public static RegistryObject<Block> endSpaceOre = BLOCKS.register("end_space_ore", () -> {
        return new SpaceOre();
    });
}
