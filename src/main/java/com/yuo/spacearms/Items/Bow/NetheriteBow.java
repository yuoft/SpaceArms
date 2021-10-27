package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Entity.NetheriteArrowEntity;
import com.yuo.spacearms.Items.TagsRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class NetheriteBow extends BowItem {
    public static final Predicate<ItemStack> NETHERITE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.NETHERITE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public NetheriteBow() {
        super(new Properties().maxDamage(524).group(ModGroup.myGroup));
    }

    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.getEntity() instanceof LivingEntity){
            return new NetheriteArrowEntity(EntityRegistry.NETHERITE_ARROW.get(), (LivingEntity) arrow.getEntity(), arrow.world);
        }
        return arrow;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return NETHERITE_ARROWS;
    }
}
