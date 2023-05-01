package com.github.suel_ki.snuffles.core.tags;

import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class SnufflesItemTags {
    public static final TagKey<Item> SNUFFLE_FOOD = makeTag("snuffle_food");

    private static TagKey<Item> makeTag(String id) {
        return  TagKey.of(Registry.ITEM_KEY, Snuffles.id(id));
    }
}