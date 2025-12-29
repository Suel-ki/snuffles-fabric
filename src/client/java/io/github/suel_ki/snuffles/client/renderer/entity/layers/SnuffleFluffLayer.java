package io.github.suel_ki.snuffles.client.renderer.entity.layers;

import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Vector3f;

public class SnuffleFluffLayer<T extends SnuffleEntityRenderState, M extends SnuffleModel> extends FeatureRenderer<T, M> {

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

    public SnuffleFluffLayer(FeatureRendererContext<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, T state, float limbAngle, float limbDistance) {
        if (!state.baby && state.fluff) {
            if (state.invisible) {
                boolean flag = state.hasOutline();
                if (flag) {
                    queue.submitModel(this.getContextModel(), state, matrices, RenderLayer.getOutline(this.getTextureLocation(state)), light, LivingEntityRenderer.getOverlay(state, 0.0F), state.outlineColor, null);
                }
            } else {
                queue.submitModel(this.getContextModel(), state, matrices, RenderLayer.getEntityCutoutNoCull(this.getTextureLocation(state)), light, LivingEntityRenderer.getOverlay(state, 0.0F), state.outlineColor, null);
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