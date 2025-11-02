package com.yuo.spacearms.Items.Arms;

import com.yuo.spacearms.Items.SAArmorMaterials;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.*;
import java.util.function.Consumer;

public class OpArms extends ArmorItem {

    private static final Properties properties = new Properties().stacksTo(1);
    public static AttributeModifier modifier = new AttributeModifier(UUID.fromString("63e94267-8e6d-781a-b573-462fd18c5a84"), SpaceArms.MOD_ID + ":movement_speed", 0.2, AttributeModifier.Operation.ADDITION);

    public OpArms(Type slot) {
        super(SAArmorMaterials.OP, slot, properties);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
        Item item = stack.getItem();
        if (item.equals(SAItems.opHead.get())) {
            components.add(Component.translatable("spacearms.text.itemInfo.op_head"));
        }
        if (item.equals(SAItems.opChest.get())) {
            components.add(Component.translatable("spacearms.text.itemInfo.op_chest"));
        }
        if (item.equals(SAItems.opLegs.get())) {
            components.add(Component.translatable("spacearms.text.itemInfo.op_legs"));
        }
        if (item.equals(SAItems.opFeet.get())) {
            components.add(Component.translatable("spacearms.text.itemInfo.op_feet"));
        }
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        Item item = stack.getItem();
        if (item.equals(SAItems.opHead.get())) {
            if (player.isInWater()) { //玩家视线在水中
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 0, 5));
            }
            player.getFoodData().eat(20, 20f); //饱腹
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0)); //夜视
        }
        if (item.equals(SAItems.opChest.get())) {
            //清除所有负面效果
            Collection<MobEffectInstance> effects = player.getActiveEffects();
            if (!effects.isEmpty()) {
                List<MobEffect> bad = new ArrayList<>();
                effects.forEach((e) -> {
                    if (!e.getEffect().isBeneficial())
                        bad.add(e.getEffect());
                });
                if (!bad.isEmpty()) {
                    bad.forEach(player::removeEffect);
                }
            }
        }
        if (item.equals(SAItems.opLegs.get())) {
            if (player.isOnFire()) player.extinguishFire();//着火时熄灭
            player.fireImmune(); //免疫火伤
        }
    }
}
