package com.yuo.spacearms.Items.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.yuo.spacearms.Entity.Render.ShieldTileEntityRenderer;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShieldItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import javax.annotation.Nullable;

//盾牌
public class ModShield extends ShieldItem {

    private final ShieldType shieldType; //盾牌类型
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public ModShield(ShieldType type) {
        super(type.getProperties().setISTER(() -> ShieldTileEntityRenderer::new));
        this.shieldType = type;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, SpaceArms.MOD_ID + "shield_armor", type.getProtectionValue(), AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return stack.getItem() == SAItems.goldShield.get();
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityIn;
            ItemStack offhand = player.getHeldItemOffhand();
            if (offhand.getItem() == SAItems.obsidianShield.get()){
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 0));
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        return slot == EquipmentSlotType.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot, stack);
    }

    public ShieldType getShieldType() {
        return shieldType;
    }

    @Override
    public int getItemEnchantability() {
        return shieldType.getEnchantAbility();
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.shieldType.getRepairable().contains(repair.getItem()) || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }
}
