package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.common.item.FuelBlockItem;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SnufflesItems {

    public static final Item SNUFFLE_FLUFF = new FuelBlockItem(SnufflesBlocks.SNUFFLE_FLUFF, 100, new Item.Settings());
    public static final Item FROSTY_FLUFF = new BlockItem(SnufflesBlocks.FROSTY_FLUFF, new Item.Settings());
    public static final Item SNUFFLE_FLUFF_CARPET = new FuelBlockItem(SnufflesBlocks.SNUFFLE_FLUFF_CARPET, 67,new Item.Settings());
    public static final Item FROSTY_FLUFF_CARPET = new BlockItem(SnufflesBlocks.FROSTY_FLUFF_CARPET, new Item.Settings());
    public static final Item SNUFFLE_SPAWN_EGG = new SpawnEggItem(SnufflesEntityTypes.SNUFFLE, 16777215, 7125720, new Item.Settings());

    public static void init() {
        Registry.register(Registries.ITEM, Snuffles.id("snuffle_fluff"), SNUFFLE_FLUFF);
        Registry.register(Registries.ITEM, Snuffles.id("frosty_fluff"), FROSTY_FLUFF);
        Registry.register(Registries.ITEM, Snuffles.id("snuffle_fluff_carpet"), SNUFFLE_FLUFF_CARPET);
        Registry.register(Registries.ITEM, Snuffles.id("frosty_fluff_carpet"), FROSTY_FLUFF_CARPET);
        Registry.register(Registries.ITEM, Snuffles.id("snuffle_spawn_egg"), SNUFFLE_SPAWN_EGG);
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
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(SNUFFLE_FLUFF, 100);
        fuelRegistry.add(SNUFFLE_FLUFF_CARPET, 67);
    }

}