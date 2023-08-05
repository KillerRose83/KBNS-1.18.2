package com.killer.killersblocksnstuff.world.gen;

import com.killer.killersblocksnstuff.world.feature.*;
import net.minecraft.core.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.*;

import java.util.*;

public class KbnsOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(KbnsPlacedFeatures.TELEMBERITE_ORE_PLACED);
    }
}

