package com.stagoh.kthtech.kthtech.menu;

import com.stagoh.kthtech.kthtech.registry.KTMenuTypes;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class KTCrusherMenu extends AbstractContainerMenu
{
    private static final int BGOF_RAW = 0;
    private static final int BGOF_RESULT = 1;
    private static final int BGOF_INVENTORY = BGOF_RESULT + 1;
    private static final int BGOF_HOTBAR = BGOF_RESULT + 3 * 9 + 1;

    public KTCrusherMenu(int id, Inventory inventory)
    {
        this(id, inventory, new ItemStackHandler(2));
    }

    public KTCrusherMenu(int id, Inventory inventory, IItemHandler items)
    {
        super(KTMenuTypes.CRUSHER.get(), id);
        this.addSlot(new SlotItemHandler(items, BGOF_RAW, 8 + 2 * 18, 36));
        this.addSlot(new SlotItemHandler(items, BGOF_RESULT, 8 + 6 * 18, 36) {
            public boolean mayPlace(ItemStack is)
            {
                return false;
            }
        });
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(inventory, 9 + i * 9 + j, 8 + j * 18, 84 + i * 18));
        for (int i = 0; i < 9; ++i)
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack quickMovedStack = ItemStack.EMPTY;
        Slot quickMovedSlot = this.slots.get(index);
        if (quickMovedSlot != null && quickMovedSlot.hasItem())
        {
            ItemStack rawStack = quickMovedSlot.getItem();
            quickMovedStack = rawStack.copy();
            if (index == BGOF_RESULT)
            {
                if (!this.moveItemStackTo(rawStack, BGOF_INVENTORY, BGOF_HOTBAR + 9, true))
                    return ItemStack.EMPTY;
                quickMovedSlot.onQuickCraft(rawStack, quickMovedStack);
            }
            else if (index == BGOF_RAW)
            {
                if (!this.moveItemStackTo(rawStack, BGOF_INVENTORY, BGOF_HOTBAR + 9, false))
                    return ItemStack.EMPTY;
            }
            else if (!this.moveItemStackTo(rawStack, BGOF_RAW, BGOF_INVENTORY, false))
            {
                if (index < BGOF_HOTBAR)
                {
                    if (!this.moveItemStackTo(rawStack, BGOF_HOTBAR, BGOF_HOTBAR + 9, false))
                        return ItemStack.EMPTY;
                }
                else if (!this.moveItemStackTo(rawStack, BGOF_INVENTORY, BGOF_HOTBAR, false))
                    return ItemStack.EMPTY;
            }
            if (rawStack.isEmpty()) quickMovedSlot.set(ItemStack.EMPTY);
            else quickMovedSlot.setChanged();
            if (rawStack.getCount() == quickMovedStack.getCount()) return ItemStack.EMPTY;
            quickMovedSlot.onTake(player, rawStack);
        }
        return quickMovedStack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }
}