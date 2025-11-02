package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Consumer;

public class OpSword extends SwordItem {

	public OpSword() {
		super(SAItemTiers.OP, 0, -2.0F, new Item.Properties());
	}

//    @Override
//    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
//	    if (this.isInGroup(group)){ //防止添加到其它物品页
//            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
//            map.put(Enchantments.LOOTING, 10);
//            ItemStack stack = new ItemStack(this);
//            EnchantmentHelper.setEnchantments(map, stack);
//            items.add(stack);
//        }
//    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("spacearms.text.itemInfo.opSword",""));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (!worldIn.isClientSide) {
            attackAOE(playerIn, 16, 10000, playerIn.isCrouching());
            playerIn.getCooldowns().addCooldown(this, 20);
        }
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }

    //攻击实体

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            if (target instanceof EnderDragon dragon){
                dragon.hurt(dragon.head, attacker.damageSources().playerAttack(player), Integer.MAX_VALUE);
            } else if (target instanceof WitherBoss witherBoss){
                witherBoss.hurt(attacker.damageSources().playerAttack(player), Integer.MAX_VALUE);
            } else target.hurt(attacker.damageSources().playerAttack(player), Integer.MAX_VALUE);
        }else target.hurt(attacker.damageSources().generic(), Integer.MAX_VALUE);
        return super.hurtEnemy(stack, target, attacker);
    }

    //aoe伤害
    protected void attackAOE(Player player, float range, float damage, boolean type) {
        if (player.level().isClientSide) return;
        AABB aabb = player.getBoundingBox().inflate(range);//范围
        List<Entity> toAttack = player.level().getEntities(player, aabb);//生物列表
        DamageSource src = player.damageSources().generic();//伤害类型
        for (Entity entity : toAttack) { //循环遍历
            if (type) {
                if (entity instanceof LivingEntity) {
                    entity.hurt(src, damage);//给与实体伤害
                }
            } else {
                if (entity instanceof Mob mob) {
                    if (mob instanceof EnderDragon dragon){
                        dragon.hurt(dragon.head, player.damageSources().playerAttack(player), 10000.0f);
                    } else if (mob instanceof WitherBoss witherBoss){
                        witherBoss.hurt(player.damageSources().playerAttack(player), 10000.0f);
                    } else mob.hurt(player.damageSources().playerAttack(player), 10000.0f);
                }
            }
        }
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 1.0f);
    }
}
