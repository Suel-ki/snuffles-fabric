package io.github.suel_ki.snuffles.core.dispenser;

import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

public class SnufflesShearsDispenseItemBehavior {
    private static DispenseItemBehavior ShearsBehavior;

    public static void registerDispenserBehaviors() {

        ShearsBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.SHEARS);

        DispenserBlock.registerBehavior(Items.SHEARS, new ShearsDispenseItemBehavior() {

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                ServerLevel world = source.level();
                BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                for (Snuffle snuffle : world.getEntitiesOfClass(Snuffle.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {
                    if (snuffle.readyForShearing()) {
                        snuffle.shear(world, SoundSource.BLOCKS, stack);
                        world.gameEvent(null, GameEvent.SHEAR, pos);
                        stack.hurtAndBreak(1, world, null, (_) -> {
                        });

                        this.setSuccess(true);
                        return stack;
                    }
                }

                return ShearsBehavior.dispense(source, stack);
            }
        });
    }
}