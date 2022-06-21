package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.ShieldItem;

public class ModShield extends ShieldItem {

    public ModShield() {
        super(new Properties().group(ModGroup.spaceArms).maxDamage(10));
    }
}
