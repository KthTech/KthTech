package com.stagoh.kthtech.kthtech.registry;

import java.util.function.Supplier;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.item.base.KtFuelItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTItems
{
    private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(
        ForgeRegistries.ITEMS,
        KthTech.MODID
    );

    public static final RegistryObject<Item> CRUSHED_RAW_IRON = basic("crushed_raw_iron");
    public static final RegistryObject<Item> CRUSHED_RAW_COPPER = basic("crushed_raw_copper");
    public static final RegistryObject<Item> CRUSHED_RAW_GOLD = basic("crushed_raw_gold");

    public static final RegistryObject<KtFuelItem> COKE = normal("coke", () -> new KtFuelItem(1600));

    public static final RegistryObject<BlockItem> CRUSHER = basic("crusher", KTBlocks.CRUSHER);

    private static <T extends Item> RegistryObject<T> normal(String name, Supplier<T> sup)
    {
        return REGISTER.register(name, sup);
    }

    private static RegistryObject<Item> basic(String name)
    {
        return normal(name, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<BlockItem> basic(String name, RegistryObject<Block> blockRegobj)
    {
        return normal(name, () -> new BlockItem(blockRegobj.get(), new Item.Properties()));
    }

    @SubscribeEvent
    public static void register(FMLConstructModEvent event)
    {
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
