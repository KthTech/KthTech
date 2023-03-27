package com.stagoh.kthtech.kthtech.recipe;

import com.stagoh.kthtech.kthtech.registry.KTRecipeTypes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record KTCrusherRecipe(Ingredient input, int data, ItemStack output) implements Recipe<Container>
{
    @Override
    public boolean matches(Container p_44002_, Level p_44003_)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assemble'");
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canCraftInDimensions'");
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResultItem'");
    }

    @Override
    public ResourceLocation getId()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSerializer'");
    }

    @Override
    public RecipeType<?> getType()
    {
        return KTRecipeTypes.CRUSHER.get();
    }
}
