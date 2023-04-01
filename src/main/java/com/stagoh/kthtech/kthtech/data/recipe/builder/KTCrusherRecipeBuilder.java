package com.stagoh.kthtech.kthtech.data.recipe.builder;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.stagoh.kthtech.kthtech.registry.KTRecipeSerializers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class KTCrusherRecipeBuilder implements RecipeBuilder
{
    private final Item result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private Ingredient ingredient;
    @Nullable
    private String group;

    public KTCrusherRecipeBuilder(ItemLike result, int count)
    {
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public RecipeBuilder unlockedBy(String p_176496_, CriterionTriggerInstance p_176497_)
    {
        this.advancement.addCriterion(p_176496_, p_176497_);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group)
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
        // TODO: Supplement
    }

    public KTCrusherRecipeBuilder requires(TagKey<Item> tag)
    {
        return this.requires(Ingredient.of(tag));
    }

    public KTCrusherRecipeBuilder requires(ItemLike item)
    {
        return this.requires(Ingredient.of(item));
    }

    public KTCrusherRecipeBuilder requires(Ingredient ingredient)
    {
        this.ingredient = ingredient;
        return this;
    }

    public static record Result(
        ResourceLocation location,
        Item result,
        int count,
        String group,
        Ingredient ingredient,
        Advancement.Builder advancement,
        ResourceLocation advancementId
    ) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json)
        {
            if (!this.group.isEmpty()) json.addProperty("group", this.group);
            json.add("ingredients", ingredient.toJson());
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
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
