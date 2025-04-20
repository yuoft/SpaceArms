package com.yuo.spacearms.Arms;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SpaceArms;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class OpArms extends ArmorItem {

    private static final Properties properties = new Properties().maxStackSize(1).group(ModGroup.spaceArms);
    public static AttributeModifier modifier = new AttributeModifier(UUID.fromString("63e94267-8e6d-781a-b573-462fd18c5a84"), SpaceArms.MOD_ID + ":movement_speed", 0.2, AttributeModifier.Operation.ADDITION);

    public OpArms(EquipmentSlotType slot) {
        super(SAArmorMaterials.OP, slot, properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        Item item = stack.getItem();
        if (item.equals(SAItems.opHead.get())) {
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.op_head"));
        }
        if (item.equals(SAItems.opChest.get())) {
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.op_chest"));
        }
        if (item.equals(SAItems.opLegs.get())) {
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.op_legs"));
        }
        if (item.equals(SAItems.opFeet.get())) {
            tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.op_feet"));
        }
    }

    @Override
    public boolean isEnderMask(ItemStack stack, PlayerEntity player, EndermanEntity endermanEntity) {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        Item item = stack.getItem();
        if (item.equals(SAItems.opHead.get())) {
            if (player.areEyesInFluid(FluidTags.WATER)) { //玩家视线在水中
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 0, 5));
            }
            player.getFoodStats().addStats(20, 20f); //饱腹
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0)); //夜视
        }
        if (item.equals(SAItems.opChest.get())) {
            //清除所有负面效果
            Collection<EffectInstance> effects = player.getActivePotionEffects();
            if (effects.size() > 0) {
                List<Effect> bad = new ArrayList<>();
                effects.forEach((e) -> {
                    if (!e.getPotion().isBeneficial())
                        bad.add(e.getPotion());
                });
                if (bad.size() > 0) {
                    bad.forEach(player::removePotionEffect);
                }
            }
        }
        if (item.equals(SAItems.opLegs.get())) {
            if (player.isBurning()) player.extinguish();//着火时熄灭
            player.isImmuneToFire(); //免疫火伤
        }
    }

    @Override
    public int getDamageReduceAmount() {
        return 1000;
    }
}
