package com.yuo.spacearms.Entity.Mob;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class GreenZombie extends ZombieEntity {

    private static final Random rand = new Random();

    public GreenZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::isBreakDoorsTaskSet));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(MonsterEntity.class)); //被攻击后呼叫所有怪物帮助
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
    }

    //属性
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS, 1.5D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
//                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0d);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setMotionMultiplier(BlockState state, Vector3d motionMultiplierIn) {
        if (!state.matchesBlock(Blocks.COBWEB)) {
            super.setMotionMultiplier(state, motionMultiplierIn);
        }

    }
    //被攻击时
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
    }

    //攻击时
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }

    //是否可以装备物品
    @Override
    public boolean canEquipItem(ItemStack stack) {
        return true;
    }

    //生成
    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        float f = difficultyIn.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(rand.nextFloat() < 0.55F * f);
        if (spawnDataIn == null) {
            spawnDataIn = new GroupData(func_241399_a_(worldIn.getRandom()), true);
        }

        if (spawnDataIn instanceof GroupData) {
            GroupData zombieentity$groupdata = (GroupData) spawnDataIn;
            if (zombieentity$groupdata.isChild) {
                this.setChild(true);
                if (zombieentity$groupdata.field_241400_b_) {
                    if ((double) worldIn.getRandom().nextFloat() < 0.05D) {
                        List<ChickenEntity> list = worldIn.getEntitiesWithinAABB(ChickenEntity.class, this.getBoundingBox().grow(5.0D, 3.0D, 5.0D), EntityPredicates.IS_STANDALONE);
                        if (!list.isEmpty()) {
                            ChickenEntity chickenentity = list.get(0);
                            chickenentity.setChickenJockey(true);
                            this.startRiding(chickenentity);
                        }
                    } else if ((double) worldIn.getRandom().nextFloat() < 0.05D) {
                        ChickenEntity chickenentity1 = EntityType.CHICKEN.create(this.world);
                        if (chickenentity1 != null) {
                            chickenentity1.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
                            chickenentity1.onInitialSpawn(worldIn, difficultyIn, SpawnReason.JOCKEY, null, null);
                            chickenentity1.setChickenJockey(true);
                            this.startRiding(chickenentity1);
                            worldIn.addEntity(chickenentity1);
                        }
                    }
                }
            }

            this.setBreakDoorsAItask(this.canBreakDoors() && rand.nextFloat() < f * 0.3F); //破门概率
            this.setEquipmentBasedOnDifficulty(difficultyIn); //装备
            this.setEnchantmentBasedOnDifficulty(difficultyIn); //附魔
        }

        if (this.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.getDayOfMonth();
            int j = localdate.getMonthValue();
            if (j == 10 && i == 31 && rand.nextFloat() < 0.25F) {
                this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(rand.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.inventoryArmorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        }

        this.applyAttributeBonuses(f);
        return spawnDataIn;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        //添加装备
        if (rand.nextFloat() < 0.25F * (difficulty.getDifficulty() == Difficulty.HARD ? 2 : 1)) {
            int i = rand.nextInt(2);
            float f = this.world.getDifficulty() == Difficulty.HARD ? 0.05F : 0.15F;
            if (rand.nextFloat() < 0.105F) {
                ++i;
            }

            if (rand.nextFloat() < 0.085F) {
                ++i;
            }

            if (rand.nextFloat() < 0.075F) {
                ++i;
            }

            boolean flag = true;

            for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
                if (equipmentslottype.getSlotType() == EquipmentSlotType.Group.ARMOR) {
                    ItemStack itemstack = this.getItemStackFromSlot(equipmentslottype);
                    if (!flag && rand.nextFloat() < f) {
                        break;
                    }

                    flag = false;
                    if (itemstack.isEmpty()) {
                        Item item = getArmorByChance(equipmentslottype, i); //装备物品获取
                        if (item != null) {
                            this.setItemStackToSlot(equipmentslottype, new ItemStack(item));
                        }
                    }
                }
            }
        }
        //添加武器
        if (rand.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 0.7F : 0.3F)) {
            int i = rand.nextInt(10);
            if (i < 3) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else if (i < 6) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            } else if (i < 8) {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
                this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
            } else {
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
                this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
            }
        }

    }

    @Nullable
    public static Item getArmorByChance(EquipmentSlotType slotIn, int chance) {
        switch (slotIn) {
            case HEAD:
                if (chance == 0) {
                    return Items.IRON_HELMET;
                } else if (chance == 1) {
                    return Items.DIAMOND_HELMET;
                }
            case CHEST:
                if (chance == 0) {
                    return Items.IRON_CHESTPLATE;
                } else if (chance == 1) {
                    return Items.DIAMOND_CHESTPLATE;
                }
            case LEGS:
                if (chance == 0) {
                    return Items.IRON_LEGGINGS;
                } else if (chance == 1) {
                    return Items.DIAMOND_LEGGINGS;
                }
            case FEET:
               if (chance == 0) {
                    return Items.IRON_BOOTS;
                } else if (chance == 1) {
                    return Items.DIAMOND_BOOTS;
                }
            default:
                return null;
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20, 0));
    }

    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }

    //淹死
    @Override
    public boolean isDrowning() {
        return false;
    }

    @Override
    protected boolean shouldDrown() {
        return false;
    }

    @Override
    public boolean isBreakDoorsTaskSet() {
        return true;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        this.experienceValue *= 10;
        return super.getExperiencePoints(player);
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        ItemStack stack = new ItemStack(Items.DIAMOND, MathHelper.nextInt(rand, 1, 3 + looting));
        ItemStack stack0 = new ItemStack(Items.EMERALD, MathHelper.nextInt(rand, 1 + looting, 8 + looting));
        ItemStack stack1 = new ItemStack(Items.GOLD_INGOT, MathHelper.nextInt(rand, 1 + looting, 5 + looting));
//        ItemStack stack2 = new ItemStack(Items.DIAMOND_BLOCK, MathHelper.nextInt(rand, 1, 3 + looting));
//        ItemStack stack3 = new ItemStack(Items.EMERALD_BLOCK, MathHelper.nextInt(rand, 2, 4 + looting));
//        ItemStack stack4 = new ItemStack(Items.GOLD_BLOCK, MathHelper.nextInt(rand, 3, 5 + looting));
        this.entityDropItem(stack);
        this.entityDropItem(stack0);
        this.entityDropItem(stack1);
//        this.entityDropItem(stack2);
//        this.entityDropItem(stack3);
//        this.entityDropItem(stack4);
    }
}
