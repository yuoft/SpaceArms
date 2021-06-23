package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DragonPickaxe extends PickaxeItem {

	public DragonPickaxe() {
		super(MyItemTier.DRAGON, 3, -2.4f, new Properties().group(ModGroup.myGroup));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.dragon_pickaxe"));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isRemote){
			if (entityLiving instanceof PlayerEntity){
				PlayerEntity player = (PlayerEntity) entityLiving;
				stack.damageItem(1, entityLiving, e -> entityLiving.sendBreakAnimation(Hand.MAIN_HAND));
				if (random.nextInt(100) > 50) return true; //50%额外概率掉落经验
				ExperienceOrbEntity exp = new ExperienceOrbEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), random.nextInt(5));
				worldIn.addEntity(exp);
				stack.damageItem(1, player, e -> player.sendBreakAnimation(Hand.MAIN_HAND));
			}
		}
		return true;
	}
}
