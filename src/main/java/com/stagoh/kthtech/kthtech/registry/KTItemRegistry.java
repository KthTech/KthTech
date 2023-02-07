package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTItemRegistry
{
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerItems(RegisterEvent event)
    {
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            final var COUNT1 = ForgeRegistries.ITEMS.getKeys().size();

            helper.register("coke", new Item(new Item.Properties()));

            final var COUNT2 = ForgeRegistries.ITEMS.getKeys().size();
            LOGGER.info(String.format("%d items registered", COUNT2 - COUNT1));
        });
    }
}
