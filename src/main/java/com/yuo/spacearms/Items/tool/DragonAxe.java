package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class DragonAxe extends AxeItem {

	public DragonAxe() {
		super(SAItemTiers.DRAGON, 6, -3.0f, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.dragon_tool"));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isRemote){
			ToolType toolType = state.getHarvestTool();
			if (entityLiving instanceof PlayerEntity && toolType == ToolType.AXE){
				PlayerEntity player = (PlayerEntity) entityLiving;
				ToolHelper.spawnExp(player, worldIn, stack, pos);
			}
		}
		return true;
	}
}
