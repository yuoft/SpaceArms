package com.yuo.spacearms.Event;

import com.yuo.spacearms.Arms.NetheriteArms;
import com.yuo.spacearms.Arms.OpArms;
import com.yuo.spacearms.Blocks.BlockRegistry;
import com.yuo.spacearms.Blocks.NetheriteBlock;
import com.yuo.spacearms.Blocks.NetheriteOre;
import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.NetheriteItem;
import com.yuo.spacearms.Items.tool.BeheadSword;
import com.yuo.spacearms.Items.tool.OpPickaxe;
import com.yuo.spacearms.Items.tool.OpSword;
import com.yuo.spacearms.Items.tool.WolfSword;
import com.yuo.spacearms.Spacearms;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 事件处理类
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MODID)
public class EventHandler {
    //基岩镐右键基岩，将基岩变为脆弱基岩
    @SubscribeEvent
    public static void breakBedrock(PlayerInteractEvent.RightClickBlock event){
        World world = event.getWorld();
        Item item = event.getItemStack().getItem();
        BlockPos pos = event.getPos();
        if (item instanceof OpPickaxe && !world.isRemote){
            BlockState state = world.getBlockState(pos);
            if (pos.getY() == 1){
                PlayerEntity player = event.getPlayer();
                player.sendMessage(new TranslationTextComponent("spacearms.text.info.bedrock"));
                return;
            }
            if (state.getBlock().equals(Blocks.BEDROCK)){
                world.setBlockState(pos, BlockRegistry.fragileBedrock.get().getDefaultState());
            }
        }
    }
    //玩家合成基岩武器时添加附魔
    @SubscribeEvent
    public static void opTool(PlayerEvent.ItemCraftedEvent event){
        ItemStack stack = event.getCrafting();
        if (stack.getItem().equals(ItemRegistry.opSword.get())){
            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
            map.put(Enchantments.LOOTING, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
        if (stack.getItem().equals(ItemRegistry.opPickaxe.get())){
            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
            map.put(Enchantments.FORTUNE, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
    }
    //闪电击中基岩粉变成基岩锭
    @SubscribeEvent
    public static void lightningEntity(EntityStruckByLightningEvent event){
        Entity entity = event.getEntity();
        LightningBoltEntity lightning = event.getLightning();
        if (entity instanceof ItemEntity){
            ItemEntity itemEntity = (ItemEntity) entity;
            if (itemEntity.getItem().getItem().equals(ItemRegistry.bedrockPowder.get())){
                World world = itemEntity.world;
                BlockPos pos = itemEntity.getPosition();
                int count = itemEntity.getItem().getCount();
                Random random = new Random();
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), d0, d1, d2);
                if (!world.isRemote){
                    ItemStack stack = new ItemStack(ItemRegistry.bedrockIngot.get(), count);
                    itemEntity.setItem(stack);
                    world.addEntity(itemEntity);
                }
            }
        }
    }
    //不会被烧毁的物品
    @SubscribeEvent
    public static void entityItemUnDeath(ItemEvent event) { //物品实体事件
        ItemEntity entityItem = event.getEntityItem();
        Item item = entityItem.getItem().getItem();
        if(item instanceof OpSword || item instanceof OpArms || item instanceof WolfSword || item instanceof NetheriteItem
            || item instanceof NetheriteArms || Block.getBlockFromItem(item) instanceof NetheriteBlock || Block.getBlockFromItem(item)
        instanceof NetheriteOre || item.equals(ItemRegistry.netheriteSword.get())) {
            entityItem.setInvulnerable(true); // 设置物品实体不会死亡
        }
    }
    //铁砧配方
    @SubscribeEvent
    public static void NewRecipes(AnvilUpdateEvent event) {
        ItemStack stack=event.getLeft();
        ItemStack stack2=event.getRight();
        int count1=stack.getCount();
        int count2=stack2.getCount();
        if((stack.getItem().equals(Items.DIAMOND_SWORD) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
//            if(count1 <= count2)
//            {
                event.setCost(5);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteSword.get(), 1));
//            }
        }
        if((stack.getItem().equals(Items.DIAMOND_HELMET) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(5);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteHead.get(), 1));
            }
        }
        if((stack.getItem().equals(Items.DIAMOND_CHESTPLATE) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(10);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteChest.get(), 1));
            }
        }
        if((stack.getItem().equals(Items.DIAMOND_LEGGINGS) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(10);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteLegs.get(), 1));
            }
        }
        if((stack.getItem().equals(Items.DIAMOND_BOOTS) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(5);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteFeet.get(), 1));
            }
        }
        if((stack.getItem().equals(Items.DIAMOND_AXE) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(5);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheriteAxe.get(), 1));
            }
        }
        if((stack.getItem().equals(Items.DIAMOND_PICKAXE) && stack2.getItem().equals(ItemRegistry.netheriteIngot.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(5);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.netheritePickaxe.get(), 1));
            }
        }
        if((stack.getItem().equals(ItemRegistry.spacePickaxe.get()) && stack2.getItem().equals(ItemRegistry.spaceCore.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(30);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemRegistry.superSpacePickaxe.get(), 1));
            }
        }
    }

    //原版生物额外掉落
    @SubscribeEvent
    public static void enderDragonDrops(LivingDropsEvent event){
        LivingEntity entityLiving = event.getEntityLiving();
        World world = entityLiving.world;
        BlockPos pos = entityLiving.getPosition();
        Random random = new Random();
        Entity source = event.getSource().getTrueSource();
        if (entityLiving instanceof EnderDragonEntity){ //末影龙额外掉落 龙晶
            if (source instanceof PlayerEntity){ //伤害来源于玩家
                ItemStack stack = ((PlayerEntity) source).getHeldItemMainhand();
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack); //抢夺
                ItemStack stack1 = new ItemStack(ItemRegistry.dragonCrystal.get(), random.nextInt(4 + level) + 1);
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
                event.getDrops().add(itemEntity);
            }
        }
        if (entityLiving instanceof WitherSkeletonEntity){ //凋零骷髅额外掉落 凋零骷髅头 凋零骨
            if (source instanceof PlayerEntity){ //伤害来源于玩家
                ItemStack stack = ((PlayerEntity) source).getHeldItemMainhand();
                if (stack.getItem() instanceof BeheadSword){ //使用斩首大剑
                    int i = random.nextInt(2);
                    if (i > 0){ //掉落时额外耐久消耗
                        stack.damageItem(1,(PlayerEntity) source, e -> ((PlayerEntity) source).sendBreakAnimation(Hand.MAIN_HAND));
                        ItemStack stack1 = new ItemStack(Items.WITHER_SKELETON_SKULL, i);
                        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
                        event.getDrops().add(itemEntity);
                    }
                }
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack); //抢夺
                int j = random.nextInt(100);
                if ( j > (70 - level * 5)){
                    ItemStack stack1 = new ItemStack(ItemRegistry.witherBone.get(), random.nextInt(2 + level));
                    ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
                    event.getDrops().add(itemEntity);
                }
            }
        }
        if (entityLiving instanceof BlazeEntity){ //烈焰人额外掉落 烈焰骨
            if (source instanceof PlayerEntity){ //伤害来源于玩家
                ItemStack stack = ((PlayerEntity) source).getHeldItemMainhand();
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack); //抢夺
                int j = random.nextInt(100);
                if ( j > (70 - level * 5)){
                    ItemStack stack1 = new ItemStack(ItemRegistry.blazeBone.get(), random.nextInt(2 + level));
                    ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
                    event.getDrops().add(itemEntity);
                }
            }
        }
    }
}

