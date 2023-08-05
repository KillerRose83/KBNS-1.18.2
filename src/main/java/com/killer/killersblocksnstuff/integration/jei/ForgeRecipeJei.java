package com.killer.killersblocksnstuff.integration.jei;

import com.killer.killersblocksnstuff.*;
import com.killer.killersblocksnstuff.init.*;
import com.killer.killersblocksnstuff.recipe.*;
import mezz.jei.api.constants.*;
import mezz.jei.api.gui.builder.*;
import mezz.jei.api.gui.drawable.*;
import mezz.jei.api.helpers.*;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;

public class ForgeRecipeJei implements IRecipeCategory<VibraniumForgeRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(KBNS.MOD_ID, "forge");
    public static final ResourceLocation TEXTURE = new ResourceLocation(KBNS.MOD_ID, "textures/gui/vibranium_forge_gui.png");


    private final IDrawable background;
    private final IDrawable icon;
    private final String title = "Forging";

    public ForgeRecipeJei(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.VIBRANIUM_FORGE.get()));
    }


    @Override
    public RecipeType<VibraniumForgeRecipe> getRecipeType() {
        return IRecipeCategory.super.getRecipeType();
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Vibranium Forge");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends VibraniumForgeRecipe> getRecipeClass() {
        return VibraniumForgeRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, VibraniumForgeRecipe recipe, IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,56, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT,56, 53).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem());
    }
}
