package io.github.suel_ki.snuffles.core.tags;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SnufflesItemTags {
    public static final TagKey<Item> SNUFFLE_FOOD = makeTag("snuffle_food");

    private static TagKey<Item> makeTag(String id) {
        return  TagKey.create(Registries.ITEM, Snuffles.id(id));
    }
}