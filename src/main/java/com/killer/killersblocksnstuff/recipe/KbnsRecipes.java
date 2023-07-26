package com.killer.killersblocksnstuff.recipe;

import com.killer.killersblocksnstuff.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;

public class KbnsRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KBNS.MOD_ID);

    public static final RegistryObject<RecipeSerializer<VibraniumForgeRecipe>> FORGING_SERIALIZER =
            SERIALIZERS.register("forging", () -> VibraniumForgeRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
