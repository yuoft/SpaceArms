package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpSword extends SwordItem{

	public OpSword() {
		super(MyItemTier.OP, 0, -2.0F, new Item.Properties().group(ModGroup.spaceArms));
	}

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
	    if (this.isInGroup(group)){ //防止添加到其它物品页
            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
            map.put(Enchantments.LOOTING, 10);
            ItemStack stack = new ItemStack(this);
            EnchantmentHelper.setEnchantments(map, stack);
            items.add(stack);
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.opSword",""));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            attackAOE(playerIn, 16, 10000, playerIn.isSneaking());
            playerIn.getCooldownTracker().setCooldown(this, 20);
        }
        return new ActionResult<>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

    //攻击实体
    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
	    if (target instanceof EnderDragonEntity){
            EnderDragonEntity drageon = (EnderDragonEntity) target; //攻击末影龙
            drageon.attackEntityPartFrom(drageon.dragonPartHead, DamageSource.causePlayerDamage((PlayerEntity) attacker), 10000);
        }else target.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) attacker), 10000.0f);
        return super.hitEntity(stack, target, attacker);
    }

    //aoe伤害
    protected void attackAOE(PlayerEntity player,float range, float damage,boolean type)
    {
        if (player.getEntityWorld().isRemote) return;
        AxisAlignedBB aabb = player.getBoundingBox().grow(range);//范围
        List<Entity> toAttack = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);//生物列表
        DamageSource src = DamageSource.GENERIC;//伤害类型
        for (Entity entity : toAttack) { //循环遍历
            if(type) {
                if(entity instanceof LivingEntity) {
                    entity.attackEntityFrom(src, damage);//给与实体伤害
                }
            }
            else {
                if (entity instanceof IMob) {
                    if (entity instanceof EnderDragonEntity){
                        EnderDragonEntity drageon = (EnderDragonEntity) entity;
                        drageon.attackEntityPartFrom(drageon.dragonPartHead, DamageSource.causePlayerDamage(player), 10000);
                    }else entity.attackEntityFrom(src, damage);
                }
            }
        }
        player.getEntityWorld().playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }
}
