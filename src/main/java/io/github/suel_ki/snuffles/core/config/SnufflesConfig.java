package io.github.suel_ki.snuffles.core.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import io.github.suel_ki.snuffles.core.Snuffles;
import net.fabricmc.loader.api.FabricLoader;

public class SnufflesConfig {

    public static ConfigClassHandler<SnufflesConfig> INSTANCE = ConfigClassHandler.createBuilder(SnufflesConfig.class)
            .id(Snuffles.id(Snuffles.MOD_ID))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("snuffles.json"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .build())
            .build();

    @SerialEntry(comment = "Spawning weight of Snuffles")
    public int snuffles_weight = 8;

    @SerialEntry
    public boolean legacy_snuffle_model = false;
}
