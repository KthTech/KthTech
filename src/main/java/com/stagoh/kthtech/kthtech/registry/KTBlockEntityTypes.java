package com.stagoh.kthtech.kthtech.registry;

import java.util.function.Supplier;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.block.entity.KTCrusherBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTBlockEntityTypes
{
    private static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(
        ForgeRegistries.BLOCK_ENTITY_TYPES,
        KthTech.MODID
    );

    public static final RegistryObject<BlockEntityType<KTCrusherBlockEntity>> CRUSHER = basic(
        "crusher",
        KTBlocks.CRUSHER,
        KTCrusherBlockEntity::new
    );

    private static <T extends BlockEntityType<?>> RegistryObject<T> normal(String name, Supplier<T> sup)
    {
        return REGISTER.register(name, sup);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> basic(
        String name,
        RegistryObject<Block> blockRegObj,
        BlockEntityType.BlockEntitySupplier<T> entitySup
    )
    {
        return normal(name, () -> BlockEntityType.Builder.of(entitySup, blockRegObj.get()).build(null));
    }

    @SubscribeEvent
    public static void register(FMLConstructModEvent event)
    {
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
