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
    public KTCrusherMenu(int id, Inventory inventory)
    {
        this(id, inventory, new ItemStackHandler(1), new ItemStackHandler(1));
    }

    public KTCrusherMenu(int id, Inventory inventory, IItemHandler input, IItemHandler output)
    {
        super(KTMenuTypes.CRUSHER.get(), id);
        this.addSlot(new SlotItemHandler(input, 0, 8 + 2 * 18, 36));
        this.addSlot(new SlotItemHandler(output, 0, 8 + 6 * 18, 36) {
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
        ItemStack res = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            ItemStack is = slot.getItem();
            res = is.copy();
            if (index < 2)
            {
                if (!this.moveItemStackTo(is, 2, this.slots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if (!this.moveItemStackTo(is, 0, 0, false))
                return ItemStack.EMPTY;
            if (is.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return res;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }
}
