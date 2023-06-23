package com.github.suel_ki.snuffles.client;

import com.github.suel_ki.snuffles.client.block.ClientBlockInit;
import com.github.suel_ki.snuffles.client.particle.SnufflesParticleFactories;
import com.github.suel_ki.snuffles.client.renderer.SnufflesModelLayers;
import com.github.suel_ki.snuffles.client.renderer.SnufflesRenderers;
import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class SnufflesClient  implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientBlockInit.init();
        SnufflesModelLayers.registerLayers();
        SnufflesRenderers.rendererEntityRenderers();
        SnufflesParticleFactories.registerParticleFactories();
    }

}
