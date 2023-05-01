package com.github.suel_ki.snuffles.core.registry;

import com.github.suel_ki.snuffles.common.item.FuelBlockItem;
import com.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;

public class SnufflesItems {

    public static final Item SNUFFLE_FLUFF = new FuelBlockItem(SnufflesBlocks.SNUFFLE_FLUFF, 100, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item FROSTY_FLUFF = new BlockItem(SnufflesBlocks.FROSTY_FLUFF, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SNUFFLE_FLUFF_CARPET = new FuelBlockItem(SnufflesBlocks.SNUFFLE_FLUFF_CARPET, 67,new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item FROSTY_FLUFF_CARPET = new BlockItem(SnufflesBlocks.FROSTY_FLUFF_CARPET, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SNUFFLE_SPAWN_EGG = new SpawnEggItem(SnufflesEntityTypes.SNUFFLE, 16777215, 7125720, new FabricItemSettings().group(ItemGroup.MISC));

    public static void init() {
       Registry.register(Registry.ITEM, Snuffles.id("snuffle_fluff"), SNUFFLE_FLUFF);
       Registry.register(Registry.ITEM, Snuffles.id("frosty_fluff"), FROSTY_FLUFF);
       Registry.register(Registry.ITEM, Snuffles.id("snuffle_fluff_carpet"), SNUFFLE_FLUFF_CARPET);
       Registry.register(Registry.ITEM, Snuffles.id("frosty_fluff_carpet"), FROSTY_FLUFF_CARPET);
       Registry.register(Registry.ITEM, Snuffles.id("snuffle_spawn_egg"), SNUFFLE_SPAWN_EGG);
    }

    public static void registerFuelTime() {
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(SNUFFLE_FLUFF, 100);
        fuelRegistry.add(SNUFFLE_FLUFF_CARPET, 67);
    }

}