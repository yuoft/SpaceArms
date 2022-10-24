package com.yuo.spacearms.Event;

import com.yuo.spacearms.Spacearms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

/**
 * 用户输入/快捷键
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyBindingEvent {

    private static boolean IS_KEY_C = false; //是否按住快捷键

    public static final KeyBinding MESSAGE_KEY = new KeyBinding("key.message.spacearms", //分类
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputMappings.Type.KEYSYM,
            67, //按键C
            "key.category.spacearms_c"); //按键描述

//    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (MESSAGE_KEY.isPressed() && Minecraft.getInstance().player != null) {
            if (IS_KEY_C){
                IS_KEY_C = false;
                Minecraft.getInstance().player.sendMessage(new TranslationTextComponent("spacearms.text.info.closeHp"), UUID.randomUUID());
            }else {
                IS_KEY_C = true;
                Minecraft.getInstance().player.sendMessage(new TranslationTextComponent("spacearms.text.info.openHp"), UUID.randomUUID());
            }
        }
    }

    public static boolean isIsKeyC() {
        return IS_KEY_C;
    }

    public static void setIsKeyC(boolean isKeyC) {
        IS_KEY_C = isKeyC;
    }
}
