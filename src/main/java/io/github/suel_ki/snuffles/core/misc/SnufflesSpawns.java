package io.github.suel_ki.snuffles.core.misc;

import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import io.github.suel_ki.snuffles.core.registry.SnufflesEntityTypes;
import io.github.suel_ki.snuffles.core.tags.SnufflesBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class SnufflesSpawns {

    public static void registerSpawns() {
        int weight = SnufflesConfig.INSTANCE.instance().snuffles_weight;
        addEntityToBiome(SpawnGroup.CREATURE, SnufflesEntityTypes.SNUFFLE,
                weight > 0 ? weight : 8, 4, 4);
    }

    public static void addEntityToBiome(SpawnGroup spawnGroup, EntityType<?> entityType,
                                        int weight, int minGroupSize, int maxGroupSize) {
        BiomeModifications.addSpawn(BiomeSelectors.tag(SnufflesBiomeTags.SNUFFLES_SPAWNABLE), spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
    }
}
