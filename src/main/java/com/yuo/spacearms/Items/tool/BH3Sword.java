package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Effect.EffectRegistry;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BH3Sword extends SwordItem {
    public BH3Sword() {
        super(MyItemTier.BH3, 4, -2.4f, new Properties().group(ModGroup.spaceArms).isImmuneToFire());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking() && (playerIn.experienceLevel > 30 || playerIn.isCreative())) { // 潜行使用
            playerIn.swingArm(handIn);
            onItemUseFinish(itemstack, worldIn, playerIn);
            return ActionResult.resultConsume(itemstack);
        }else {
            playerIn.sendMessage(new TranslationTextComponent("spacearms.text.info.bh3_use"), UUID.randomUUID());
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Item item = stack.getItem();
        if (entityLiving instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityLiving;
            AxisAlignedBB aabb = player.getBoundingBox().grow(8);
            List<Entity> entityList = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);
            DamageSource source = DamageSource.GENERIC;
            if (item == SAItems.hengshuang.get()){
                worldIn.playSound(player, player.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 3.0f, 1.0f);
                source = DamageSource.MAGIC;
            } else if (item == SAItems.tianhuo.get()){
                worldIn.playSound(player, player.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 3.0f, 1.0f);
                source = DamageSource.IN_FIRE;
            }
            for (Entity entity : entityList) {
                if (entity instanceof LivingEntity && !(entity instanceof ArmorStandEntity)) {
                    entity.attackEntityFrom(source, 10);
                    BlockPos pos = entity.getPosition();
                    if (item == SAItems.hengshuang.get()){
                        addParticle(worldIn, pos, ParticleTypes.ITEM_SNOWBALL);
                        ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectRegistry.frozen.get(), 3 * 20, 0));
                    } else if (item == SAItems.tianhuo.get()){
                        addParticle(worldIn, pos, ParticleTypes.LAVA);
                        entity.setFire(10);
                    }
                }
            }
            if (!player.isCreative()){
                player.addExperienceLevel(-30);
                stack.damageItem(9, player, e -> e.sendBreakAnimation(player.getActiveHand()));
            }
            player.getCooldownTracker().setCooldown(item, 20 * 60);
        }

        return stack;
    }

    /**
     * 粒子生成
     * @param world d
     * @param pos 玩家坐标
     */
    private void addParticle(World world, BlockPos pos, IParticleData particle){
        for (int i = 0; i < 20; i++){
            world.addOptionalParticle(particle,pos.getX() + world.rand.nextGaussian(), pos.getY() + world.rand.nextGaussian(), pos.getZ() + world.rand.nextGaussian(),
                    0.05, 0.05, 0.05);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        Item item = stack.getItem();
        if (item == SAItems.hengshuang.get()){
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.hengshuang"));
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.hengshuang1"));
        }
        if (item == SAItems.tianhuo.get()){
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.tianhuo"));
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.tianhuo1"));
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Item item = stack.getItem();
        World world = attacker.world;
        if (item == SAItems.hengshuang.get() && world.rand.nextDouble() < 0.2){
            target.addPotionEffect(new EffectInstance(EffectRegistry.frozen.get(), 3 * 20, 0));
        }
        if (item == SAItems.tianhuo.get() && world.rand.nextDouble() < 0.2){
            target.setFire(10);
        }
        //击退
        target.applyKnockback( 1F, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
        return super.hitEntity(stack, target, attacker);
    }
}
