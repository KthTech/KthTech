package com.stagoh.kthtech.kthtech.block;

import javax.annotation.Nullable;

import com.stagoh.kthtech.kthtech.block.entity.KTCrusherBlockEntity;
import com.stagoh.kthtech.kthtech.menu.KTCrusherMenu;
import com.stagoh.kthtech.kthtech.registry.KTBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class KTCrusherBlock extends BaseEntityBlock
{
    public KTCrusherBlock()
    {
        super(BlockBehaviour.Properties.of(Material.METAL));
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

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos)
    {
        return new SimpleMenuProvider(
            (id, inventory, player) -> new KTCrusherMenu(id, inventory),
            Component.translatable("menu.title.kthtech.crusher")
        );
    }

    @Override
    public InteractionResult use(
        BlockState state,
        Level level,
        BlockPos pos,
        Player player,
        InteractionHand hand,
        BlockHitResult res
    )
    {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer)
            NetworkHooks.openScreen(serverPlayer, state.getMenuProvider(level, pos));
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
