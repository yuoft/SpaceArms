package com.yuo.spacearms.Items.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.yuo.spacearms.Entity.Render.ShieldTileEntityRenderer;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

//盾牌
public class ModShield extends ShieldItem {

    private final ShieldType shieldType; //盾牌类型
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("841e293d-d6ae-46e1-880b-911933f851eb");

    public ModShield(ShieldType type) {
        super(new Properties().stacksTo(1).defaultDurability(123));
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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        if (entity instanceof Player player){
            ItemStack offhand = player.getOffhandItem();
            if (offhand.getItem() == SAItems.obsidianShield.get()){
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0));
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot, stack);
    }

    public ShieldType getShieldType() {
        return shieldType;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return shieldType.getEnchantAbility();
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(this.shieldType.getRepairable()) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }

    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }
}
