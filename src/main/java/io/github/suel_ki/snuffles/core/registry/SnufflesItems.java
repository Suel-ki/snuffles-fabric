package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.item.FuelBlockItem;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class SnufflesItems {

    public static final Item SNUFFLE_FLUFF = registerFuelBlockItem("snuffle_fluff", SnufflesBlocks.SNUFFLE_FLUFF, 100);
    public static final Item FROSTY_FLUFF = registerBlockItem("frosty_fluff",  SnufflesBlocks.FROSTY_FLUFF);
    public static final Item SNUFFLE_FLUFF_CARPET = registerFuelBlockItem("snuffle_fluff_carpet", SnufflesBlocks.SNUFFLE_FLUFF_CARPET, 67);
    public static final Item FROSTY_FLUFF_CARPET = registerBlockItem("frosty_fluff_carpet", SnufflesBlocks.FROSTY_FLUFF_CARPET);
    public static final Item SNUFFLE_SPAWN_EGG = register("snuffle_spawn_egg", settings -> new SpawnEggItem(SnufflesEntityTypes.SNUFFLE, settings), new Item.Settings());

    public static void init() {
    }

    private static <T extends Item> T register(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Snuffles.id(name));
        T item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    private static BlockItem registerBlockItem(String name, Block block) {
        return register(name, settings -> new BlockItem(block, settings), new Item.Settings().useBlockPrefixedTranslationKey());
    }

    private static FuelBlockItem registerFuelBlockItem(String name, Block block, int burnTime) {
        return register(name, settings -> new FuelBlockItem(block, burnTime, settings), new Item.Settings().useBlockPrefixedTranslationKey());
    }

    public static void addItemTooItemGroup() {
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
                entries.add(SNUFFLE_FLUFF);
                entries.add(FROSTY_FLUFF);
                entries.add(SNUFFLE_FLUFF_CARPET);
                entries.add(FROSTY_FLUFF_CARPET);
            });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.add(SNUFFLE_SPAWN_EGG));
    }

    public static void registerFuelTime() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(SNUFFLE_FLUFF, 100);
            builder.add(SNUFFLE_FLUFF_CARPET, 67);
        });
    }

}