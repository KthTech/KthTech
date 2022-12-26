package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.item.KthTechMetaItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechItemRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerItems(RegisterEvent e) {
        e.register(ForgeRegistries.Keys.ITEMS, helper -> {
            LOGGER.info("Start registering items");
            var s1 = ForgeRegistries.ITEMS.getKeys().size();

            helper.register(new ResourceLocation(KthTech.MODID, "dust"), new KthTechMetaItem(new Item.Properties()));
            helper.register(new ResourceLocation(KthTech.MODID, "ingot"), new KthTechMetaItem(new Item.Properties()));

            var s2 = ForgeRegistries.ITEMS.getKeys().size();
            LOGGER.info(String.format("%d items registered", s2 - s1));
        });
    }
}
