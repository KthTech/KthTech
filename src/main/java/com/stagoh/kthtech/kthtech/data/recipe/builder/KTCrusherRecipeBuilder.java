package com.stagoh.kthtech.kthtech.data.recipe.builder;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.stagoh.kthtech.kthtech.registry.KTRecipeSerializers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class KTCrusherRecipeBuilder implements RecipeBuilder
{
    private final Ingredient ingredient;
    private final Item result;
    private final int count;
    private final int energy;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    private String group;

    private KTCrusherRecipeBuilder(Ingredient ingredient, ItemLike result, int count, int energy)
    {
        this.ingredient = ingredient;
        this.result = result.asItem();
        this.count = count;
        this.energy = energy;
    }

    public static KTCrusherRecipeBuilder crusher(Ingredient ingredient, ItemLike result, int count, int energy)
    {
        return new KTCrusherRecipeBuilder(ingredient, result, count, energy);
    }

    public static KTCrusherRecipeBuilder crusher(ItemLike ingredient, ItemLike result, int count, int energy)
    {
        return new KTCrusherRecipeBuilder(Ingredient.of(ingredient), result, count, energy);
    }

    public static KTCrusherRecipeBuilder crusher(Ingredient ingredient, ItemLike result)
    {
        return crusher(ingredient, result, 1, 100);
    }

    public static KTCrusherRecipeBuilder crusher(ItemLike ingredient, ItemLike result)
    {
        return crusher(Ingredient.of(ingredient), result);
    }

    @Override
    public KTCrusherRecipeBuilder unlockedBy(String name, CriterionTriggerInstance trigger)
    {
        this.advancement.addCriterion(name, trigger);
        return this;
    }

    @Override
    public KTCrusherRecipeBuilder group(@Nullable String group)
    {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult()
    {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation location)
    {
        if (this.advancement.getCriteria().isEmpty())
            throw new IllegalStateException("No way of obtaining recipe " + location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT)
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location))
            .rewards(AdvancementRewards.Builder.recipe(location))
            .requirements(RequirementsStrategy.OR);
        consumer.accept(
            new Result(
                location,
                this.ingredient,
                this.result,
                this.count,
                this.energy,
                this.group == null ? "" : this.group,
                this.advancement,
                location.withPrefix("recipes/crusher/")
            )
        );
    }

    public static record Result(
        ResourceLocation location,
        Ingredient ingredient,
        Item result,
        int count,
        int energy,
        String group,
        Advancement.Builder advancement,
        ResourceLocation advancementId
    ) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json)
        {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);
            json.add("ingredient", ingredient.toJson());
            JsonObject map = new JsonObject();
            map.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) map.addProperty("count", this.count);
            json.add("result", map);
            json.addProperty("energy", energy);
        }

        @Override
        public ResourceLocation getId()
        {
            return this.location;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return KTRecipeSerializers.CRUSHER.get();
        }

        @Override
        @Nullable
        public JsonObject serializeAdvancement()
        {
            return this.advancement.serializeToJson();
        }

        @Override
        @Nullable
        public ResourceLocation getAdvancementId()
        {
            return this.advancementId;
        }
    }
}
