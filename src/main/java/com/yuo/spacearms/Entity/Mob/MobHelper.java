package com.yuo.spacearms.Entity.Mob;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;

public class MobHelper {
    private static final RandomSource rand  = RandomSource.create();

    /**
     * 怪物通用掉落物
     * @param isRed 是否精英
     */
    public static void getMobDrops(Monster mob, DamageSource source, int looting, boolean isRed){
        ItemStack stack = new ItemStack(Items.DIAMOND, Mth.nextInt(rand, 0, 3 + looting));
        ItemStack stack0 = new ItemStack(Items.EMERALD, Mth.nextInt(rand, looting, 2 + looting));
        ItemStack stack1 = new ItemStack(Items.GOLD_INGOT, Mth.nextInt(rand, 0, 4 + looting));
        ItemStack stack2 = new ItemStack(Items.IRON_INGOT, Mth.nextInt(rand, looting, 5 + looting));
        ItemStack stack3 = new ItemStack(Items.NETHERITE_INGOT, Mth.nextInt(rand, 0, looting));

        mob.spawnAtLocation(stack0);
        mob.spawnAtLocation(stack1);
        mob.spawnAtLocation(stack2);
        mob.spawnAtLocation(stack);
        if (isRed){
            mob.spawnAtLocation(stack3);
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
    public static void setEquipmentBasedOnDifficulty(Monster mob, DifficultyInstance difficulty, boolean isRed){
        //添加装备
        int chance = MobHelper.getArmorChance();
        if (rand.nextFloat() < 0.25F * (difficulty.getDifficulty() == Difficulty.HARD ? 2 : 1)) {
            MobHelper.setMobArmor(mob, difficulty, chance, isRed);
        }

        //添加武器
        if (rand.nextFloat() < (mob.level().getDifficulty() == Difficulty.HARD ? 0.7F : 0.3F)) {
            MobHelper.setMobWeapons(mob, chance, isRed);
        }
    }

    /**
     * 设置装备
     */
    public static void setMobArmor(Monster mob, DifficultyInstance difficulty, int chance, boolean isRed){
        float f = difficulty.getDifficulty() == Difficulty.HARD ? 0.05F : 0.15F;
        boolean flag = true;

        for (EquipmentSlot equipmentslottype : EquipmentSlot.values()) {
            if (equipmentslottype.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack itemstack = mob.getItemBySlot(equipmentslottype);
                if (!flag && rand.nextFloat() < f) {
                    break;
                }

                flag = false;
                if (itemstack.isEmpty()) {
                    Item item = getArmorByChance(equipmentslottype, chance, isRed); //装备物品获取
                    if (item != null) {
                        mob.setItemSlot(equipmentslottype, new ItemStack(item));
                    }
                }
            }
        }
    }

    /**
     * 根据装备品质获取物品
     * @param isRed 是否精英
     */
    public static Item getArmorByChance(EquipmentSlot slotIn, int chance, boolean isRed) {
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
    public static void setMobWeapons(Monster mob, int chance, boolean isRed){
        if (rand.nextFloat() < 0.67) {
            mob.setItemSlot(EquipmentSlot.MAINHAND, getMobWeaponLv(chance, isRed));
        } else {
            mob.setItemSlot(EquipmentSlot.MAINHAND, getMobWeaponLv(chance, isRed));
            mob.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
        }
    }

    /**
     * 获取武器
     */
    private static ItemStack getMobWeaponLv(int chance, boolean isRed){
        boolean b = rand.nextFloat() < .25f;
        return switch (chance) {
            case 0 -> new ItemStack(isRed && b ? Items.WOODEN_AXE : Items.WOODEN_SWORD);
            case 1 -> new ItemStack(isRed && b ? Items.STONE_AXE : Items.STONE_SWORD);
            case 2 -> new ItemStack(isRed && b ? Items.IRON_AXE : Items.IRON_SWORD);
            case 3 -> new ItemStack(isRed && b ? Items.GOLDEN_AXE : Items.GOLDEN_SWORD);
            case 4 -> new ItemStack(isRed && b ? Items.DIAMOND_AXE : Items.DIAMOND_SWORD);
            default -> ItemStack.EMPTY;
        };
    }
}
