package com.github.suel_ki.snuffles.core.tags;

import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class SnufflesBiomeTags {
    public static final TagKey<Biome> SNUFFLES_SPAWNABLE = makeTag("snuffles_spawns_in");

    private static TagKey<Biome> makeTag(String id) {
        return TagKey.of(Registry.BIOME_KEY, Snuffles.id(id));
    }
}
