package com.stagoh.kthtech.kthtech.item.base;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class KtFuelItem extends Item
{
    private int burnTime;

    public KtFuelItem(int burnTime)
    {
        super(new Item.Properties());
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
    {
        return this.burnTime;
    }
}
