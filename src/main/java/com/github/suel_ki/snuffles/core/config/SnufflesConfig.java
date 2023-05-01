package com.github.suel_ki.snuffles.core.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SnufflesConfig {

    public static final GsonConfigInstance<SnufflesConfig> INSTANCE = new GsonConfigInstance<>(SnufflesConfig.class, FabricLoader.getInstance().getConfigDir().resolve("snuffles.json"), GsonBuilder::setPrettyPrinting);

    @ConfigEntry
    public int snuffles_weight = 4;

    @ConfigEntry
    public boolean legacy_snuffle_model = false;

    /*public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var categoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("snuffles.title"));

            var scrollingGroup = OptionGroup.createBuilder();

            var snufflesWeightOption = Option.createBuilder(int.class)
                                    .name(Text.translatable("snuffles.option.snuffles_weight.title"))
                                    .tooltip(Text.translatable("snuffles.option.snuffles_weight.desc"))
                                    .binding(
                                            defaults.snuffles_weight,
                                            () -> config.snuffles_weight,
                                            value -> config.snuffles_weight = value
                                    )
                                    .controller(option -> new IntegerSliderController(option, 4, 0, 100))
                                    .build();

            scrollingGroup.option(snufflesWeightOption);

            categoryBuilder.group(scrollingGroup.build());
            return builder
                    .title(Text.translatable("snuffles.title"))
                    .category(categoryBuilder.build());
        }).generateScreen(parent);
    }*/
}
