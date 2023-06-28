package com.stagoh.kthtech.kthtech.menu;

import com.stagoh.kthtech.kthtech.registry.KTMenuTypes;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class KTCrusherMenu extends AbstractContainerMenu
{
    private static final int CONTAINER_SIZE = 2;
    private static final int CONTAINER_DATA_COUNT = 2;

    private static final int BGOF_RAW = 0;
    private static final int BGOF_RESULT = 1;
    private static final int BGOF_INVENTORY = BGOF_RESULT + 1;
    private static final int BGOF_HOTBAR = BGOF_RESULT + 3 * 9 + 1;

    private final Container container;
    private final ContainerData data;

    public KTCrusherMenu(int id, Inventory inventory)
    {
        this(id, inventory, new SimpleContainer(2), new SimpleContainerData(2));
    }

    public KTCrusherMenu(int id, Inventory inventory, Container container, ContainerData data)
    {
        super(KTMenuTypes.CRUSHER.get(), id);
        checkContainerSize(container, CONTAINER_SIZE);
        checkContainerDataCount(data, CONTAINER_DATA_COUNT);
        this.container = container;
        this.data = data;
        this.addSlot(new Slot(container, BGOF_RAW, 8 + 2 * 18, 36));
        this.addSlot(new Slot(container, BGOF_RESULT, 8 + 6 * 18, 36) {
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
        this.addDataSlots(data);
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
        return this.container.stillValid(player);
    }

    public int getProcess()
    {
        int u = this.data.get(0), t = this.data.get(1);
        return u != 0 && t != 0 ? 24 * u / t : 0;
    }
}
