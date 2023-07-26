package com.killer.killersblocksnstuff.common.Blocks.tileEntity;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.core.init.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;

public class KbnsBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, KBNS.MOD_ID);

    public static RegistryObject<BlockEntityType<VibraniumForgeBlockEntity>> VIBRANIUM_FORGE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("vibranium_forge_block_entity", () -> BlockEntityType.Builder.of(
                    VibraniumForgeBlockEntity::new, BlockInit.VIBRANIUM_FORGE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}