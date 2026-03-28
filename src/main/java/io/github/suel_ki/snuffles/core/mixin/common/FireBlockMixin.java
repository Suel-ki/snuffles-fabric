package io.github.suel_ki.snuffles.core.mixin.common;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import io.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(
            method = "checkBurnOut",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;")
    )
    private void trySpreadingFire(Level world, BlockPos pos, int spreadFactor, RandomSource random, int currentAge, CallbackInfo ci, @Local(name = "oldState") BlockState blockState) {
        Block block = blockState.getBlock();
        if (block instanceof FrostyFluffBlock) {
            world.setBlock(pos, SnufflesBlocks.SNUFFLE_FLUFF.defaultBlockState(), 2);
        } else if (block instanceof FrostyFluffCarpetBlock) {
            world.setBlock(pos, SnufflesBlocks.SNUFFLE_FLUFF_CARPET.defaultBlockState(), 2);
        }

    }
}
