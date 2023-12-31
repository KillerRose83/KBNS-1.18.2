package com.killer.killersblocksnstuff.Blocks.tileEntity;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.init.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;

public class KbnsBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, KBNS.MOD_ID);

    public static RegistryObject<BlockEntityType<VibraniumForgeBlockEntity>> VIBRANIUM_FORGE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("vibranium_forge_block_entity", () -> BlockEntityType.Builder.of(
                    VibraniumForgeBlockEntity::new, BlockInit.VIBRANIUM_FORGE.get()).build(null));

    public static RegistryObject<BlockEntityType<SolarGeneratorBlockEntity>> SOLAR_GENERATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("solar_generator_block_entity", () -> BlockEntityType.Builder.of(
                    SolarGeneratorBlockEntity::new, BlockInit.SOLAR_GENERATOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}