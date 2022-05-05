package com.yuo.spacearms.Items;

import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Entity.GoldTntEntity;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GoldTnt extends Item{

	public GoldTnt() {
		super(new Properties().group(ModGroup.myGroup));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote){
			GoldTntEntity goldTntEntity = new GoldTntEntity(EntityRegistry.GOLD_TNT.get(), playerIn, worldIn);
			goldTntEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw,
					0, 2.0f,1.0f);
			worldIn.addEntity(goldTntEntity);
			stack.setCount(stack.getCount() - 1);
		}
		return ActionResult.resultSuccess(stack);
	}
}
