package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechBlockRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent e) {
        e.register(ForgeRegistries.Keys.BLOCKS, helper -> {
            LOGGER.info("Start registering blocks");
            var s1 = ForgeRegistries.BLOCKS.getKeys().size();

            var s2 = ForgeRegistries.BLOCKS.getKeys().size();
            LOGGER.info(String.format("%d blocks registered", s2 - s1));
        });
    }
}
