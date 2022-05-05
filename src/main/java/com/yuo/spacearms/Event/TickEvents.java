package com.yuo.spacearms.Event;

import com.yuo.spacearms.Spacearms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.IdentityHashMap;

/**
 * 事件处理类
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MODID)
public class TickEvents {

    private static boolean IS_BEDROCK = false; //是否持有基岩
    private static final IdentityHashMap<Entity, TickEvents> bouncingEntities = new IdentityHashMap<>();

    public final LivingEntity entityLiving;
    private int timer;
    private boolean wasInAir;
    private double bounce;
    private int bounceTick;

    private double lastMovX;
    private double lastMovZ;

    public TickEvents(LivingEntity entityLiving, double bounce) {
        this.entityLiving = entityLiving;
        this.timer = 0;
        this.wasInAir = false;
        this.bounce = bounce;

        if (bounce != 0) {
            this.bounceTick = entityLiving.ticksExisted;
        } else {
            this.bounceTick = 0;
        }

        bouncingEntities.put(entityLiving, this);
    }
    //检查玩家背包是否有基岩，有则给予负面状态
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        PlayerInventory inventory = player.inventory;
        NonNullList<ItemStack> mainInventory = inventory.mainInventory;
        mainInventory.forEach((e) ->{ setPlayerEffects(e, player); });
        NonNullList<ItemStack> offHandInventory = inventory.offHandInventory;
        offHandInventory.forEach( e ->{ setPlayerEffects(e, player); });
    }
    //使玩家弹起来
    @SubscribeEvent
    public void slimeFeet(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        //玩家掉落着陆时触发
        if (event.phase == TickEvent.Phase.END && player == this.entityLiving && !event.player.isElytraFlying()) {
            // 弹起来. 重置玩家y方向运动轨迹
            if (event.player.ticksExisted == this.bounceTick) {
                Vector3d vec3d = event.player.getMotion();
                player.setMotion(vec3d.x, this.bounce, vec3d.z);
                this.bounceTick = 0;
            }

            // 保持运动
            if (!this.entityLiving.isOnGround() && this.entityLiving.ticksExisted != this.bounceTick) {
                if (this.lastMovX != this.entityLiving.getMotion().x || this.lastMovZ != this.entityLiving.getMotion().z) {
                    double f = 0.91d + 0.025d;
                    Vector3d vec3d = this.entityLiving.getMotion();
                    player.setMotion(vec3d.x / f, vec3d.y, vec3d.z / f);
                    this.entityLiving.isAirBorne = true;
                    this.lastMovX = this.entityLiving.getMotion().x;
                    this.lastMovZ = this.entityLiving.getMotion().z;
                }
            }

            // timing the effect out
            if (this.wasInAir && this.entityLiving.isOnGround()) {
                if (this.timer == 0) {
                    this.timer = this.entityLiving.ticksExisted;
                } else if (this.entityLiving.ticksExisted - this.timer > 5) {
                    MinecraftForge.EVENT_BUS.unregister(this);
                    bouncingEntities.remove(this.entityLiving);
                }
            } else {
                this.timer = 0;
                this.wasInAir = true;
            }
        }
    }

    /**
     * 判断物品，设置玩家状态
     * @param e 物品
     * @param player 玩家
     */
    private static void setPlayerEffects(ItemStack e, PlayerEntity player){
        if (e.getItem().equals(Items.BEDROCK)){ //如果玩家持有基岩，则给予负面状态
            if (!player.isCreative()){
                IS_BEDROCK = true;
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
                player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 3));
                player.addPotionEffect(new EffectInstance(Effects.HUNGER, 200, 2));
                player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 2));
            }else IS_BEDROCK = false;
        }else {
            IS_BEDROCK = false;
        }
    }
    public static void addBounceHandler(LivingEntity entity) {
        addBounceHandler(entity, 0d);
    }

    public static void addBounceHandler(LivingEntity entity, double bounce) {
        // 只对玩家生效
        if (!(entity instanceof PlayerEntity) || entity instanceof FakePlayer) {
            return;
        }
        TickEvents handler = bouncingEntities.get(entity);
        if (handler == null) {
            // 没有事件监听，则注册一个
            MinecraftForge.EVENT_BUS.register(new TickEvents(entity, bounce));
        } else if (bounce != 0) {
            // 更新弹跳
            handler.bounce = bounce;
            handler.bounceTick = entity.ticksExisted;
        }
    }

    public static boolean isIS_BEDROCK() {
        return IS_BEDROCK;
    }

    public static void setIS_BEDROCK(boolean flag) {
        IS_BEDROCK = flag;
    }
}

