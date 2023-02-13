package com.stagoh.kthtech.kthtech.block;

import javax.annotation.Nullable;

import com.stagoh.kthtech.kthtech.block.entity.KTCrusherBlockEntity;
import com.stagoh.kthtech.kthtech.registry.KTBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class KTCrusherBlock extends BaseEntityBlock
{
    public KTCrusherBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new KTCrusherBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        Level level,
        BlockState state,
        BlockEntityType<T> type
    )
    {
        return createTickerHelper(type, KTBlockEntityTypes.CRUSHER.get(), KTCrusherBlockEntity::tick);
    }
}
