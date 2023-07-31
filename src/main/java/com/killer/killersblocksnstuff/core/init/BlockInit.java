package com.killer.killersblocksnstuff.core.init;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.common.Blocks.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.*;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KBNS.MOD_ID);

    public static final RegistryObject<Block> VIBRANIUM_ORE  = BLOCKS.register("vibranium_ore", VibraniumOre::new);
    public static final RegistryObject<Block> VIBRANIUM_BLOCK  = BLOCKS.register("vibranium_block", VibraniumBlock::new);
    public static final RegistryObject<Block> REFINED_VIBRANIUM_POWDER_BLOCK = BLOCKS.register("refined_vibranium_powder_block", RefinedVibraniumPowderBlock::new);
    public static final RegistryObject<Block> VOID_STONE = BLOCKS.register("void_stone", VoidStone::new);
    public static final RegistryObject<Block> CONCRETE_LAMP_WHITE = BLOCKS.register("concrete_lamp_white", ConcreteLampWhite::new);
    public static final RegistryObject <Block>CONCRETE_LAMP_ORANGE = BLOCKS.register("concrete_lamp_orange", ConcreteLampOrange::new);
    public static final RegistryObject<Block> CONCRETE_LAMP_RED = BLOCKS.register("concrete_lamp_red", ConcreteLampRed::new);
    public static final RegistryObject<Block> VIBRANIUM_FORGE = BLOCKS.register("vibranium_forge", VibraniumForgeBlock::new);
    public static final RegistryObject<Block> SOLAR_GENERATOR = BLOCKS.register("solar_generator", SolarGeneratorBlock::new);









}

