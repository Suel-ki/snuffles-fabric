package io.github.suel_ki.snuffles.core.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.suel_ki.snuffles.common.entity.animal.Snuffle;
import io.github.suel_ki.snuffles.core.Snuffles;
import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SnufflesEntityTypes {

    public static final ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Snuffles.id("snuffle"));

    public static final EntityType<Snuffle> SNUFFLE = FabricEntityType.Builder.createMob(Snuffle::new, MobCategory.CREATURE,
                    (mob) -> mob.defaultAttributes(Snuffle::createSnuffleAttributes).spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Snuffle::checkSnuffleSpawnRules))
            .sized(1.2F, SnufflesConfig.instance().legacy_snuffle_model ? 1.0F : 1.2F)
            .clientTrackingRange(8)
            .build(key);

    public static void init() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, key, SNUFFLE);
        Registry.register(
                BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE,
                Snuffles.id("frosty"),
                FrostyPredicate.CODEC
        );
    }

    public record FrostyPredicate(boolean frosty) implements EntitySubPredicate {

        public static final Codec<FrostyPredicate> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.BOOL.fieldOf("frosty").forGetter(FrostyPredicate::frosty)
                ).apply(instance, FrostyPredicate::new)
        );

        @Override
        public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
            if (entity instanceof Snuffle snuffle) {
                return snuffle.isFrosty() == this.frosty;
            }
            return false;
        }
    }
}