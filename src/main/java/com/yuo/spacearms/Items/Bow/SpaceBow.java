package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Entity.SpaceArrowEntity;
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
 * 空间弓
 */
public class SpaceBow extends BowItem {
    public static final Predicate<ItemStack> SPACE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.SPACE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public SpaceBow() {
        super(new Properties().maxDamage(684).group(ModGroup.myGroup));
    }

    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.getEntity() instanceof LivingEntity){
            return new SpaceArrowEntity(EntityRegistry.SPACE_ARROW.get(), (LivingEntity) arrow.getEntity(), arrow.world);
        }
        return arrow;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return SPACE_ARROWS;
    }
}
