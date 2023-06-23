package com.github.suel_ki.snuffles.client.block;

import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

public class ClientBlockInit extends SnufflesBlocks{

    public static void init() {
        BlockRenderLayerMapImpl.INSTANCE.putBlock(SNUFFLE_FLUFF, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(FROSTY_FLUFF, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(SNUFFLE_FLUFF_CARPET, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(FROSTY_FLUFF_CARPET, RenderLayer.getCutout());
    }
}
