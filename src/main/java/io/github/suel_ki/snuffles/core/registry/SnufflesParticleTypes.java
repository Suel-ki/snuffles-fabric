package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class SnufflesParticleTypes {

    public static final SimpleParticleType SNOWFLAKE = FabricParticleTypes.simple(true);

    public static void init() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Snuffles.id("snowflake"), SNOWFLAKE);
    }
}