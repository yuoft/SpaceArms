package com.yuo.spacearms.Event;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SpaceArms;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * 添加村民交易处理类
 */
@Mod.EventBusSubscriber(modid = SpaceArms.MOD_ID)
public class VillagerEvents {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        VillagerProfession type = event.getType();
        if (VillagerProfession.WEAPONSMITH.equals(type)){
            Int2ObjectMap<List<ItemListing>> trades = event.getTrades();
            trades.get(1).add(new EmeraldForItemsTrade(SAItems.rubyIngot.get(), 10, 16, 2));  //每次可交易次数，获取经验
            trades.get(2).add(new EmeraldForItemsTrade(SAItems.emeraldIngot.get(), 8, 12, 3));
            trades.get(3).add(new EmeraldForItemsTrade(SAItems.dragonCrystal.get(), 4, 8, 5));
            trades.get(4).add(new ItemsForEmeraldsAndItemsTrade(SAItems.spacePath.get(), 9,16,  SAItems.spaceIngot.get(), 1,6, 6));
            trades.get(5).add(new ItemsForEmeraldsAndItemsTrade(Items.NETHER_STAR, 1,64,  SAItems.spaceCore.get(), 1,5, 8));
        }
    }
    //物品换绿宝石
    static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
        private final Item tradeItem;
        private final int count;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(ItemLike tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
            this.tradeItem = tradeItemIn.asItem();
            this.count = countIn;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
            this.priceMultiplier = 0.05F;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }
    //物品加绿宝石换物品
    static class ItemsForEmeraldsAndItemsTrade implements VillagerTrades.ItemListing {
        private final ItemStack buyingItem;
        private final int buyingItemCount;
        private final int emeraldCount;
        private final ItemStack sellingItem;
        private final int sellingItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public ItemsForEmeraldsAndItemsTrade(ItemLike itemLike, int i, Item item, int i1, int i2, int i3) {
            this(itemLike, i, 1, item, i1, i2, i3);
        }

        public ItemsForEmeraldsAndItemsTrade(ItemLike itemLike, int i, int i1, Item item, int i2, int i3, int i4) {
            this.buyingItem = new ItemStack(itemLike);
            this.buyingItemCount = i;
            this.emeraldCount = i1;
            this.sellingItem = new ItemStack(item);
            this.sellingItemCount = i2;
            this.maxUses = i3;
            this.xpValue = i4;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource rand) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCount), new ItemStack(this.buyingItem.getItem(), this.buyingItemCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }
}

