package com.stagoh.kthtech.kthtech.registry;

import java.util.function.Supplier;

import com.stagoh.kthtech.kthtech.KthTech;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTBlocks
{
    private static final DeferredRegister<Block> REGISTER = DeferredRegister.create(
        ForgeRegistries.BLOCKS,
        KthTech.MODID
    );

    public static final RegistryObject<Block> CRUSHER = basic("crusher", Material.METAL);

    private static <T extends Block> RegistryObject<T> normal(String name, Supplier<T> sup)
    {
        return REGISTER.register(name, sup);
    }

    private static RegistryObject<Block> basic(String name, Material material)
    {
        return normal(name, () -> new Block(BlockBehaviour.Properties.of(material)));
    }

    @SubscribeEvent
    public static void register(FMLConstructModEvent event)
    {
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
