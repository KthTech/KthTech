package com.stagoh.kthtech.kthtech.data;

import java.util.function.Consumer;

import com.stagoh.kthtech.kthtech.util.KTAdvancementUtils;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class KTRecipeDataGenerator extends RecipeProvider
{
    public KTRecipeDataGenerator(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 3)
            .requires(Tags.Items.RAW_MATERIALS_IRON)
            .requires(Items.CALCITE)
            .requires(Items.CHARCOAL)
            .unlockedBy("got_raw_iron", KTAdvancementUtils.haveLeastOne(Items.RAW_IRON))
            .save(consumer);
    }
}
