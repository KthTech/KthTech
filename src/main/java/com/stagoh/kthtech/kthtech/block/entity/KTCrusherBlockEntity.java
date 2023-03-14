package com.stagoh.kthtech.kthtech.block.entity;

import com.stagoh.kthtech.kthtech.registry.KTBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KTCrusherBlockEntity extends BlockEntity
{
    public KTCrusherBlockEntity(BlockPos pos, BlockState state)
    {
        super(KTBlockEntityTypes.CRUSHER.get(), pos, state);
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, KTCrusherBlockEntity entity)
    {
    }
}
