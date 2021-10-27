package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.AmosiArrowEntity;
import com.yuo.spacearms.Entity.EntityRegistry;
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

/**
 * 阿莫斯之弓
 */
public class AmosiBow extends BowItem {
    public static final Predicate<ItemStack> AMOSI_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.AMOSI_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public AmosiBow() {
        super(new Properties().maxDamage(384).group(ModGroup.myGroup));
    }

    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.getEntity() instanceof LivingEntity){
            return new AmosiArrowEntity(EntityRegistry.AMOSI_ARROW.get(), (LivingEntity) arrow.getEntity(), arrow.world);
        }
        return arrow;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return AMOSI_ARROWS;
    }
}
