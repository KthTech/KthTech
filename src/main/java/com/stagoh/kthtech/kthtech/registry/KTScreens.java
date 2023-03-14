package com.stagoh.kthtech.kthtech.registry;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.screen.KTCrusherScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KTScreens
{
    @SubscribeEvent
    public static void register(FMLClientSetupEvent event)
    {
        event.enqueueWork(
            () -> MenuScreens.register(KTMenuTypes.CRUSHER.get(), KTCrusherScreen::new)
        );
    }
}
