package com.yuo.spacearms.Event;

import com.yuo.spacearms.Spacearms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 事件处理类
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MODID)
public class TickEvents {
    //检查玩家背包是否有基岩，有则给予负面状态
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        PlayerInventory inventory = player.inventory;
        NonNullList<ItemStack> mainInventory = inventory.mainInventory;
        mainInventory.forEach((e) ->{ setPlayerEffects(e, player); });
        NonNullList<ItemStack> offHandInventory = inventory.offHandInventory;
        offHandInventory.forEach( e ->{ setPlayerEffects(e, player); });
    }

    /**
     * 判断物品，设置玩家状态
     * @param e 物品
     * @param player 玩家
     */
    private static void setPlayerEffects(ItemStack e, PlayerEntity player){
        if (e.getItem().equals(Items.BEDROCK) && !player.isCreative()){ //如果玩家持有基岩，则给予负面状态
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 3));
            player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 2));
            player.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 0));
            player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 0));
        }
    }
}

