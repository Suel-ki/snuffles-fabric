package com.github.suel_ki.snuffles.client.renderer;


import com.github.suel_ki.snuffles.client.renderer.entity.SnuffleRenderer;
import com.github.suel_ki.snuffles.core.registry.SnufflesEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SnufflesRenderers {

    public static void rendererEntityRenderers() {
        EntityRendererRegistry.register(SnufflesEntityTypes.SNUFFLE, SnuffleRenderer::new);
    }
}