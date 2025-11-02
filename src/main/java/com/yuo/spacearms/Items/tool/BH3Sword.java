package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Effect.EffectRegistry;
import com.yuo.spacearms.Items.SAItemTiers;
import com.yuo.spacearms.Items.SAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BH3Sword extends SwordItem {
    public BH3Sword() {
        super(SAItemTiers.BH3, 4, -2.4f, new Properties().fireResistant());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (playerIn.isCrouching() && (playerIn.experienceLevel > 10 || playerIn.isCreative())) { // 潜行使用
            playerIn.startUsingItem(handIn);
            finishUsingItem(itemstack, worldIn, playerIn);
            return InteractionResultHolder.consume(itemstack);
        }else {
            playerIn.sendSystemMessage(Component.translatable("spacearms.text.info.bh3_use"));
            return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Item item = stack.getItem();
        if (entityLiving instanceof Player player){
            AABB aabb = player.getBoundingBox().inflate(8);
            List<Entity> entityList = player.level().getEntities(player, aabb);
            DamageSource source = entityLiving.damageSources().generic();
            if (item == SAItems.hengshuang.get()){
                worldIn.playSound(player, player.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 3.0f, 1.0f);
                source = entityLiving.damageSources().magic();
            } else if (item == SAItems.tianhuo.get()){
                worldIn.playSound(player, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 3.0f, 1.0f);
                source = entityLiving.damageSources().inFire();
            }
            for (Entity entity : entityList) {
                if (entity instanceof LivingEntity && !(entity instanceof ArmorStand)) {
                    entity.hurt(source, 10);
                    BlockPos pos = entity.blockPosition();
                    if (item == SAItems.hengshuang.get()){
                        addParticle(worldIn, pos, ParticleTypes.ITEM_SNOWBALL);
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(EffectRegistry.frozen.get(), 3 * 20, 0));
                    } else if (item == SAItems.tianhuo.get()){
                        addParticle(worldIn, pos, ParticleTypes.LAVA);
                        entity.setSecondsOnFire(10);
                    }
                }
            }
            if (!player.isCreative()){
                player.giveExperienceLevels(-10);
                stack.hurtAndBreak(9, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
            }
            player.getCooldowns().addCooldown(item, 20 * 30);
        }

        return stack;
    }

    /**
     * 粒子生成
     * @param world d
     * @param pos 玩家坐标
     */
    private void addParticle(Level world, BlockPos pos, ParticleOptions particle){
        for (int i = 0; i < 20; i++){
            world.addParticle( particle,pos.getX() + world.random.nextGaussian(), pos.getY() + world.random.nextGaussian(), pos.getZ() + world.random.nextGaussian(),
                    0.05, 0.05, 0.05);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
        Item item = stack.getItem();
        if (item == SAItems.hengshuang.get()){
            components.add(Component.translatable("spacearms.text.itemInfo.hengshuang"));
            components.add(Component.translatable("spacearms.text.itemInfo.hengshuang1"));
        }
        if (item == SAItems.tianhuo.get()){
            components.add(Component.translatable("spacearms.text.itemInfo.tianhuo"));
            components.add(Component.translatable("spacearms.text.itemInfo.tianhuo1"));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Item item = stack.getItem();
        Level world = attacker.level();
        if (item == SAItems.hengshuang.get() && world.random.nextDouble() < 0.2){
            target.addEffect(new MobEffectInstance(EffectRegistry.frozen.get(), 3 * 20, 0));
        }
        if (item == SAItems.tianhuo.get() && world.random.nextDouble() < 0.2){
            target.setSecondsOnFire(10);
        }
        //击退
        target.knockback( 1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        return super.hurtEnemy(stack, target, attacker);
    }
}
