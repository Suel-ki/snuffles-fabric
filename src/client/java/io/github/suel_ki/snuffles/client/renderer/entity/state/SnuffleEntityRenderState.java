package io.github.suel_ki.snuffles.client.renderer.entity.state;

import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class SnuffleEntityRenderState extends LivingEntityRenderState {
    public boolean licking;
    public boolean frosty;
    public boolean frosting;
    public boolean fluff;
    public int hairstyleId;
    public Snuffle.Hairstyle hairstyle;
}
