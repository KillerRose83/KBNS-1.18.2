package com.killer.killersblocksnstuff.event;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.recipe.*;
import net.minecraft.core.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

@Mod.EventBusSubscriber(modid = KBNS.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KbnsEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, VibraniumForgeRecipe.Type.ID, VibraniumForgeRecipe.Type.INSTANCE);
    }
}