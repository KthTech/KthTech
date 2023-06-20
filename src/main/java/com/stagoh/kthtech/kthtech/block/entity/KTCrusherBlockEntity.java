package com.stagoh.kthtech.kthtech.block.entity;

import java.util.Collections;
import java.util.Optional;

import javax.annotation.Nullable;

import com.stagoh.kthtech.kthtech.menu.KTCrusherMenu;
import com.stagoh.kthtech.kthtech.recipe.KTCrusherRecipe;
import com.stagoh.kthtech.kthtech.registry.KTBlockEntityTypes;
import com.stagoh.kthtech.kthtech.registry.KTRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KTCrusherBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer
{
    private static final int SLOT_RAW = 0;
    private static final int SLOT_RESULT = 1;

    private int usedTime;
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private final RecipeManager.CachedCheck<KTCrusherBlockEntity, KTCrusherRecipe> quickCheck;

    public KTCrusherBlockEntity(BlockPos pos, BlockState state)
    {
        super(KTBlockEntityTypes.CRUSHER.get(), pos, state);
        this.quickCheck = RecipeManager.createCheck(KTRecipeTypes.CRUSHER.get());
    }

    @Override
    public int getContainerSize()
    {
        return this.items.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.items.isEmpty();
    }

    @Override
    public ItemStack getItem(int index)
    {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int p_18942_, int p_18943_)
    {
        return ContainerHelper.removeItem(this.items, p_18942_, p_18943_);
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_18951_)
    {
        return ContainerHelper.takeItem(this.items, p_18951_);
    }

    @Override
    public void setItem(int index, ItemStack newStack)
    {
        ItemStack oldStack = this.items.get(index);
        boolean flag = !newStack.isEmpty() && newStack.sameItem(oldStack) && ItemStack.tagMatches(newStack, oldStack);
        this.items.set(index, newStack);
        if (newStack.getCount() > this.getMaxStackSize())
            newStack.setCount(this.getMaxStackSize());
        if (index == 0 && !flag)
        {
            this.usedTime = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player)
    {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent()
    {
        this.items.clear();
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container.kthtech.crusher");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory)
    {
        return new KTCrusherMenu(id, inventory, this);
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        Collections.fill(this.items, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.usedTime = tag.getInt("UsedTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
        tag.putInt("UsedTime", this.usedTime);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack)
    {
        return index != 1;
    }

    @Override
    public int[] getSlotsForFace(Direction direction)
    {
        if (direction == Direction.UP) return new int[] { 0 };
        return new int[] { 1 };
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction)
    {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction)
    {
        return true;
    }

    public NonNullList<ItemStack> getItems()
    {
        return this.items;
    }

    private boolean canProgress(Optional<KTCrusherRecipe> recipe)
    {
        var resSlot = this.items.get(SLOT_RESULT);
        if (recipe.isEmpty()) return false;
        if (resSlot.isEmpty()) return true;
        var recipeRes = recipe.map(KTCrusherRecipe::result).get();
        if (!resSlot.sameItem(recipeRes)) return false;
        return resSlot.getCount() + recipeRes.getCount() <= resSlot.getMaxStackSize();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, KTCrusherBlockEntity entity)
    {
        boolean changed = false;
        var rawSLot = entity.items.get(SLOT_RAW);
        var resSlot = entity.items.get(SLOT_RESULT);
        var recipe = entity.quickCheck.getRecipeFor(entity, entity.level);
        if (!entity.canProgress(recipe))
        {
            if (entity.usedTime != 0)
            {
                changed = true;
                entity.usedTime = 0;
            }
        }
        else
        {
            changed = true;
            var result = recipe.get().result();
            var needTime = recipe.get().energy();
            if (++entity.usedTime == needTime)
            {
                entity.usedTime = 0;
                rawSLot.shrink(1);
                if (resSlot.isEmpty()) entity.items.set(SLOT_RESULT, result);
                else resSlot.grow(result.getCount());
            }
        }
        if (changed) entity.setChanged();
    }
}
