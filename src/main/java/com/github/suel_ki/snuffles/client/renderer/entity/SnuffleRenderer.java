package com.github.suel_ki.snuffles.client.renderer.entity;


import com.github.suel_ki.snuffles.client.model.SnuffleModel;
import com.github.suel_ki.snuffles.client.renderer.SnufflesModelLayers;
import com.github.suel_ki.snuffles.client.renderer.entity.layers.SnuffleFluffLayer;
import com.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SnuffleRenderer extends MobEntityRenderer<Snuffle, SnuffleModel<Snuffle>> {
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
        super(context, new SnuffleModel<>(context.getPart(SnufflesModelLayers.SNUFFLE)), 0.7F);
        this.addFeature(new SnuffleFluffLayer<>(this));
    }

    @Override
    public Identifier getTexture(Snuffle snuffle) {
        int i = snuffle.getHairstyleId();
        return snuffle.isFrosty() ? FROSTY_LOCATIONS[i] : SNUFFLE_LOCATIONS[i];
    }
}