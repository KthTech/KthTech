package com.stagoh.kthtech.kthtech.block.entity;

import java.util.Collections;

import com.stagoh.kthtech.kthtech.registry.KTBlockEntityTypes;
import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KTCrusherBlockEntity extends BlockEntity
{
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_RESULT = 1;

    private int elapsedTime;
    private NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public KTCrusherBlockEntity(BlockPos pos, BlockState state)
    {
        super(KTBlockEntityTypes.CRUSHER.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        Collections.fill(this.items, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.elapsedTime = tag.getInt("ElapsedTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
        tag.putInt("ElapsedTime", this.elapsedTime);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, KTCrusherBlockEntity entity)
    {
        if (++entity.elapsedTime == 200)
        {
            entity.elapsedTime = 0;
            var iptSlot = entity.items.get(SLOT_INPUT);
            var resSlot = entity.items.get(SLOT_RESULT);
            if (resSlot.isEmpty())
            {
                resSlot = new ItemStack(KTItems.CRUSHED_RAW_IRON.get());
            }
            else if (resSlot.is(KTItems.CRUSHED_RAW_IRON.get()))
            {
                if (resSlot.getCount() == resSlot.getMaxStackSize()) return;
                resSlot.setCount(resSlot.getCount() + 1);
            }
        }
    }
}
