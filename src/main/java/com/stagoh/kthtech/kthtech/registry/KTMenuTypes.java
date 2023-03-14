package com.stagoh.kthtech.kthtech.registry;

import com.google.common.base.Supplier;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.menu.KTCrusherMenu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTMenuTypes
{
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(
        ForgeRegistries.MENU_TYPES,
        KthTech.MODID
    );

    public static final RegistryObject<MenuType<KTCrusherMenu>> CRUSHER = basic("crusher", KTCrusherMenu::new);

    private static <T extends MenuType<?>> RegistryObject<T> normal(String name, Supplier<T> sup)
    {
        return REGISTER.register(name, sup);
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> basic(
        String name,
        MenuType.MenuSupplier<T> sup
    )
    {
        return normal(name, () -> new MenuType<>(sup));
    }

    @SubscribeEvent
    public static void register(FMLConstructModEvent event)
    {
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
