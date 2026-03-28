package io.github.suel_ki.snuffles.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.client.renderer.SnufflesModelLayers;
import io.github.suel_ki.snuffles.client.renderer.entity.layers.SnuffleFluffLayer;
import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class SnuffleRenderer extends MobRenderer<Snuffle, SnuffleEntityRenderState, SnuffleModel> {
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

    private final SnuffleModel adultModel;
    private final SnuffleModel babyModel;

    public SnuffleRenderer(EntityRendererProvider.Context context) {
        super(context, new SnuffleModel(context.bakeLayer(SnufflesModelLayers.SNUFFLE)), 0.7F);
        this.addLayer(new SnuffleFluffLayer<>(this));
        adultModel = new SnuffleModel(context.bakeLayer(SnufflesModelLayers.SNUFFLE));
        babyModel = new SnuffleModel(context.bakeLayer(SnufflesModelLayers.SNUFFLE_BABY));
    }

    @Override
    public void submit(final SnuffleEntityRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        this.model = state.isBaby ? this.babyModel : this.adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public SnuffleEntityRenderState createRenderState() {
        return new SnuffleEntityRenderState();
    }

    @Override
    public void extractRenderState(Snuffle snuffle, SnuffleEntityRenderState snuffleEntityRenderState, float f) {
        super.extractRenderState(snuffle, snuffleEntityRenderState, f);
        snuffleEntityRenderState.licking = snuffle.isLicking();
        snuffleEntityRenderState.frosty = snuffle.isFrosty();
        snuffleEntityRenderState.frosting = snuffle.isFrosting();
        snuffleEntityRenderState.fluff = snuffle.hasFluff();
        snuffleEntityRenderState.hairstyleId = snuffle.getHairstyleId();
        snuffleEntityRenderState.hairstyle = snuffle.getHairstyle();
    }

    @Override
    public Identifier getTextureLocation(SnuffleEntityRenderState state) {
        int i = state.hairstyleId;
        return state.frosty ? FROSTY_LOCATIONS[i] : SNUFFLE_LOCATIONS[i];
    }
}