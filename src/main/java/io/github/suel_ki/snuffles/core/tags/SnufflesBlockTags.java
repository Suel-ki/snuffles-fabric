package io.github.suel_ki.snuffles.core.tags;


import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SnufflesBlockTags {
    public static final TagKey<Block> SNUFFLES_SPAWNABLE_ON = makeTag("snuffles_spawnable_on");

    private static TagKey<Block> makeTag(String id) {
        return TagKey.create(Registries.BLOCK, Snuffles.id(id));
    }
}