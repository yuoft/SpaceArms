package com.yuo.spacearms.Entity.Mob;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;

import java.util.Random;

public class MobHelper {
    private static final Random rand  = new Random();

    /**
     * 怪物通用掉落物
     * @param isRed 是否精英
     */
    public static void getMobDrops(MonsterEntity mob, DamageSource source, int looting, boolean isRed){
        ItemStack stack = new ItemStack(Items.DIAMOND, MathHelper.nextInt(rand, 0, 3 + looting));
        ItemStack stack0 = new ItemStack(Items.EMERALD, MathHelper.nextInt(rand, looting, 2 + looting));
        ItemStack stack1 = new ItemStack(Items.GOLD_INGOT, MathHelper.nextInt(rand, 0, 4 + looting));
        ItemStack stack2 = new ItemStack(Items.IRON_INGOT, MathHelper.nextInt(rand, looting, 5 + looting));
        ItemStack stack3 = new ItemStack(Items.NETHERITE_INGOT, MathHelper.nextInt(rand, 0, looting));

        mob.entityDropItem(stack0);
        mob.entityDropItem(stack1);
        mob.entityDropItem(stack2);
        mob.entityDropItem(stack);
        if (isRed){
            mob.entityDropItem(stack3);
        }
    }

    /**
     * 装备品质
     */
    public static int getArmorChance(){
        int i = rand.nextInt(2);
        if (rand.nextFloat() < 0.185F) {
            ++i;
        }

        if (rand.nextFloat() < 0.125F) {
            ++i;
        }

        if (rand.nextFloat() < 0.105F) {
            ++i;
        }

        if (rand.nextFloat() < 0.075F) {
            ++i;
        }

        return i;
    }

    /**
     * 根据难度设置装备
     */
    public static void setEquipmentBasedOnDifficulty(MonsterEntity mob, DifficultyInstance difficulty, boolean isRed){
        //添加装备
        int chance = MobHelper.getArmorChance();
        if (rand.nextFloat() < 0.25F * (difficulty.getDifficulty() == Difficulty.HARD ? 2 : 1)) {
            MobHelper.setMobArmor(mob, difficulty, chance, isRed);
        }

        //添加武器
        if (rand.nextFloat() < (mob.world.getDifficulty() == Difficulty.HARD ? 0.7F : 0.3F)) {
            MobHelper.setMobWeapons(mob, chance, isRed);
        }
    }

    /**
     * 设置装备
     */
    public static void setMobArmor(MonsterEntity mob, DifficultyInstance difficulty, int chance, boolean isRed){
        float f = difficulty.getDifficulty() == Difficulty.HARD ? 0.05F : 0.15F;
        boolean flag = true;

        for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
            if (equipmentslottype.getSlotType() == EquipmentSlotType.Group.ARMOR) {
                ItemStack itemstack = mob.getItemStackFromSlot(equipmentslottype);
                if (!flag && rand.nextFloat() < f) {
                    break;
                }

                flag = false;
                if (itemstack.isEmpty()) {
                    Item item = getArmorByChance(equipmentslottype, chance, isRed); //装备物品获取
                    if (item != null) {
                        mob.setItemStackToSlot(equipmentslottype, new ItemStack(item));
                    }
                }
            }
        }
    }

    /**
     * 根据装备品质获取物品
     * @param isRed 是否精英
     */
    public static Item getArmorByChance(EquipmentSlotType slotIn, int chance, boolean isRed) {
        switch (slotIn) {
            case HEAD:
                if (chance == 0) {
                    return Items.CHAINMAIL_HELMET;
                } else if (chance == 1) {
                    return Items.IRON_HELMET;
                } else if (chance == 2) {
                    return Items.GOLDEN_HELMET;
                } else if (chance == 3 && isRed) {
                    return Items.DIAMOND_HELMET;
                } else if (chance == 4 && isRed) {
                    return Items.NETHERITE_HELMET;
                }
            case CHEST:
                if (chance == 0) {
                    return Items.CHAINMAIL_CHESTPLATE;
                } else if (chance == 1) {
                    return Items.IRON_CHESTPLATE;
                } else if (chance == 2) {
                    return Items.GOLDEN_CHESTPLATE;
                } else if (chance == 3 && isRed) {
                    return Items.DIAMOND_CHESTPLATE;
                } else if (chance == 4 && isRed) {
                    return Items.NETHERITE_CHESTPLATE;
                }
            case LEGS:
                if (chance == 0) {
                    return Items.CHAINMAIL_LEGGINGS;
                } else if (chance == 1) {
                    return Items.IRON_LEGGINGS;
                } else if (chance == 2) {
                    return Items.GOLDEN_LEGGINGS;
                } else if (chance == 3 && isRed) {
                    return Items.DIAMOND_LEGGINGS;
                } else if (chance == 4 && isRed) {
                    return Items.NETHERITE_LEGGINGS;
                }
            case FEET:
                if (chance == 0) {
                    return Items.CHAINMAIL_BOOTS;
                } else if (chance == 1) {
                    return Items.IRON_BOOTS;
                } else if (chance == 2) {
                    return Items.GOLDEN_BOOTS;
                } else if (chance == 3 && isRed) {
                    return Items.DIAMOND_BOOTS;
                } else if (chance == 4 && isRed) {
                    return Items.NETHERITE_BOOTS;
                }
            default:
                return null;
        }
    }

    /**
     * 设置手持武器
     */
    public static void setMobWeapons(MonsterEntity mob, int chance, boolean isRed){
        if (rand.nextFloat() < 0.67) {
            mob.setItemStackToSlot(EquipmentSlotType.MAINHAND, getMobWeaponLv(chance, isRed));
        } else {
            mob.setItemStackToSlot(EquipmentSlotType.MAINHAND, getMobWeaponLv(chance, isRed));
            mob.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
        }
    }

    /**
     * 获取武器
     */
    private static ItemStack getMobWeaponLv(int chance, boolean isRed){
        boolean b = rand.nextFloat() < .25f;
        switch (chance){
            case 0: return new ItemStack(isRed && b ? Items.WOODEN_AXE : Items.WOODEN_SWORD);
            case 1: return new ItemStack(isRed && b ? Items.STONE_AXE : Items.STONE_SWORD);
            case 2: return new ItemStack(isRed && b ? Items.IRON_AXE : Items.IRON_SWORD);
            case 3: return new ItemStack(isRed && b ? Items.GOLDEN_AXE : Items.GOLDEN_SWORD);
            case 4: return new ItemStack(isRed && b ? Items.DIAMOND_AXE : Items.DIAMOND_SWORD);
            default: return ItemStack.EMPTY;
        }
    }
}
