package com.github.suel_ki.snuffles.common.block;

import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import com.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import com.github.suel_ki.snuffles.core.registry.SnufflesSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;

public class FrostyFluffBlock extends Block {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public FrostyFluffBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0.5F);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public @NotNull VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    /*@Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if ((entity.lastRenderX != entity.getX() || entity.lastRenderY != entity.getY() || entity.lastRenderZ != entity.getZ()) && world.getRandom().nextBoolean()) {
            if (world.isClient() && entity instanceof PlayerEntity)
                world.addParticle(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F);
            else if (!world.isClient())
                ((ServerWorld) world).spawnParticles(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), 0, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 1.0F);
        }

        super.onSteppedOn(world, pos, state, entity);
    }*/

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !entity.bypassesSteppingEffects() && !world.isClient()) {
            if (entity.lastRenderX != entity.getX() || entity.lastRenderY != entity.getY() || entity.lastRenderZ != entity.getZ()) {
                if (world.getRandom().nextInt(5) == 0) {
                    ((ServerWorld) world).spawnParticles(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), 0, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, MathHelper.nextBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 1.0F);
                }
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
                world.setBlockState(pos, SnufflesBlocks.SNUFFLE_FLUFF.getDefaultState());
                world.playSound(null, pos, SnufflesSoundEvents.FROSTY_FLUFF_THAW, SoundCategory.BLOCKS, 0.7F, 1.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
            }

            return ActionResult.success(world.isClient());
        }

        return super.onUse(state, world, pos, player, hand, hitResult);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.getBlockState(pos.down()).isOpaque())
            world.addParticle(SnufflesParticleTypes.SNOWFLAKE, pos.getX() + random.nextDouble(), pos.getY() - 0.1F, pos.getZ() + random.nextDouble(), 0.0F, 0.0F, 0.0F);
    }
}