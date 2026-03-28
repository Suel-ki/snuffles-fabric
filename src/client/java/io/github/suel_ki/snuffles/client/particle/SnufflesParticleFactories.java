package io.github.suel_ki.snuffles.client.particle;

import io.github.suel_ki.snuffles.core.registry.SnufflesParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

public class SnufflesParticleFactories {

    public static void registerParticleFactories() {
        registerFactory(SnufflesParticleTypes.SNOWFLAKE, SnufflesSnowflakeParticle.Factory::new);
    }

    private static <T extends ParticleOptions> void registerFactory(ParticleType<T> particle, ParticleProviderRegistry.PendingParticleProvider<T> factory) {
        ParticleProviderRegistry.getInstance().register(particle, factory);
    }
}