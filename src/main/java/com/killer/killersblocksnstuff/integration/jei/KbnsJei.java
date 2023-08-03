package com.killer.killersblocksnstuff.integration.jei;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.recipe.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.*;

@JeiPlugin
public class KbnsJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(KBNS.MOD_ID, "jei_plugin");
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                ForgeRecipeJei(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<VibraniumForgeRecipe> recipes = rm.getAllRecipesFor(VibraniumForgeRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(ForgeRecipeJei.UID, VibraniumForgeRecipe.class), recipes);
    }
}
