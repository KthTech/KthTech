package com.stagoh.kthtech.kthtech.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;
import com.stagoh.kthtech.kthtech.block.entity.KTCrusherBlockEntity;
import com.stagoh.kthtech.kthtech.registry.KTRecipeSerializers;
import com.stagoh.kthtech.kthtech.registry.KTRecipeTypes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;

public record KTCrusherRecipe(
    ResourceLocation location,
    Ingredient ingredient,
    ItemStack result,
    int energy
) implements Recipe<KTCrusherBlockEntity>
{

    @Override
    public boolean matches(KTCrusherBlockEntity entity, Level level)
    {
        return this.ingredient.test(entity.getItem(0));
    }

    @Override
    public ItemStack assemble(KTCrusherBlockEntity entity, RegistryAccess access)
    {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access)
    {
        return this.result;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.location;
    }

    @Override
    public RecipeSerializer<KTCrusherRecipe> getSerializer()
    {
        return KTRecipeSerializers.CRUSHER.get();
    }

    @Override
    public RecipeType<KTCrusherRecipe> getType()
    {
        return KTRecipeTypes.CRUSHER.get();
    }

    public static class Serializer implements RecipeSerializer<KTCrusherRecipe>
    {
        @Override
        public KTCrusherRecipe fromJson(ResourceLocation location, JsonObject json)
        {
            var ingredient = Ingredient.fromJson(json.get("ingredient"));
            var result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), true, true);
            var energy = json.get("energy").getAsInt();
            return new KTCrusherRecipe(location, ingredient, result, energy);
        }

        @Override
        public @Nullable KTCrusherRecipe fromNetwork(ResourceLocation location, FriendlyByteBuf buf)
        {
            var ingredient = Ingredient.fromNetwork(buf);
            var result = buf.readItem();
            var energy = buf.readInt();
            return new KTCrusherRecipe(location, ingredient, result, energy);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, KTCrusherRecipe recipe)
        {
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
            buf.writeInt(recipe.energy);
        }
    }
}
