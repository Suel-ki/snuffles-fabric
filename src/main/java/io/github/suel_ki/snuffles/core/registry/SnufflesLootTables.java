package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class SnufflesLootTables {
    public static final ResourceKey<LootTable> SNUFFLE_SHEAR = ResourceKey.create(Registries.LOOT_TABLE, Snuffles.id("shearing/snuffle_shear"));
}
