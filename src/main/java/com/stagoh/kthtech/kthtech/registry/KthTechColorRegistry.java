package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.KthTechSubstance;
import com.stagoh.kthtech.kthtech.KthTechUtils;
import com.stagoh.kthtech.kthtech.item.KthTechMetaItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechColorRegistry
{
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item e)
    {
        LOGGER.info("Start registering ItemColors");

        e.register((is, mint) ->
        {
            var substance = 0;
            var tag = is.getTag();
            if (tag != null)
                substance = tag.getInt("substance");
            return KthTechSubstance.get(substance).color;
        }, KthTechUtils.regdObjs(ForgeRegistries.ITEMS, KthTechMetaItem.META_ITEMS).toArray(Item[]::new));

        LOGGER.info("All ItemColors registered");
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block e)
    {
        LOGGER.info("Start registering BlockColors");

        LOGGER.info("All BlockColors registered");
    }
}
