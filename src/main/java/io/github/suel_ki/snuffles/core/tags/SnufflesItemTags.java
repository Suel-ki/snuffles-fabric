package io.github.suel_ki.snuffles.core.tags;

import io.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class SnufflesItemTags {
    public static final TagKey<Item> SNUFFLE_FOOD = makeTag("snuffle_food");

    private static TagKey<Item> makeTag(String id) {
        return  TagKey.of(RegistryKeys.ITEM, Snuffles.id(id));
    }
}