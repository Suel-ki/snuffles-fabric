package io.github.suel_ki.snuffles.core.tags;


import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class SnufflesBlockTags {
    public static final TagKey<Block> SNUFFLES_SPAWNABLE_ON = makeTag("snuffles_spawnable_on");

    private static TagKey<Block> makeTag(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Snuffles.id(id));
    }
}