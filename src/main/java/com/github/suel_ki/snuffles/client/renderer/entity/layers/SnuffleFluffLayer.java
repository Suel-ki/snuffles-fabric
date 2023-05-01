package com.github.suel_ki.snuffles.client.renderer.entity.layers;

import com.github.suel_ki.snuffles.client.model.SnuffleModel;
import com.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;

public class SnuffleFluffLayer<T extends Snuffle, M extends SnuffleModel<T>> extends FeatureRenderer<T, M> {

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

    public void render(MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight, T snuffle, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!snuffle.isBaby() && snuffle.hasFluff()) {
            if (snuffle.isInvisible()) {
                MinecraftClient minecraft = MinecraftClient.getInstance();
                boolean flag = minecraft.hasOutline(snuffle);
                if (flag) {
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderLayer.getOutline(this.getTextureLocation(snuffle)));
                    this.getContextModel().render(matrixStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlay(snuffle, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
                }
            } else {
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTextureLocation(snuffle)));
                this.getContextModel().render(matrixStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlay(snuffle, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    public Identifier getTextureLocation(Snuffle snuffle) {
        Pair<Identifier, Identifier> fluff;
        switch (snuffle.getHairstyle()) {
            case HORSESHOE -> fluff = FLUFF_HORSESHOE;
            case SHEEPDOG -> fluff = FLUFF_SHEEPDOG;
            default -> fluff = FLUFF;
        }
        return snuffle.isFrosty() ? fluff.getRight() : fluff.getLeft();
    }
}