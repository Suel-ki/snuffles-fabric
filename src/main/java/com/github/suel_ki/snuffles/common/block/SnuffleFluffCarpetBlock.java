package com.github.suel_ki.snuffles.common.block;


import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import org.jetbrains.annotations.NotNull;

public class SnuffleFluffCarpetBlock extends CarpetBlock {
    public SnuffleFluffCarpetBlock(Settings settings) {
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
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.isSnowingAt(world, pos))
            world.setBlockState(pos, SnufflesBlocks.FROSTY_FLUFF_CARPET.getDefaultState(), 2);
    }

    private boolean isSnowingAt(ServerWorld world, BlockPos pos) {
        if (!world.isRaining())
            return false;
        else if (!world.isSkyVisible(pos))
            return false;
        else if (world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos).getY() > pos.getY())
            return false;
        else
            return world.getBiome(pos).value().isCold(pos);
    }
}