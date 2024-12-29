package io.github.suel_ki.snuffles.client.renderer.entity.layers;

import io.github.suel_ki.snuffles.client.model.SnuffleModel;
import io.github.suel_ki.snuffles.client.renderer.entity.state.SnuffleEntityRenderState;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
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
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T state, float limbAngle, float limbDistance) {
        if (!state.baby && state.fluff) {
            if (state.invisible) {
                boolean flag = state.hasOutline;
                if (flag) {
                    VertexConsumer vertexconsumer = vertexConsumers.getBuffer(RenderLayer.getOutline(this.getTextureLocation(state)));
                    this.getContextModel().render(matrices, vertexconsumer, light, LivingEntityRenderer.getOverlay(state, 0.0F), packRGB(new Vector3f(0.0F, 0.0F, 0.0F)));
                }
            } else {
                VertexConsumer vertexconsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTextureLocation(state)));
                this.getContextModel().render(matrices, vertexconsumer, light, LivingEntityRenderer.getOverlay(state, 0.0F), packRGB(new Vector3f(1.0F, 1.0F, 1.0F)));
            }
        }
    }

    static int packRGB(Vector3f color) {
        int red = (int) (color.x() * 255.0F);
        int green = (int) (color.y() * 255.0F);
        int blue = (int) (color.z() * 255.0F);

        return (red << 16) | (green << 8) | blue;
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