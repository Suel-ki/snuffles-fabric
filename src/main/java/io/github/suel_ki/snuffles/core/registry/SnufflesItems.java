package io.github.suel_ki.snuffles.core.registry;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;

import java.util.function.Function;

public class SnufflesItems {

    public static final Item SNUFFLE_FLUFF = registerFuelBlockItem("snuffle_fluff", SnufflesBlocks.SNUFFLE_FLUFF, 100);
    public static final Item FROSTY_FLUFF = registerBlockItem("frosty_fluff",  SnufflesBlocks.FROSTY_FLUFF);
    public static final Item SNUFFLE_FLUFF_CARPET = registerFuelBlockItem("snuffle_fluff_carpet", SnufflesBlocks.SNUFFLE_FLUFF_CARPET, 67);
    public static final Item FROSTY_FLUFF_CARPET = registerBlockItem("frosty_fluff_carpet", SnufflesBlocks.FROSTY_FLUFF_CARPET);
    public static final Item SNUFFLE_SPAWN_EGG = register("snuffle_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(SnufflesEntityTypes.SNUFFLE));

    public static void init() {
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Snuffles.id(name));
        T item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static BlockItem registerBlockItem(String name, Block block) {
        return register(name, settings -> new BlockItem(block, settings), new Item.Properties().useBlockDescriptionPrefix());
    }

    private static BlockItem registerFuelBlockItem(String name, Block block, int burnTime) {
        return register(name, settings -> new BlockItem(block, settings), new Item.Properties().useBlockDescriptionPrefix()
                .component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(burnTime), new ResolvableFloat.Constant(1))));
    }

    public static void addItemTooItemGroup() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.accept(SNUFFLE_FLUFF);
            entries.accept(FROSTY_FLUFF);
            entries.accept(SNUFFLE_FLUFF_CARPET);
            entries.accept(FROSTY_FLUFF_CARPET);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> entries.accept(SNUFFLE_SPAWN_EGG));
    }

}