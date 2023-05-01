package com.github.suel_ki.snuffles.client.particle;

import com.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class SnufflesParticleFactories {

    public static void registerParticleFactories() {
        registerFactory(SnufflesParticleTypes.SNOWFLAKE, SnufflesSnowflakeParticle.Factory::new);
    }

    private static <T extends ParticleEffect> void registerFactory(ParticleType<T> particle, ParticleFactoryRegistry.PendingParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(particle, factory);
    }
}