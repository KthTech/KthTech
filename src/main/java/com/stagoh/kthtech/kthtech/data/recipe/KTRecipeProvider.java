package com.stagoh.kthtech.kthtech.data.recipe;

import java.util.function.Consumer;

import com.stagoh.kthtech.kthtech.data.recipe.builder.KTCrusherRecipeBuilder;
import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

public class KTRecipeProvider extends RecipeProvider
{
    public KTRecipeProvider(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        /*
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
        */

        KTCrusherRecipeBuilder.crusher(Items.RAW_IRON, KTItems.CRUSHED_RAW_IRON.get())
            .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON))
            .save(consumer);
        KTCrusherRecipeBuilder.crusher(Items.RAW_COPPER, KTItems.CRUSHED_RAW_COPPER.get())
            .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER))
            .save(consumer);
        KTCrusherRecipeBuilder.crusher(Items.RAW_GOLD, KTItems.CRUSHED_RAW_GOLD.get())
            .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD))
            .save(consumer);
    }
}
