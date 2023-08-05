package com.killer.killersblocksnstuff.init;

import com.killer.killersblocksnstuff.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraftforge.registries.*;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KBNS.MOD_ID);


    public static RegistryObject<SoundEvent> VIBRANIUM_FORGE_START = registerSoundEvent("vibranium_forge_start");
    public static RegistryObject<SoundEvent> VIBRANIUM_FORGE_RUNNING = registerSoundEvent("vibranium_forge_running");
    public static RegistryObject<SoundEvent> VIBRANIUM_FORGE_STOP = registerSoundEvent("vibranium_forge_stop");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(KBNS.MOD_ID, name)));
    }

}

