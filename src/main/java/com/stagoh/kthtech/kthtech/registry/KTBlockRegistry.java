package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTBlockRegistry
{
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event)
    {
        event.register(ForgeRegistries.Keys.BLOCKS, helper -> {
            var cnt1 = ForgeRegistries.BLOCKS.getKeys().size();

            var cnt2 = ForgeRegistries.BLOCKS.getKeys().size();
            LOGGER.info(String.format("%d blocks registered", cnt2 - cnt1));
        });
    }
}
