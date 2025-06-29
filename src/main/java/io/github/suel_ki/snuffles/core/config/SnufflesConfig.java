package io.github.suel_ki.snuffles.core.config;

import io.github.suel_ki.snuffles.core.Snuffles;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Snuffles.MOD_ID)
public class SnufflesConfig implements ConfigData {

    @Comment("Spawning weight of Snuffles")
    public int snuffles_weight = 8;

    public boolean legacy_snuffle_model = false;

    public static SnufflesConfig instance() {
        return AutoConfig.getConfigHolder(SnufflesConfig.class).getConfig();
    }


}
