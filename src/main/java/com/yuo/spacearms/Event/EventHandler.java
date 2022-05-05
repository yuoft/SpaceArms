package com.yuo.spacearms.Event;

import com.yuo.spacearms.Arms.OpArms;
import com.yuo.spacearms.Blocks.BlockRegistry;
import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.NetheriteItem;
import com.yuo.spacearms.Items.tool.BeheadSword;
import com.yuo.spacearms.Items.tool.OpPickaxe;
import com.yuo.spacearms.Items.tool.OpSword;
import com.yuo.spacearms.Items.tool.WolfSword;
import com.yuo.spacearms.Spacearms;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

/**
 * 事件处理类
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MODID)
public class EventHandler {
    public static List<String> playersWithOpHead = new ArrayList<>();
    public static List<String> playersWithOpChest = new ArrayList<>();
    public static List<String> playersWithOpLeg = new ArrayList<>();
    public static List<String> playersWithOpFeet = new ArrayList<>();

    //op鞋子 无摔落伤害
    @SubscribeEvent
    public static void playerFall(LivingFallEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)living;
            String key = player.getGameProfile().getName()+":"+player.world.isRemote;
            if (playersWithOpFeet.contains(key)) {
                event.setCanceled(true);
            }
            //史莱姆鞋子 弹出玩家
            ItemStack slimeFeet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
            if (!slimeFeet.isEmpty() && !player.isCrouching() && slimeFeet.getItem().equals(ItemRegistry.slimeFeet.get())
                    && event.getDistance() > 2){ //玩家未蹲着， 摔落距离大于2格
                event.setDamageMultiplier(0); //设置摔落伤害为0
                player.fallDistance =  0.0F; //摔落距离为0

                if (player.world.isRemote) {
                    player.setMotion(player.getMotion().x, player.getMotion().y * -0.9, player.getMotion().z);
                    player.isAirBorne = true;
                    player.setOnGround(false);
                    double f = 0.91d + 0.04d;
                    // 弹跳时减速一半
                    player.setMotion(player.getMotion().x / f, player.getMotion().y, player.getMotion().z / f);
                } else {
                    event.setCanceled(true);
                }

                player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1f, 1f);
                TickEvents.addBounceHandler(player, player.getMotion().y);
            }
        }
    }
    //op装备 不受伤害
    @SubscribeEvent
    public static void opArmsImmuneDamage(LivingDamageEvent event){
        LivingEntity entityLiving = event.getEntityLiving();
        if (entityLiving instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityLiving;
            Boolean hasChest = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemRegistry.opChest.get();
            Boolean hasLeg = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemRegistry.opLegs.get();
            Boolean hasHead = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemRegistry.opHead.get();
            Boolean hasFeet = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemRegistry.opFeet.get();
            if (hasChest && hasFeet && hasHead && hasLeg){
                event.setAmount(0);
                return;
            }
            if (hasChest || hasFeet || hasHead || hasLeg){
                event.setAmount(0.1f);
            }
        }

    }
    //op胸甲 飞行 护腿 行走速度增加
    @SubscribeEvent
    public static void updatePlayerAbilityStatus(LivingEvent.LivingUpdateEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) living;
            boolean hasChest = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemRegistry.opChest.get();
            boolean hasLeg = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemRegistry.opLegs.get();
            boolean hasHead = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemRegistry.opHead.get();
            boolean hasFoot = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemRegistry.opFeet.get();
            //防止其它模组飞行装备无法使用
            String key = player.getGameProfile().getName()+":"+player.world.isRemote;
            //head
            if (playersWithOpHead.contains(key)) {
                if (hasHead) {

                } else {
                    playersWithOpHead.remove(key);
                }
            } else if (hasHead) {
                playersWithOpHead.add(key);
            }
            //chest
            if (playersWithOpChest.contains(key)) {
                if (hasChest) {
                    player.abilities.allowFlying = true;
                }else {
                    if (!player.isCreative()) {
                        player.abilities.allowFlying = false;
                        player.abilities.isFlying = false;
                    }
                    playersWithOpChest.remove(key);
                }
            }else if (hasChest) {
                playersWithOpChest.add(key);
            }
            //leg
            if (playersWithOpLeg.contains(key)) {
                ModifiableAttributeInstance attribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
                if (hasLeg && attribute != null) {
                    boolean hasModifier = player.getAttributeManager().hasModifier(Attributes.MOVEMENT_SPEED, OpArms.modifier.getID());
                    if (!hasModifier)
                        attribute.applyNonPersistentModifier(OpArms.modifier); //行走速度
                }else {
                    if (attribute != null)
                        attribute.removeModifier(OpArms.modifier);
                    playersWithOpLeg.remove(key);
                }
            }else if (hasLeg) {
                playersWithOpLeg.add(key);
            }
            //feet
            if (playersWithOpFeet.contains(key)) {
                if (hasFoot) {
                } else {
                    playersWithOpFeet.remove(key);
                }
            } else if (hasFoot) {
                playersWithOpFeet.add(key);
            }
        }
    }
    //op护腿 增加跳跃高度
    @SubscribeEvent
    public static void jumpBoost(LivingEvent.LivingJumpEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)living;
            String key = player.getGameProfile().getName()+":"+player.world.isRemote;
            if (playersWithOpFeet.contains(key)) {
                player.setMotion(0, 1.0f, 0);
                return;
            }
            if (TickEvents.isIS_BEDROCK()){
                player.setMotion(0, 0.05f, 0);
            }
        }
    }
    //基岩镐右键基岩，将基岩变为脆弱基岩
    @SubscribeEvent
    public static void breakBedrock(PlayerInteractEvent.RightClickBlock event){
        World world = event.getWorld();
        Item item = event.getItemStack().getItem();
        BlockPos pos = event.getPos();
        if (item instanceof OpPickaxe && !world.isRemote){
            BlockState state = world.getBlockState(pos);
            if (pos.getY() == 0){ //0是最下层基岩
                PlayerEntity player = event.getPlayer();
                player.sendMessage(new TranslationTextComponent("spacearms.text.info.bedrock"), UUID.randomUUID());
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
            Map<Enchantment, Integer> map = new HashMap<>();
            map.put(Enchantments.LOOTING, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
        if (stack.getItem().equals(ItemRegistry.opPickaxe.get())){
            Map<Enchantment, Integer> map = new HashMap<>();
            map.put(Enchantments.FORTUNE, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
    }
    //闪电击中基岩粉变成基岩锭
    @SubscribeEvent
    public static void lightningEntity(EntityStruckByLightningEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof ItemEntity){
            ItemEntity itemEntity = (ItemEntity) entity;
            if (itemEntity.getItem().getItem().equals(ItemRegistry.bedrockPowder.get())){
                World world = itemEntity.world;
                BlockPos pos = itemEntity.getPosition();
                int count = itemEntity.getItem().getCount();
                if (!world.isRemote){
                    for (int i = 0; i < 10; i++)
                        ((ServerWorld)world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 1, 0,0,0,0);
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
                || item instanceof OpPickaxe ) {
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
        if (!(source instanceof PlayerEntity)) return; //伤害来源于玩家
        ItemStack stack = ((PlayerEntity) source).getHeldItemMainhand();
        int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack); //抢夺
        if (entityLiving instanceof EnderDragonEntity){ //末影龙额外掉落 龙晶 创世结晶
            spawnDrops(ItemRegistry.dragonCrystal.get(), random.nextInt(4 + level), world, pos, event);
            spawnDrops(ItemRegistry.jiejing.get(), 1, world, pos, event);
            spawnDrops(ItemRegistry.yuanshi.get(), random.nextInt(4) + level, world, pos, event);
        }
        if (entityLiving instanceof WitherEntity){ //凋零 创世结晶
            spawnDrops(ItemRegistry.jiejing.get(), 1, world, pos, event);
            spawnDrops(ItemRegistry.yuanshi.get(), random.nextInt(4) + level, world, pos, event);
        }
        if (entityLiving instanceof WitherSkeletonEntity) { //凋零骷髅额外掉落 凋零骷髅头 凋零骨
            if (stack.getItem() instanceof BeheadSword) { //使用斩首大剑
                int i = random.nextInt(2);
                if (i > 0) { //掉落时额外耐久消耗
                    stack.damageItem(1, (PlayerEntity) source, e -> ((PlayerEntity) source).sendBreakAnimation(Hand.MAIN_HAND));
                    spawnDrops(Items.WITHER_SKELETON_SKULL, i, world, pos, event);
                }
            }
            int j = random.nextInt(100);
            if (j > (70 - level * 5)) {
                spawnDrops(ItemRegistry.witherBone.get(), random.nextInt(2 + level), world, pos, event);
            }
        }
        if (entityLiving instanceof BlazeEntity) { //烈焰人额外掉落 烈焰骨
            int j = random.nextInt(100);
            if (j > (70 - level * 5)) {
                spawnDrops(ItemRegistry.blazeBone.get(), random.nextInt(2 + level), world, pos, event);
            }
        }
        if (entityLiving instanceof MagmaCubeEntity) { //岩浆怪掉落 史莱姆水晶
            int j = random.nextInt(100);
            if (j > (90 - level * 5)) {
                spawnDrops(ItemRegistry.slimeCrystal.get(), 1, world, pos, event);
            }
        }
        //所有非boss生物掉落 原石
        if (!(entityLiving instanceof EnderDragonEntity) && !(entityLiving instanceof WitherEntity)){
            int j = random.nextInt(100);
            if (j > (94 - level * 5)){
                spawnDrops(ItemRegistry.yuanshi.get(), 1, world, pos, event);
            }
        }
    }

    //玩家登入
    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event){
        //重置双爆属性
        PlayerEntity player = event.getPlayer();
        //发送消息
        player.sendMessage(new TranslationTextComponent("spacearms.message.login")
                .setStyle(Style.EMPTY.setHoverEvent(HoverEvent.Action.SHOW_TEXT.deserialize(new TranslationTextComponent("spacearms.message.login0")))
                        .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://space.bilibili.com/21854371"))), UUID.randomUUID());
    }

    /**
     * 添加额外掉落
     * @param item 需要掉落的物品
     * @param count 数量
     * @param world 世界
     * @param pos 坐标
     * @param event 事件
     */
    private static void spawnDrops(Item item, int count, World world, BlockPos pos, LivingDropsEvent event){
        ItemStack stack1 = new ItemStack(item, count);
        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
        event.getDrops().add(itemEntity);
    }
}

