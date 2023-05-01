package com.github.suel_ki.snuffles.core;

import com.github.suel_ki.snuffles.core.config.SnufflesConfig;
import com.github.suel_ki.snuffles.core.dispenser.SnufflesShearsDispenseItemBehavior;
import com.github.suel_ki.snuffles.core.misc.SnufflesFlammables;
import com.github.suel_ki.snuffles.core.misc.SnufflesSpawns;
import com.github.suel_ki.snuffles.core.registry.*;
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

        SnufflesItems.registerFuelTime();
        SnufflesSpawns.registerSpawns();
        SnufflesFlammables.registerFlammables();
        SnufflesShearsDispenseItemBehavior.registerDispenserBehaviors();
    }

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }
}
