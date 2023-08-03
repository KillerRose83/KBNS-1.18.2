package com.killer.killersblocksnstuff.recipe;

import com.google.gson.*;
import com.killer.killersblocksnstuff.*;
import net.minecraft.core.*;
import net.minecraft.network.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;

import javax.annotation.*;

public class VibraniumForgeRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public VibraniumForgeRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (recipeItems.get(0).test(pContainer.getItem(0)) && recipeItems.get(1).test(pContainer.getItem(1))){
            return true;
        } else return false;
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<VibraniumForgeRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "forging";
    }

    public static class Serializer implements RecipeSerializer<VibraniumForgeRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(KBNS.MOD_ID, "forging");

        @Override
        public VibraniumForgeRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));


            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new VibraniumForgeRecipe(id, output, inputs);
        }

        @Override
        public VibraniumForgeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new VibraniumForgeRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, VibraniumForgeRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>) cls;
        }
    }
}
