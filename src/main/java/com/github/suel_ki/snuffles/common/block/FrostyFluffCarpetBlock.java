package com.github.suel_ki.snuffles.common.block;

import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import com.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import com.github.suel_ki.snuffles.core.registry.SnufflesSoundEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;

public class FrostyFluffCarpetBlock extends CarpetBlock {
    protected static final Box TOUCH_AABB = new Box(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public FrostyFluffCarpetBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public @NotNull VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      /*  if (!world.isClient()) {
            Box aabb = TOUCH_AABB.offset(pos);
            if (world.getOtherEntities(null, aabb).contains(entity)) {
                if (!entity.bypassesSteppingEffects() && (entity.lastRenderX != entity.getX() || entity.lastRenderY != entity.getY() || entity.lastRenderZ != entity.getZ()) && world.getRandom().nextFloat() <= 0.3F)
                    ((ServerWorld) world).spawnParticles(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), 0, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 1.0F);
            }
        }*/
        if (!world.isClient() && !entity.bypassesSteppingEffects())
            if (entity.lastRenderX != entity.getX() || entity.lastRenderY != entity.getY() || entity.lastRenderZ != entity.getZ())
                if (world.getRandom().nextInt(5) == 0) {
                    Box aabb = TOUCH_AABB.offset(pos);
                    if (world.getOtherEntities(null, aabb).contains(entity)) {
                        ((ServerWorld) world).spawnParticles(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), 0, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 1.0F);
                    }
                }

        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.MAGMA_CREAM)) {
            if (!world.isClient()) {
                if (!player.isCreative())
                    stack.decrement(1);

                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                world.setBlockState(pos, SnufflesBlocks.SNUFFLE_FLUFF_CARPET.getDefaultState());
                world.playSound(null, pos, SnufflesSoundEvents.FROSTY_FLUFF_THAW, SoundCategory.BLOCKS, 0.7F, 1.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
            }

            return ActionResult.success(world.isClient());
        }

        return super.onUse(state, world, pos, player, hand, hitResult);
    }
}