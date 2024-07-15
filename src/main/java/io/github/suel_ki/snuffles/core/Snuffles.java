package io.github.suel_ki.snuffles.core;

import io.github.suel_ki.snuffles.core.config.SnufflesConfig;
import io.github.suel_ki.snuffles.core.dispenser.SnufflesShearsDispenseItemBehavior;
import io.github.suel_ki.snuffles.core.misc.SnufflesFlammables;
import io.github.suel_ki.snuffles.core.misc.SnufflesSpawns;
import io.github.suel_ki.snuffles.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snuffles implements ModInitializer {

    public static final String MOD_ID = "snuffles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SnufflesConfig.INSTANCE.load();

        SnufflesItems.init();
        SnufflesBlocks.init();
        SnufflesEntityTypes.init();
        SnufflesParticleTypes.init();
        SnufflesSoundEvents.init();

        SnufflesItems.addItemTooItemGroup();

        SnufflesItems.registerFuelTime();
        SnufflesSpawns.registerSpawns();
        SnufflesFlammables.registerFlammables();
        SnufflesShearsDispenseItemBehavior.registerDispenserBehaviors();
    }

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID, id);
    }
}
