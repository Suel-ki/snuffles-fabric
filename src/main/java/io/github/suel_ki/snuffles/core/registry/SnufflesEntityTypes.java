package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import io.github.suel_ki.snuffles.core.Snuffles;
import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.Heightmap;

public class SnufflesEntityTypes {

    public static final RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Snuffles.id("snuffle"));

    public static final EntityType<Snuffle> SNUFFLE = FabricEntityType.Builder.createMob(Snuffle::new, SpawnGroup.CREATURE,
                    (mob) -> mob.defaultAttributes(Snuffle::createSnuffleAttributes).spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Snuffle::checkSnuffleSpawnRules))
            .dimensions(1.2F, SnufflesConfig.INSTANCE.instance().legacy_snuffle_model ? 1.0F : 1.2F)
            .maxTrackingRange(8)
            .build(key);

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, key, SNUFFLE);
    }
}