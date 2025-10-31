package com.yuo.spacearms;

import com.yuo.endless.Endless;
import com.yuo.endless.Items.EndlessItems;
import com.yuo.spacearms.Items.SAItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

//创造模式物品栏 实例化
public class SATabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Endless.MOD_ID);
	public static final RegistryObject<CreativeModeTab> SA_TAB0 = TABS.register(Endless.MOD_ID + "_tab0", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.tab.SpaceArms0"))
			.icon(() -> SAItems.jade.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				for (RegistryObject<Item> entry : EndlessItems.ITEMS.getEntries()) {
					if (entry.get() instanceof DiggerItem || entry.get() instanceof ArmorItem || entry.get() instanceof TieredItem
							|| entry.get() instanceof ProjectileWeaponItem) {
					} else output.accept(new ItemStack(entry.get()));
				}
			}).build());
	public static final RegistryObject<CreativeModeTab> SA_TAB1 = TABS.register(Endless.MOD_ID + "_tab1", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.tab.SpaceArms1"))
			.icon(() -> SAItems.opSword.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				for (RegistryObject<Item> entry : EndlessItems.ITEMS.getEntries()) {
					if (entry.get() instanceof DiggerItem || entry.get() instanceof ArmorItem || entry.get() instanceof TieredItem
							|| entry.get() instanceof ProjectileWeaponItem) {
						output.accept(new ItemStack(entry.get()));
					}
				}
			}).build());
}
