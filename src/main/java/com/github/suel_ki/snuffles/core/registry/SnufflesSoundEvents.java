package com.github.suel_ki.snuffles.core.registry;

import com.github.suel_ki.snuffles.core.Snuffles;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SnufflesSoundEvents {

    public static final SoundEvent FROSTY_FLUFF_THAW = register("block.frosty_fluff.thaw");
    public static final SoundEvent SNUFFLE_AMBIENT = register("entity.snuffle.ambient");
    public static final SoundEvent SNUFFLE_DEATH = register("entity.snuffle.death");
    public static final SoundEvent SNUFFLE_EAT = register("entity.snuffle.eat");
    public static final SoundEvent SNUFFLE_HURT = register("entity.snuffle.hurt");
    public static final SoundEvent SNUFFLE_SHAKE = register("entity.snuffle.shake");
    public static final SoundEvent SNUFFLE_SHEAR = register("entity.snuffle.shear");
    public static final SoundEvent SNUFFLE_STEP = register("entity.snuffle.step");
    public static final SoundEvent SNUFFLE_STYLE = register("entity.snuffle.style");
    public static final SoundEvent SNUFFLE_THAW = register("entity.snuffle.thaw");

    private static SoundEvent register(String name) {
        return Registry.register(Registry.SOUND_EVENT, name, new SoundEvent(Snuffles.id(name)));
    }

    public static void init() {}
}