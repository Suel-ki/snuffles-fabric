package io.github.suel_ki.snuffles.client.renderer.entity;


import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.client.renderer.SnufflesModelLayers;
import io.github.suel_ki.snuffles.client.renderer.entity.layers.SnuffleFluffLayer;
import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnuffleRenderer extends AgeableMobEntityRenderer<Snuffle, SnuffleEntityRenderState, SnuffleModel> {
    private static final Identifier[] SNUFFLE_LOCATIONS = {
            Snuffles.id("textures/entity/snuffle/snuffle_default.png"),
            Snuffles.id("textures/entity/snuffle/snuffle_sheepdog.png"),
            Snuffles.id("textures/entity/snuffle/snuffle_poro.png"),
            Snuffles.id("textures/entity/snuffle/snuffle_horseshoe.png")
    };
    private static final Identifier[] FROSTY_LOCATIONS = {
            Snuffles.id("textures/entity/snuffle/frosty_default.png"),
            Snuffles.id("textures/entity/snuffle/frosty_sheepdog.png"),
            Snuffles.id("textures/entity/snuffle/frosty_poro.png"),
            Snuffles.id("textures/entity/snuffle/frosty_horseshoe.png")
    };

    public SnuffleRenderer(EntityRendererFactory.Context context) {
        super(context, new SnuffleModel(context.getPart(SnufflesModelLayers.SNUFFLE)), new SnuffleModel(context.getPart(SnufflesModelLayers.SNUFFLE_BABY)), 0.7F);
        this.addFeature(new SnuffleFluffLayer<>(this));
    }

    @Override
    public SnuffleEntityRenderState createRenderState() {
        return new SnuffleEntityRenderState();
    }

    @Override
    public void updateRenderState(Snuffle snuffle, SnuffleEntityRenderState snuffleEntityRenderState, float f) {
        super.updateRenderState(snuffle, snuffleEntityRenderState, f);
        snuffleEntityRenderState.licking = snuffle.isLicking();
        snuffleEntityRenderState.frosty = snuffle.isFrosty();
        snuffleEntityRenderState.frosting = snuffle.isFrosting();
        snuffleEntityRenderState.fluff = snuffle.hasFluff();
        snuffleEntityRenderState.hairstyleId = snuffle.getHairstyleId();
        snuffleEntityRenderState.hairstyle = snuffle.getHairstyle();
    }

    @Override
    public Identifier getTexture(SnuffleEntityRenderState state) {
        int i = state.hairstyleId;
        return state.frosty ? FROSTY_LOCATIONS[i] : SNUFFLE_LOCATIONS[i];
    }
}