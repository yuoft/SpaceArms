package com.yuo.spacearms;

import com.yuo.endless.Endless;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * ResourceLocation类调用
 */
public class RlUtils {
    public static ResourceLocation fa(String path){
        return ResourceLocation.withDefaultNamespace(path);
    }

    public static ResourceLocation fa(String namespace, String path){
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation tryParse(String s){
        return ResourceLocation.tryParse(s);
    }

    public static ResourceLocation parse(String s){
        return ResourceLocation.parse(s);
    }
}
