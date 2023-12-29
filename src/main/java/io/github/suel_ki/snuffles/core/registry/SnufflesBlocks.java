package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import io.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffCarpetBlock;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class SnufflesBlocks {
    public static final Block SNUFFLE_FLUFF = new SnuffleFluffBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .ticksRandomly().strength(0.6F)
                    .sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF = new FrostyFluffBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE).instrument(Instrument.GUITAR)
                    .strength(0.6F).sounds(BlockSoundGroup.WOOL));
    public static final Block SNUFFLE_FLUFF_CARPET = new SnuffleFluffCarpetBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .ticksRandomly().noCollision()
                    .strength(0.1F).sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF_CARPET = new FrostyFluffCarpetBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .noCollision().strength(0.1F).sounds(BlockSoundGroup.WOOL));

    public static void init() {
        Registry.register(Registries.BLOCK, Snuffles.id("snuffle_fluff"), SNUFFLE_FLUFF);
        Registry.register(Registries.BLOCK, Snuffles.id("frosty_fluff"), FROSTY_FLUFF);
        Registry.register(Registries.BLOCK, Snuffles.id("snuffle_fluff_carpet"), SNUFFLE_FLUFF_CARPET);
        Registry.register(Registries.BLOCK, Snuffles.id("frosty_fluff_carpet"), FROSTY_FLUFF_CARPET);
    }

}