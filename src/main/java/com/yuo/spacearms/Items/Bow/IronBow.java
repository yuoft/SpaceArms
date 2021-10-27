package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Entity.IronArrowEntity;
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

public class IronBow extends BowItem {
    public static final Predicate<ItemStack> IRON_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.IRON_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public IronBow() {
        super(new Properties().maxDamage(384).group(ModGroup.myGroup));
    }

    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.getEntity() instanceof LivingEntity){
            return new IronArrowEntity(EntityRegistry.IRON_ARROW.get(), (LivingEntity) arrow.getEntity(), arrow.world);
        }
        return arrow;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return IRON_ARROWS;
    }
}
