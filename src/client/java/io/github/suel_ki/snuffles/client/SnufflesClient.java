package io.github.suel_ki.snuffles.client;

import io.github.suel_ki.snuffles.client.block.ClientBlockInit;
import io.github.suel_ki.snuffles.client.particle.SnufflesParticleFactories;
import io.github.suel_ki.snuffles.client.renderer.SnufflesModelLayers;
import io.github.suel_ki.snuffles.client.renderer.SnufflesRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

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
