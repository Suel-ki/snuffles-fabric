package com.github.suel_ki.snuffles.core.misc;


import com.github.suel_ki.snuffles.core.registry.SnufflesBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;

public class SnufflesFlammables {
    public static void registerFlammables() {
        registerFlammable(SnufflesBlocks.SNUFFLE_FLUFF, 30, 60);
        registerFlammable(SnufflesBlocks.FROSTY_FLUFF, 30, 100);
        registerFlammable(SnufflesBlocks.SNUFFLE_FLUFF_CARPET, 60, 20);
        registerFlammable(SnufflesBlocks.FROSTY_FLUFF_CARPET, 60, 100);
    }

    private static void registerFlammable(Block block, int flameOdds, int burnOdds) {
        FlammableBlockRegistry.getDefaultInstance().add(block, flameOdds, burnOdds);
    }
}