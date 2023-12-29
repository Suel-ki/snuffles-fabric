package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SnufflesParticleTypes {

    public static final DefaultParticleType SNOWFLAKE = FabricParticleTypes.simple(true);

    public static void init() {
        Registry.register(Registries.PARTICLE_TYPE, Snuffles.id("snowflake"), SNOWFLAKE);
    }
}