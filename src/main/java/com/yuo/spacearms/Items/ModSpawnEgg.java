package com.yuo.spacearms.Items;

import com.yuo.spacearms.SATabs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModSpawnEgg extends Item {
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;
    private static int primaryColor = 0xff0000;
    private static int secondaryColor = 0xff00ff;

    public ModSpawnEgg(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn) {
        super(new Properties());
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
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        if (!world.isClientSide) {
            Direction face = context.getClickedFace();
            BlockState state = world.getBlockState(blockPos);
            BlockPos blockpos1;
            if (state.getCollisionShape(world, blockPos).isEmpty()) {
                blockpos1 = blockPos;
            } else {
                blockpos1 = blockPos.relative(face);
            }

            EntityType<?> entitytype = this.getType();
            if (entitytype.spawn((ServerLevel) world, stack, player, blockpos1, MobSpawnType.SPAWN_EGG, true,
                    !Objects.equals(blockPos, blockpos1) && face == Direction.UP) != null) {
                stack.shrink(1);
            }

            return InteractionResult.CONSUME;
        }
        return super.useOn(context);
    }
}
