package com.killer.killersblocksnstuff;

import com.killer.killersblocksnstuff.core.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KBNS.MOD_ID)
public class KBNS
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "kbns";

    public static final CreativeModeTab KBNS_TAB = new KbnsItemGroup("killersblocksnstuff_tab");

    public KBNS(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        //FeatureInit.FEATURES.register(bus);
        //SurfaceBuilderInit.SURFACE_BUILDERS.register(bus);
       // BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
        MinecraftForge.EVENT_BUS.register(this);
        //KbnsContainers.register(bus);
        // KbnsTileEntities.register(bus);
       //KbnsRecipeTypes.register(bus);
    }

   // private void doClientStuff(final FMLClientSetupEvent event) {
    //    event.enqueueWork(() -> {
    //        MenuScreens.register(KbnsContainers.VIBRANIUM_FORGE_CONTAINER.get(), VibraniumForgeScreen::new);
     //   });

  //  }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
