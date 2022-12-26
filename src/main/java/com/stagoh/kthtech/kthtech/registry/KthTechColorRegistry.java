package com.stagoh.kthtech.kthtech.registry;

import java.util.ArrayList;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.KthTechSubstance;
import com.stagoh.kthtech.kthtech.KthTechUtil;

import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechColorRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item e) {
        LOGGER.info("Start registering ItemColors");

        ArrayList<Item> metaItems = new ArrayList<>();
        metaItems.add(KthTechUtil.getRegisteredInstance(ForgeRegistries.ITEMS, "dust"));
        metaItems.add(KthTechUtil.getRegisteredInstance(ForgeRegistries.ITEMS, "ingot"));
        e.register((is, mint) -> {
            var substanceID = 0;
            var tag = is.getTag();
            if (tag != null)
                substanceID = tag.getInt("substanceID");
            return KthTechSubstance.get(substanceID).color;
        }, metaItems.toArray(new Item[0]));

        LOGGER.info("All ItemColors registered");
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block e) {
        LOGGER.info("Start registering BlockColors");

        LOGGER.info("All BlockColors registered");
    }
}
