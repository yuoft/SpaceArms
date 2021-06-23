package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
//狼的末路
public class WolfSword extends SwordItem{
	//武器材料
	private static IItemTier iItemTier = new IItemTier() {
        @Override
        public int getMaxUses() {
            return 10000;
        }
        @Override
        public float getEfficiency() {
            return 20.0F;
        }
        @Override
        public float getAttackDamage() {
            return 600.0F;
        }
        @Override
        public int getHarvestLevel() {
            return 3;
        }
        @Override
        public int getEnchantability() {
            return 50;
        }
        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(Items.NETHER_STAR);
        }
    };

	public WolfSword() {
		super(iItemTier, 7, -2.4F, new Item.Properties().group(ModGroup.myGroup));
	}

}
