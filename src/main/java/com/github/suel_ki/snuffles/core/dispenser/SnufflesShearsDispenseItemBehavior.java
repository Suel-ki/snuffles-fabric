package com.github.suel_ki.snuffles.core.dispenser;

import com.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.event.GameEvent;

public class SnufflesShearsDispenseItemBehavior {
    private static DispenserBehavior ShearsBehavior;

    public static void registerDispenserBehaviors() {

        ShearsBehavior = DispenserBlock.BEHAVIORS.get(Items.SHEARS);

        DispenserBlock.registerBehavior(Items.SHEARS, new FallibleItemDispenserBehavior() {

            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                ServerWorld worldIn = pointer.getWorld();
                BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                for (Snuffle snuffle : worldIn.getEntitiesByClass(Snuffle.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR)) {
                    if (snuffle.isShearable()) {
                        snuffle.sheared(SoundCategory.BLOCKS);
                        worldIn.emitGameEvent(null, GameEvent.SHEAR, pos);

                        if (stack.damage(1, worldIn.getRandom(), null))
                            stack.setCount(0);
                        this.setSuccess(true);
                        return stack;
                    }
                }

                return ShearsBehavior.dispense(pointer, stack);
            }
        });
    }
}