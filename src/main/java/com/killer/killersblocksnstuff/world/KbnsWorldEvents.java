package com.killer.killersblocksnstuff.world;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.world.gen.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

@Mod.EventBusSubscriber(modid = KBNS.MOD_ID)
public class KbnsWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        KbnsOreGeneration.generateOres(event);
    }
}
