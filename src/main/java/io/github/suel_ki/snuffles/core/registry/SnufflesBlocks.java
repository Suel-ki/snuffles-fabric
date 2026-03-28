package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.block.FrostyFluffBlock;
import io.github.suel_ki.snuffles.common.block.FrostyFluffCarpetBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffBlock;
import io.github.suel_ki.snuffles.common.block.SnuffleFluffCarpetBlock;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class SnufflesBlocks {
    public static final Block SNUFFLE_FLUFF = register("snuffle_fluff", SnuffleFluffBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR).ignitedByLava().randomTicks().strength(0.6F).sound(SoundType.WOOL));
    public static final Block FROSTY_FLUFF = register("frosty_fluff", FrostyFluffBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR).ignitedByLava().strength(0.6F).sound(SoundType.WOOL));
    public static final Block SNUFFLE_FLUFF_CARPET = register("snuffle_fluff_carpet", SnuffleFluffCarpetBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR).ignitedByLava().randomTicks().noCollision().strength(0.1F).sound(SoundType.WOOL));
    public static final Block FROSTY_FLUFF_CARPET = register("frosty_fluff_carpet", FrostyFluffCarpetBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.GUITAR).ignitedByLava().noCollision().strength(0.1F).sound(SoundType.WOOL));

    private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Snuffles.id(name));
        T item = factory.apply(settings.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, item);
    }

    public static void init() {
    }

}