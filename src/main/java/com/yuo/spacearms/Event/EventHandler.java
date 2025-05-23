package com.yuo.spacearms.Event;

import com.yuo.spacearms.Arms.OpArms;
import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.NetheriteItem;
import com.yuo.spacearms.Items.tool.*;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
        PlayerEntity player = event.player;
        PlayerInventory inventory = player.inventory;
        if (inventory.hasItemStack(new ItemStack(Items.BEDROCK))){ //如果玩家持有基岩，则给予负面状态
            //创造模式 和 玩家穿戴op甲 时不生效
            if (player.isCreative() || player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == SAItems.opChest.get()){
                player.getPersistentData().putBoolean(IS_BEDROCK, false);
            }else {
                player.getPersistentData().putBoolean(IS_BEDROCK, true);
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
                player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 3));
                player.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 2));
                player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 2));
            }
        }else player.getPersistentData().putBoolean(IS_BEDROCK, false);
    }

    @SubscribeEvent
    public static void toolInfo(ItemTooltipEvent event){
        ItemStack itemStack = event.getItemStack();
        int maxDamage = itemStack.getMaxDamage();
        if (maxDamage > 1){
            event.getToolTip().add(new StringTextComponent("耐久:" + (maxDamage - itemStack.getDamage()) + "/" + maxDamage));
        }
    }

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
        }
    }

    //op装备 不受伤害
    @SubscribeEvent
    public static void opArmsImmuneDamage(LivingDamageEvent event){
        LivingEntity entityLiving = event.getEntityLiving();
        if (entityLiving instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entityLiving;
            Boolean hasChest = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == SAItems.opChest.get();
            Boolean hasLeg = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == SAItems.opLegs.get();
            Boolean hasHead = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == SAItems.opHead.get();
            Boolean hasFeet = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == SAItems.opFeet.get();
            DamageSource source = event.getSource();
            ItemStack offhand = player.getHeldItemOffhand();
            if (offhand.getItem() instanceof ModShield && source.isMagicDamage()){ //盾牌抵抗其防护值一半的魔法伤害
                ModShield modShield = (ModShield) offhand.getItem();
                float probability = modShield.getShieldType().getProbability();
                World world = player.world;
                BlockPos pos = player.getPosition();
                if (world.rand.nextFloat() < probability){
                    world.playSound(player, pos, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 3.0f);
                    for (int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + world.rand.nextGaussian() / 2, pos.getY() + world.rand.nextGaussian() / 2, pos.getZ() + world.rand.nextGaussian() / 2,
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
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) living;
            ItemStack offhand = player.getHeldItemOffhand();
            if (offhand.getItem() instanceof ModShield && !event.getSource().isMagicDamage() && !event.getSource().canHarmInCreative()){
                ModShield modShield = (ModShield) offhand.getItem();
                float probability = modShield.getShieldType().getProbability();
                World world = player.world;
                BlockPos pos = player.getPosition();
                pos.add(player.getLookVec().x, 0, player.getLookVec().z);
                if (world.rand.nextFloat() < probability){
                    world.playSound(player, pos, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 3.0f);
                    for (int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.CLOUD, pos.getX() + world.rand.nextGaussian() / 2, pos.getY() + world.rand.nextGaussian() / 2, pos.getZ() + world.rand.nextGaussian() / 2,
                                0.01, 0.01, 0.01);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }

    //op胸甲 飞行 护腿 行走速度增加
    @SubscribeEvent
    public static void updatePlayerAbilityStatus(LivingEvent.LivingUpdateEvent event) {
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) living;
            boolean hasChest = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == SAItems.opChest.get();
            boolean hasLeg = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == SAItems.opLegs.get();
            boolean hasHead = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == SAItems.opHead.get();
            boolean hasFoot = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == SAItems.opFeet.get();
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
                if (hasLeg) {
                    if (attribute != null && !attribute.hasModifier(OpArms.modifier))
                        attribute.applyPersistentModifier(OpArms.modifier); //行走速度
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
        LivingEntity living = event.getEntityLiving();
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)living;
            String key = player.getGameProfile().getName()+":"+player.world.isRemote;
            if (playersWithOpFeet.contains(key)) {
                player.setMotion(0, 1.0f, 0);
            }
            if (player.getPersistentData().getBoolean(IS_BEDROCK)){
                player.setMotion(0, 0.15f, 0);
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
                world.setBlockState(pos, SABlocks.fragileBedrock.get().getDefaultState());
            }
        }
    }
    //玩家合成基岩武器时添加附魔
    @SubscribeEvent
    public static void opTool(PlayerEvent.ItemCraftedEvent event){
        ItemStack stack = event.getCrafting();
        if (stack.getItem().equals(SAItems.opSword.get())){
            Map<Enchantment, Integer> map = new HashMap<>();
            map.put(Enchantments.LOOTING, 10);
            EnchantmentHelper.setEnchantments( map, stack);
        }
        if (stack.getItem().equals(SAItems.opPickaxe.get())){
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
            if (itemEntity.getItem().getItem().equals(SAItems.bedrockPowder.get())){
                World world = itemEntity.world;
                BlockPos pos = itemEntity.getPosition();
                int count = itemEntity.getItem().getCount();
                if (!world.isRemote){
                    for (int i = 0; i < 10; i++)
                        ((ServerWorld)world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 1, 0,0,0,0);
                    ItemStack stack = new ItemStack(SAItems.bedrockIngot.get(), count);
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
        LivingEntity entityLiving = event.getEntityLiving();
        World world = entityLiving.world;
        BlockPos pos = entityLiving.getPosition();
        Random random = new Random();
        Entity source = event.getSource().getTrueSource();
        if (!(source instanceof PlayerEntity)) return; //伤害来源于玩家
        ItemStack stack = ((PlayerEntity) source).getHeldItemMainhand();
        int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack); //抢夺
        if (entityLiving instanceof EnderDragonEntity){ //末影龙额外掉落 龙晶 创世结晶
            spawnDrops(SAItems.dragonCrystal.get(), random.nextInt(4 + level), world, pos, event);
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
                spawnDrops(SAItems.witherBone.get(), random.nextInt(2 + level), world, pos, event);
            }
        }
        if (entityLiving instanceof BlazeEntity) { //烈焰人额外掉落 烈焰骨
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

