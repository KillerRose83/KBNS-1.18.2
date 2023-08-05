package com.killer.killersblocksnstuff.world.feature;

import net.minecraft.core.*;
import net.minecraft.data.worldgen.placement.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.*;

public class KbnsPlacedFeatures {

    public static final Holder<PlacedFeature> TELEMBERITE_ORE_PLACED = PlacementUtils.register("telemberite_ore_placed",
            KbnsConfiguredFeatures.TELEMBERITE_ORE, KbnsPlacement.commonOrePlacement(3, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
}
