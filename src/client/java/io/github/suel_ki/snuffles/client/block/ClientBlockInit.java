package io.github.suel_ki.snuffles.client.block;

import io.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.fabricmc.fabric.impl.client.rendering.BlockRenderLayerMapImpl;
import net.minecraft.client.render.BlockRenderLayer;

public class ClientBlockInit extends SnufflesBlocks {

    public static void init() {
        BlockRenderLayerMapImpl.putBlock(SNUFFLE_FLUFF, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(FROSTY_FLUFF, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(SNUFFLE_FLUFF_CARPET, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(FROSTY_FLUFF_CARPET, BlockRenderLayer.CUTOUT);
    }
}
