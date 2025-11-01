package com.yuo.spacearms.Event;

import com.yuo.spacearms.Items.Arms.OpArms;
import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.NetheriteItem;
import com.yuo.spacearms.Items.tool.*;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

/**
 * 事件处理类
 */
@Mod.EventBusSubscriber(modid = SpaceArms.MOD_ID)
public class EventHandler {
    public static List<String> playersWithOpHead = new ArrayList<>();
    public static List<String> playersWithOpChest = new ArrayList<>();
    public static List<String> playersWithOpLeg = new ArrayList<>();
    public static List<String> playersWithOpFeet = new ArrayList<>();
    public static final String IS_BEDROCK = SpaceArms.MOD_ID + ":is_bedrock";

    //检查玩家背包是否有基岩，有则给予负面状态
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Inventory inventory = player.getInventory();
        if (inventory.contains(new ItemStack(Items.BEDROCK))){ //如果玩家持有基岩，则给予负面状态
            //创造模式 和 玩家穿戴op甲 时不生效
            if (player.isCreative() || player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SAItems.opChest.get()){
                player.getPersistentData().putBoolean(IS_BEDROCK, false);
            }else {
                player.getPersistentData().putBoolean(IS_BEDROCK, true);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 4));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 3));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 2));
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 2));
            }
        }else player.getPersistentData().putBoolean(IS_BEDROCK, false);
    }

    @SubscribeEvent
    public static void toolInfo(ItemTooltipEvent event){
        ItemStack itemStack = event.getItemStack();
        int maxDamage = itemStack.getMaxDamage();
        if (maxDamage > 1){
            event.getToolTip().add(Component.literal("耐久:" + (maxDamage - itemStack.getDamageValue()) + "/" + maxDamage));
        }
    }

    //op鞋子 无摔落伤害
    @SubscribeEvent
    public static void playerFall(LivingFallEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            String key = player.getGameProfile().getName() + ":" + player.level().isClientSide;
            if (playersWithOpFeet.contains(key)) {
                event.setCanceled(true);
            }
        }
    }

    //op装备 不受伤害
    @SubscribeEvent
    public static void opArmsImmuneDamage(LivingDamageEvent event){
        LivingEntity entityLiving = event.getEntity();
        if (entityLiving instanceof Player player){
            Boolean hasChest = player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SAItems.opChest.get();
            Boolean hasLeg = player.getItemBySlot(EquipmentSlot.LEGS).getItem() == SAItems.opLegs.get();
            Boolean hasHead = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == SAItems.opHead.get();
            Boolean hasFeet = player.getItemBySlot(EquipmentSlot.FEET).getItem() == SAItems.opFeet.get();
            DamageSource source = event.getSource();
            ItemStack offhand = player.getOffhandItem();
            if (offhand.getItem() instanceof ModShield modShield && source == player.damageSources().magic()){ //盾牌抵抗其防护值一半的魔法伤害
                float probability = modShield.getShieldType().getProbability();
                Level world = player.level();
                BlockPos pos = player.blockPosition();
                if (world.random.nextFloat() < probability){
                    world.playSound(player, pos, SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0f, 3.0f);
                    for (int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + world.random.nextGaussian() / 2, pos.getY() + world.random.nextGaussian() / 2, pos.getZ() + world.random.nextGaussian() / 2,
                                0.01, 0.01, 0.01);
                    }
                    event.setAmount(event.getAmount() - (modShield.getShieldType().getProtectionValue() / 2f));
                }
            }
            if (hasChest && hasFeet && hasHead && hasLeg){
                event.setAmount(0);
                return;
            }
            if (hasChest || hasFeet || hasHead || hasLeg){
                event.setAmount(0.1f);
            }
        }

    }

    //盾牌抵抗伤害(非魔法， 非可以攻击创造伤害)
    @SubscribeEvent
    public static void shieldArmor(LivingAttackEvent event){
        LivingEntity living = event.getEntity();
        if (living instanceof Player player){
            ItemStack offhand = player.getOffhandItem();
            if (offhand.getItem() instanceof ModShield modShield && event.getSource() != player.damageSources().magic()
                    && !event.getSource().isCreativePlayer()){
                float probability = modShield.getShieldType().getProbability();
                Level world = player.level();
                BlockPos pos = player.blockPosition();
                pos.offset((int) Math.ceil(player.getLookAngle().x), 0, (int) Math.ceil(player.getLookAngle().z));
                if (world.random.nextFloat() < probability){
                    world.playSound(player, pos, SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0f, 3.0f);
                    for (int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.CLOUD, pos.getX() + world.random.nextGaussian() / 2, pos.getY() + world.random.nextGaussian() / 2, pos.getZ() + world.random.nextGaussian() / 2,
                                0.01, 0.01, 0.01);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }

    //op胸甲 飞行 护腿 行走速度增加
    @SubscribeEvent
    public static void updatePlayerAbilityStatus(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            boolean hasChest = player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SAItems.opChest.get();
            boolean hasLeg = player.getItemBySlot(EquipmentSlot.LEGS).getItem() == SAItems.opLegs.get();
            boolean hasHead = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == SAItems.opHead.get();
            boolean hasFoot = player.getItemBySlot(EquipmentSlot.FEET).getItem() == SAItems.opFeet.get();
            //防止其它模组飞行装备无法使用
            String key = player.getGameProfile().getName()+":"+player.level().isClientSide;
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
                    player.getAbilities().flying = true;
                }else {
                    if (!player.isCreative()) {
                        player.getAbilities().flying = false;
                        player.getAbilities().mayfly = false;
                    }
                    playersWithOpChest.remove(key);
                }
            }else if (hasChest) {
                playersWithOpChest.add(key);
            }
            //leg
            if (playersWithOpLeg.contains(key)) {
                AttributeInstance attribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
                if (hasLeg) {
                    if (attribute != null && !attribute.hasModifier(OpArms.modifier))
                        attribute.addPermanentModifier(OpArms.modifier); //行走速度
                }else {
                    if (attribute != null && attribute.hasModifier(OpArms.modifier))
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
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            String key = player.getGameProfile().getName()+":"+player.level().isClientSide;
            if (playersWithOpFeet.contains(key)) {
                player.setDeltaMovement(0, 1.0f, 0);
            }
            if (player.getPersistentData().getBoolean(IS_BEDROCK)){
                player.setDeltaMovement(0, 0.15f, 0);
            }
        }
    }
    //基岩镐右键基岩，将基岩变为脆弱基岩
    @SubscribeEvent
    public static void breakBedrock(PlayerInteractEvent.RightClickBlock event){
        Level world = event.getLevel();
        Item item = event.getItemStack().getItem();
        BlockPos pos = event.getPos();
        if (item instanceof OpPickaxe && !world.isClientSide){
            BlockState state = world.getBlockState(pos);
            if (pos.getY() == 0){ //0是最下层基岩
                Player player = event.getEntity();
                player.sendSystemMessage(Component.translatable("spacearms.text.info.bedrock"));
                return;
            }
            if (state.getBlock().equals(Blocks.BEDROCK)){
                world.setBlockAndUpdate(pos, SABlocks.fragileBedrock.get().defaultBlockState());
            }
        }
    }
    //玩家合成基岩武器时添加附魔
    @SubscribeEvent
    public static void opTool(PlayerEvent.ItemCraftedEvent event){
        ItemStack stack = event.getCrafting();
        if (stack.getItem().equals(SAItems.opSword.get())){
            Map<Enchantment, Integer> map = new HashMap<>();
            map.put(Enchantments.MOB_LOOTING, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
        if (stack.getItem().equals(SAItems.opPickaxe.get())){
            Map<Enchantment, Integer> map = new HashMap<>();
            map.put(Enchantments.BLOCK_FORTUNE, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
    }
    //闪电击中基岩粉变成基岩锭
    @SubscribeEvent
    public static void lightningEntity(EntityStruckByLightningEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof ItemEntity){
            ItemEntity itemEntity = (ItemEntity) entity;
            if (itemEntity.getItem().getItem().equals(SAItems.bedrockPowder.get())){
                Level world = itemEntity.level();
                BlockPos pos = itemEntity.blockPosition();
                int count = itemEntity.getItem().getCount();
                if (!world.isClientSide){
                    for (int i = 0; i < 10; i++)
                        ((ServerLevel)world).sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 1, 0,0,0,0);
                    ItemStack stack = new ItemStack(SAItems.bedrockIngot.get(), count);
                    itemEntity.setItem(stack);
                    world.addFreshEntity(itemEntity);
                }
            }
        }
    }
    //不会被烧毁的物品
    @SubscribeEvent
    public static void entityItemUnDeath(ItemEvent event) { //物品实体事件
        ItemEntity entityItem = event.getEntity();
        Item item = entityItem.getItem().getItem();
        if(item instanceof OpSword || item instanceof OpArms || item instanceof WolfSword || item instanceof NetheriteItem
                || item instanceof OpPickaxe ) {
            entityItem.setInvulnerable(true); // 设置物品实体不会死亡
        }
    }
    //铁砧配方
//    @SubscribeEvent
    public static void NewRecipes(AnvilUpdateEvent event) {
        ItemStack stack=event.getLeft();
        ItemStack stack2=event.getRight();
        int count1=stack.getCount();
        int count2=stack2.getCount();
        if((stack.getItem().equals(SAItems.spacePickaxe.get()) && stack2.getItem().equals(SAItems.spaceCore.get())))
        {
            if(count1 <= count2)
            {
                event.setCost(30);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(SAItems.superSpacePickaxe.get(), 1));
            }
        }
    }

    //原版生物额外掉落
    @SubscribeEvent
    public static void enderDragonDrops(LivingDropsEvent event){
        LivingEntity entityLiving = event.getEntity();
        Level world = entityLiving.level();
        BlockPos pos = entityLiving.blockPosition();
        Random random = new Random();
        Entity source = event.getSource().getEntity();
        if (!(source instanceof Player)) return; //伤害来源于玩家
        ItemStack stack = ((Player) source).getMainHandItem();
        int level = stack.getEnchantmentLevel(Enchantments.MOB_LOOTING); //抢夺
        if (entityLiving instanceof EnderDragon){ //末影龙额外掉落 龙晶 创世结晶
            spawnDrops(SAItems.dragonCrystal.get(), random.nextInt(4 + level), world, pos, event);
        }
        if (entityLiving instanceof WitherBoss) { //凋零骷髅额外掉落 凋零骷髅头 凋零骨
            if (stack.getItem() instanceof BeheadSword) { //使用斩首大剑
                int i = random.nextInt(2);
                if (i > 0) { //掉落时额外耐久消耗
                    stack.hurtAndBreak(1, (Player) source, e -> ((Player) source).broadcastBreakEvent(InteractionHand.MAIN_HAND));
                    spawnDrops(Items.WITHER_SKELETON_SKULL, i, world, pos, event);
                }
            }
            int j = random.nextInt(100);
            if (j > (70 - level * 5)) {
                spawnDrops(SAItems.witherBone.get(), random.nextInt(2 + level), world, pos, event);
            }
        }
        if (entityLiving instanceof Blaze) { //烈焰人额外掉落 烈焰骨
            int j = random.nextInt(100);
            if (j > (70 - level * 5)) {
                spawnDrops(SAItems.blazeBone.get(), random.nextInt(2 + level), world, pos, event);
            }
        }
    }

    //玩家登入
    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event){
        //重置双爆属性
        Player player = event.getEntity();
        //发送消息
        player.sendSystemMessage(Component.translatable("spacearms.message.login")
                .setStyle(Style.EMPTY.withHoverEvent(HoverEvent.Action.SHOW_TEXT.deserializeFromLegacy(Component.translatable("spacearms.message.login0")))
                        .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://space.bilibili.com/21854371"))));
    }

    /**
     * 添加额外掉落
     * @param item 需要掉落的物品
     * @param count 数量
     * @param world 世界
     * @param pos 坐标
     * @param event 事件
     */
    private static void spawnDrops(Item item, int count, Level world, BlockPos pos, LivingDropsEvent event){
        ItemStack stack1 = new ItemStack(item, count);
        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack1);
        event.getDrops().add(itemEntity);
    }
}

