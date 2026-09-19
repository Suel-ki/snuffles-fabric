package io.github.suel_ki.snuffles.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.apache.commons.lang3.tuple.Pair;

public class SnuffleFluffLayer<T extends SnuffleEntityRenderState, M extends SnuffleModel> extends RenderLayer<T, M> {

    private static final Pair<Identifier, Identifier> FLUFF = Pair.of(
            Snuffles.id("textures/entity/snuffle/snuffle_fluff.png"),
            Snuffles.id("textures/entity/snuffle/frosty_fluff.png")
    );
    private static final Pair<Identifier, Identifier> FLUFF_HORSESHOE = Pair.of(
            Snuffles.id("textures/entity/snuffle/snuffle_fluff_horseshoe.png"),
            Snuffles.id("textures/entity/snuffle/frosty_fluff_horseshoe.png")
    );
    private static final Pair<Identifier, Identifier> FLUFF_SHEEPDOG = Pair.of(
            Snuffles.id("textures/entity/snuffle/snuffle_fluff_sheepdog.png"),
            Snuffles.id("textures/entity/snuffle/frosty_fluff_sheepdog.png")
    );

    public SnuffleFluffLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, final SubmitNodeCollector collector, int light, T state, float yRot, float xRot) {
        if (!state.isBaby && state.fluff) {
            if (state.isInvisible) {
                boolean flag = state.appearsGlowing();
                if (flag) {
                    collector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.outline(this.getTextureLocation(state)), light, LivingEntityRenderer.getOverlayCoords(state, 0.0F), state.outlineColor);
                }
            } else {
                collector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.entityCutout(this.getTextureLocation(state)), light, LivingEntityRenderer.getOverlayCoords(state, 0.0F), state.outlineColor);
            }
        }
    }

    public Identifier getTextureLocation(SnuffleEntityRenderState state) {
        Pair<Identifier, Identifier> fluff;
        switch (state.hairstyle) {
            case HORSESHOE -> fluff = FLUFF_HORSESHOE;
            case SHEEPDOG -> fluff = FLUFF_SHEEPDOG;
            default -> fluff = FLUFF;
        }
        return state.frosty ? fluff.getRight() : fluff.getLeft();
    }
}