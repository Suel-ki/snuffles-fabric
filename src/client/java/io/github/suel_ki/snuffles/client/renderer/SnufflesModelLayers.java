package io.github.suel_ki.snuffles.client.renderer;

import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class SnufflesModelLayers {
    public static ModelLayerLocation SNUFFLE = new ModelLayerLocation(Snuffles.id("snuffle"), "main");
    public static ModelLayerLocation SNUFFLE_BABY = new ModelLayerLocation(Snuffles.id("snuffle_baby"), "main");

    public static void registerLayers() {
        ModelLayerRegistry.registerModelLayer(SNUFFLE, () -> SnuffleModel.getTexturedModelData(false));
        ModelLayerRegistry.registerModelLayer(SNUFFLE_BABY, () -> SnuffleModel.getTexturedModelData(true));
    }

}