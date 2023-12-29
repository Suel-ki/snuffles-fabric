package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import io.github.suel_ki.snuffles.core.Snuffles;
import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.Heightmap;

public class SnufflesEntityTypes {

    public static final EntityType<Snuffle> SNUFFLE = FabricEntityTypeBuilder.createMob()
            .entityFactory(Snuffle::new)
            .dimensions(EntityDimensions.changing(1.2F, SnufflesConfig.INSTANCE.instance().legacy_snuffle_model ? 1.0F:1.2F))
            .trackRangeChunks(8)
            .defaultAttributes(Snuffle::createSnuffleAttributes)
            .spawnGroup(SpawnGroup.CREATURE)
            .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Snuffle::checkSnuffleSpawnRules)
            .build();

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, Snuffles.id("snuffle"), SNUFFLE);
    }
}