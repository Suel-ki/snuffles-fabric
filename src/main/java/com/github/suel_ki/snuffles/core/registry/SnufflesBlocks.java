package com.github.suel_ki.snuffles.core.registry;

import com.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import com.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import com.github.suel_ki.snuffles.common.block.SnuffleFluffBlock;
import com.github.suel_ki.snuffles.common.block.SnuffleFluffCarpetBlock;
import com.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class SnufflesBlocks {
    public static final Block SNUFFLE_FLUFF = new SnuffleFluffBlock(
            AbstractBlock.Settings.of(Material.WOOL, MapColor.WHITE)
                    .ticksRandomly().strength(0.6F)
                    .sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF = new FrostyFluffBlock(
            AbstractBlock.Settings.of(Material.WOOL, MapColor.WHITE)
                    .strength(0.6F).sounds(BlockSoundGroup.WOOL));
    public static final Block SNUFFLE_FLUFF_CARPET = new SnuffleFluffCarpetBlock(
            AbstractBlock.Settings.of(
                    Material.CARPET, MapColor.WHITE)
                    .ticksRandomly().noCollision()
                    .strength(0.1F).sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF_CARPET = new FrostyFluffCarpetBlock(
            AbstractBlock.Settings.of(
                    Material.CARPET, MapColor.WHITE)
                    .noCollision().strength(0.1F).sounds(BlockSoundGroup.WOOL));

    public static void init() {
        Registry.register(Registry.BLOCK, Snuffles.id("snuffle_fluff"), SNUFFLE_FLUFF);
        Registry.register(Registry.BLOCK, Snuffles.id("frosty_fluff"), FROSTY_FLUFF);
        Registry.register(Registry.BLOCK, Snuffles.id("snuffle_fluff_carpet"), SNUFFLE_FLUFF_CARPET);
        Registry.register(Registry.BLOCK, Snuffles.id("frosty_fluff_carpet"), FROSTY_FLUFF_CARPET);
    }

    public static void clientInit() {
        BlockRenderLayerMap.INSTANCE.putBlock(SNUFFLE_FLUFF, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FROSTY_FLUFF, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SNUFFLE_FLUFF_CARPET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FROSTY_FLUFF_CARPET, RenderLayer.getCutout());
    }

}