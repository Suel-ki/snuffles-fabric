package io.github.suel_ki.snuffles.common.block;

import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import io.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import io.github.suel_ki.snuffles.core.registry.SnufflesSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class FrostyFluffBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public FrostyFluffBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public @NotNull VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        super.fallOn(world, state, pos, entity, fallDistance * 0.5D);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (entity instanceof LivingEntity && !entity.isSteppingCarefully() && world.isClientSide()) {
            if (entity.xOld != entity.getX() || entity.yOld != entity.getY() || entity.zOld != entity.getZ()) {
                if (world.getRandom().nextInt(5) == 0) {
                    world.addParticle(SnufflesParticleTypes.SNOWFLAKE, entity.getX(), entity.getY(), entity.getZ(), Mth.randomBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F, 0.05F, Mth.randomBetween(world.getRandom(), -1.0F, 1.0F) * 0.083F);
                }
            }
        }

        super.entityInside(state, world, pos, entity, effectApplier, isPrecise);
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(Items.MAGMA_CREAM)) {
            if (!world.isClientSide()) {
                stack.consume(1, player);

                world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                world.setBlockAndUpdate(pos, SnufflesBlocks.SNUFFLE_FLUFF.defaultBlockState());
                world.playSound(null, pos, SnufflesSoundEvents.FROSTY_FLUFF_THAW, SoundSource.BLOCKS, 0.7F, 1.6F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.4F);
            }

            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, world, pos, player, hand, hitResult);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (!world.getBlockState(pos.below()).canOcclude())
            world.addParticle(SnufflesParticleTypes.SNOWFLAKE, pos.getX() + random.nextDouble(), pos.getY() - 0.1F, pos.getZ() + random.nextDouble(), 0.0F, 0.0F, 0.0F);
    }
}