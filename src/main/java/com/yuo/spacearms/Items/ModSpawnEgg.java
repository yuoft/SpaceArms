package com.yuo.spacearms.Items;

import com.yuo.spacearms.SATabs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModSpawnEgg extends Item {
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;
    private static int primaryColor = 0xff0000;
    private static int secondaryColor = 0xff00ff;

    public ModSpawnEgg(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn) {
        super(new Properties().group(SATabs.spaceArms));
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier);
        primaryColor = primaryColorIn;
        secondaryColor = secondaryColorIn;
    }

    public EntityType<?> getType() {
        return this.entityTypeSupplier.get();
    }

    public static int getColor(ItemStack stack, int i) {
        return i == 0 ? ModSpawnEgg.primaryColor : ModSpawnEgg.secondaryColor;
    }


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getPos();
        if (!world.isRemote) {
            Direction face = context.getFace();
            BlockState state = world.getBlockState(blockPos);
            BlockPos blockpos1;
            if (state.getCollisionShapeUncached(world, blockPos).isEmpty()) {
                blockpos1 = blockPos;
            } else {
                blockpos1 = blockPos.offset(face);
            }

            EntityType<?> entitytype = this.getType();
            if (entitytype.spawn((ServerWorld)world, stack, player, blockpos1, SpawnReason.SPAWN_EGG, true,
                    !Objects.equals(blockPos, blockpos1) && face == Direction.UP) != null) {
                stack.shrink(1);
            }

            return ActionResultType.CONSUME;
        }
        return super.onItemUse(context);
    }
}
