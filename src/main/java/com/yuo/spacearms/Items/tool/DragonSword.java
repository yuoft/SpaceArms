package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Entity.DragonCrystalEntity;
import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DragonSword extends SwordItem{
	public DragonSword() {
		super(MyItemTier.SPACE, 4, -2.4F, new Properties().group(ModGroup.myGroup));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.dragon_sword"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote){
			DragonCrystalEntity diamondEntity = new DragonCrystalEntity(EntityRegistry.DRAGON_CRYSTAL.get(), playerIn, worldIn);
			diamondEntity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw,
					0, 2.0f,1.0f);
			worldIn.addEntity(diamondEntity);
			stack.damageItem(5, playerIn, e -> playerIn.sendBreakAnimation(handIn));
			playerIn.getCooldownTracker().setCooldown(this, 60);
		}
		return ActionResult.resultSuccess(stack);
	}
}
