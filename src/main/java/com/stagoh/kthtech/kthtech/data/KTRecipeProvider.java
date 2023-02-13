package com.stagoh.kthtech.kthtech.data;

import static com.stagoh.kthtech.kthtech.util.KTUtils.ktRLoc;

import java.util.function.Consumer;

import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class KTRecipeProvider extends RecipeProvider
{
    public KTRecipeProvider(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 3)
            .group(getItemName(Items.IRON_INGOT))
            .requires(Tags.Items.RAW_MATERIALS_IRON)
            .requires(Items.CALCITE)
            .requires(Items.CHARCOAL, 2)
            .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON))
            .save(consumer, ktRLoc(getConversionRecipeName(Items.IRON_INGOT, Items.CHARCOAL)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 3)
            .group(getItemName(Items.IRON_INGOT))
            .requires(Tags.Items.RAW_MATERIALS_IRON)
            .requires(Items.CALCITE)
            .requires(KTItems.COKE.get())
            .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON))
            .save(consumer, ktRLoc(getConversionRecipeName(Items.IRON_INGOT, KTItems.COKE.get())));

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.COAL),
            RecipeCategory.MISC,
            KTItems.COKE.get(),
            0.15f,
            200
        ).unlockedBy(getHasName(Items.COAL), has(Items.COAL))
            .save(consumer);
    }
}
