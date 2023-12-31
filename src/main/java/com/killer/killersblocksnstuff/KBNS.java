package com.killer.killersblocksnstuff;

import com.killer.killersblocksnstuff.Blocks.screen.*;
import com.killer.killersblocksnstuff.Blocks.tileEntity.*;
import com.killer.killersblocksnstuff.init.*;
import com.killer.killersblocksnstuff.recipe.*;
import com.killer.killersblocksnstuff.world.structure.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.*;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KBNS.MOD_ID)
public class KBNS
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "kbns";

    public static final CreativeModeTab KBNS_TAB = new KbnsItemGroup("kbns_tab");

    public KBNS(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        SoundInit.SOUNDS.register(bus);
        KbnsStructures.register(bus);
        //FeatureInit.FEATURES.register(bus);
        //SurfaceBuilderInit.SURFACE_BUILDERS.register(bus);
        //BiomeInit.BIOMES.register(bus);
        //BiomeInit.registerBiomes();
        MinecraftForge.EVENT_BUS.register(this);
        KbnsMenuTypes.register(bus);
        KbnsBlockEntities.register(bus);
        KbnsRecipes.register(bus);
    }

   private void doClientStuff(final FMLClientSetupEvent event) {
       event.enqueueWork(() -> {
           MenuScreens.register(KbnsMenuTypes.VibraniumForgeMenu.get(), VibraniumForgeScreen::new);
           MenuScreens.register(KbnsMenuTypes.SolarGeneratorMenu.get(), SolarGeneratorScreen::new);
       });

   }

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
