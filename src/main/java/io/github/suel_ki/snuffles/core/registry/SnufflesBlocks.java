package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import io.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffCarpetBlock;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public class SnufflesBlocks {
    public static final Block SNUFFLE_FLUFF = register("snuffle_fluff", SnuffleFluffBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
            .ticksRandomly().strength(0.6F)
            .sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF = register("frosty_fluff", FrostyFluffBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .strength(0.6F).sounds(BlockSoundGroup.WOOL));
    public static final Block SNUFFLE_FLUFF_CARPET = register("snuffle_fluff_carpet", SnuffleFluffCarpetBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .ticksRandomly().noCollision()
                    .strength(0.1F).sounds(BlockSoundGroup.WOOL));
    public static final Block FROSTY_FLUFF_CARPET = register("frosty_fluff_carpet", FrostyFluffCarpetBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.WHITE)
                    .noCollision().strength(0.1F).sounds(BlockSoundGroup.WOOL));

    private static <T extends Block> T register(String name, Function<Block.Settings, T> factory, Block.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Snuffles.id(name));
        T item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.BLOCK, key, item);
    }

    public static void init() {
    }

}