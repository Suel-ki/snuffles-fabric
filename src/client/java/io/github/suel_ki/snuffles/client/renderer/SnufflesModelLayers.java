package io.github.suel_ki.snuffles.client.renderer;

import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class SnufflesModelLayers {
    public static EntityModelLayer SNUFFLE = new EntityModelLayer(Snuffles.id("snuffle"), "main");
    public static EntityModelLayer SNUFFLE_BABY = new EntityModelLayer(Snuffles.id("snuffle_baby"), "main");

    public static void registerLayers() {
        EntityModelLayerRegistry.registerModelLayer(SNUFFLE, () -> SnuffleModel.getTexturedModelData(false));
        EntityModelLayerRegistry.registerModelLayer(SNUFFLE_BABY, () -> SnuffleModel.getTexturedModelData(true));
    }

}