package io.github.suel_ki.snuffles.core.mixin.common;

import io.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import io.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(
            method = "trySpreadingFire",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void trySpreadingFire(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci, int i, BlockState blockState) {
        Block block = blockState.getBlock();
        if (block instanceof FrostyFluffBlock) {
            world.setBlockState(pos, SnufflesBlocks.SNUFFLE_FLUFF.getDefaultState(), 2);
        } else if (block instanceof FrostyFluffCarpetBlock) {
            world.setBlockState(pos, SnufflesBlocks.SNUFFLE_FLUFF_CARPET.getDefaultState(), 2);
        }

    }
}
