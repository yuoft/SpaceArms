package com.yuo.spacearms;

import com.yuo.endless.Endless;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * ResourceLocation类调用
 */
public class RlUtils {
    public static ResourceLocation fa(String path){
        return new ResourceLocation(path);
    }

    public static ResourceLocation fa(String namespace, String path){
        return new ResourceLocation(namespace, path);
    }
}
