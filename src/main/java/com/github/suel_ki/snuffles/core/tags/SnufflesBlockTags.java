package com.github.suel_ki.snuffles.core.tags;


import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class SnufflesBlockTags {
    public static final TagKey<Block> SNUFFLES_SPAWNABLE_ON = makeTag("snuffles_spawnable_on");

    private static TagKey<Block> makeTag(String id) {
        return TagKey.of(Registry.BLOCK_KEY, Snuffles.id(id));
    }
}