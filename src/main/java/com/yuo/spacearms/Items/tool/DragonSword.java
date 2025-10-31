package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Entity.DragonCrystal;
import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.SATabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DragonSword extends SwordItem {
	public DragonSword() {
		super(SAItemTiers.DRAGON, 4, -2.4F, new Properties());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.dragon_sword"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (!worldIn.isClientSide && !playerIn.isCrouching()){
			DragonCrystal diamondEntity = new DragonCrystal(SAEntitys.DRAGON_CRYSTAL.get(), playerIn, worldIn);
			diamondEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0, 2.0f,1.0f);
			worldIn.addFreshEntity(diamondEntity);
			stack.hurtAndBreak(5, playerIn, e -> playerIn.broadcastBreakEvent(handIn));
			playerIn.getCooldowns().addCooldown(this, 60);
		}
		return InteractionResultHolder.success(stack);
	}
}
