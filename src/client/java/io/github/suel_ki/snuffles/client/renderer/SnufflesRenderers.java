package io.github.suel_ki.snuffles.client.renderer;


import io.github.suel_ki.snuffles.client.renderer.entity.SnuffleRenderer;
import io.github.suel_ki.snuffles.core.registry.SnufflesEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class SnufflesRenderers {

    public static void rendererEntityRenderers() {
        EntityRenderers.register(SnufflesEntityTypes.SNUFFLE, SnuffleRenderer::new);
    }
}