package com.github.suel_ki.snuffles.core.mixin.common;

import com.github.suel_ki.snuffles.common.block.SnuffleFluffBlock;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @ModifyReturnValue(
            method = "getBlockBreakingSpeed",
            at = @At(value = "RETURN")
    )
    public float getBlockBreakingSpeed(float original, BlockState state) {
        if (state.getBlock() instanceof SnuffleFluffBlock
                && ((PlayerEntity)(Object)this).getMainHandStack().isIn(ConventionalItemTags.SHEARS))
            original *= 5.0F;
        return original;
    }

    @Inject(
            method = "getBlockBreakingSpeed",
            at = @At(value = "RETURN")
    )
    public void getBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir) {
    }

}
