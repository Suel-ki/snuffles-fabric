package io.github.suel_ki.snuffles.core.mixin.common;

import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    private Level level;

    @Shadow
    protected abstract BlockPos getOnPos(float offset);

    @Inject(method = "spawnSprintParticle", at = @At("HEAD"), cancellable = true)
    private void cancelWalkingParticles(CallbackInfo ci) {
        BlockPos pos = this.getOnPos(0.2F);
        BlockState state = this.level.getBlockState(pos);

        if (state.is(SnufflesBlocks.FROSTY_FLUFF) || state.is(SnufflesBlocks.FROSTY_FLUFF_CARPET)) {
            ci.cancel();
        }
    }
}
