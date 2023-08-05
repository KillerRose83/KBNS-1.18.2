package com.killer.killersblocksnstuff.world.feature;

import com.killer.killersblocksnstuff.init.*;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.*;

public class KbnsConfiguredFeatures {

    public static final RuleTest END_STONE = new BlockMatchTest(Blocks.END_STONE);

    public static final List<OreConfiguration.TargetBlockState> END_TELEMBERITE_ORES = List.of(
            OreConfiguration.target(END_STONE, BlockInit.VIBRANIUM_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TELEMBERITE_ORE = FeatureUtils.register("telemberite_ore",
            Feature.ORE, new OreConfiguration(END_TELEMBERITE_ORES, 3));

}
