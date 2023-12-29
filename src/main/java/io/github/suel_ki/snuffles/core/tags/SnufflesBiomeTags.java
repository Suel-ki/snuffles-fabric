package io.github.suel_ki.snuffles.core.tags;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class SnufflesBiomeTags {
    public static final TagKey<Biome> SNUFFLES_SPAWNABLE = makeTag("snuffles_spawns_in");

    private static TagKey<Biome> makeTag(String id) {
        return TagKey.of(RegistryKeys.BIOME, Snuffles.id(id));
    }
}
