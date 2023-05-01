package com.github.suel_ki.snuffles.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SnowflakeParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;

public class SnufflesSnowflakeParticle extends SnowflakeParticle {

    protected SnufflesSnowflakeParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteProvider spriteProvider) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed, spriteProvider);

        this.scale = 0.05F * (this.random.nextFloat() * this.random.nextFloat() + 1.0F);
        this.maxAge = 240 + random.nextInt(160);
        this.setColor(0.923F, 0.964F, 0.999F);
        this.gravityStrength = 0.08F;
        this.velocityY = ySpeed;
    }

    public void tick() {
        super.tick();

        if (!this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isEmpty())
            this.markDead();
        else if (this.onGround)
            this.age++;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SnufflesSnowflakeParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
        }
    }
}
