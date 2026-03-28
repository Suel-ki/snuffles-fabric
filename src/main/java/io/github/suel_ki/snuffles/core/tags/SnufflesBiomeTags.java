package io.github.suel_ki.snuffles.core.tags;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class SnufflesBiomeTags {
    public static final TagKey<Biome> SNUFFLES_SPAWNABLE = makeTag("snuffles_spawns_in");

    private static TagKey<Biome> makeTag(String id) {
        return TagKey.create(Registries.BIOME, Snuffles.id(id));
    }
}
