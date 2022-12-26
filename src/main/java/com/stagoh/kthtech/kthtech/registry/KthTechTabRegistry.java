package com.stagoh.kthtech.kthtech.registry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.KthTechSubstance;
import com.stagoh.kthtech.kthtech.KthTechUtil;

import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechTabRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register e) {
        LOGGER.info("Start registering creativetabs");

        e.registerCreativeModeTab(new ResourceLocation(KthTech.MODID, "dust"), builder -> {
            var dust = KthTechUtil.getRegisteredInstance(ForgeRegistries.ITEMS, "dust");
            builder.title(Component.translatable("item_group." + KthTech.MODID + ".dust"))
                    .icon(() -> dust.getDefaultInstance())
                    .displayItems((enabledFlags, populator, hasPermissions) -> {
                        for (int i = 1; i <= KthTechSubstance.size(); i++) {
                            if (!KthTechSubstance.get(i).has_dust)
                                continue;
                            var is = dust.getDefaultInstance();
                            is.addTagElement("substanceID", IntTag.valueOf(i));
                            populator.accept(is);
                        }
                    });
        });
        e.registerCreativeModeTab(new ResourceLocation(KthTech.MODID, "ingot"), builder -> {
            var ingot = KthTechUtil.getRegisteredInstance(ForgeRegistries.ITEMS, "ingot");
            builder.title(Component.translatable("item_group." + KthTech.MODID + ".ingot"))
                    .icon(() -> ingot.getDefaultInstance())
                    .displayItems((enabledFlags, populator, hasPermissions) -> {
                        for (int i = 1; i <= KthTechSubstance.size(); i++) {
                            if (!KthTechSubstance.get(i).has_ingot)
                                continue;
                            var is = ingot.getDefaultInstance();
                            is.addTagElement("substanceID", IntTag.valueOf(i));
                            populator.accept(is);
                        }
                    });
        });

        LOGGER.info("All creativetabs registered");
    }
}
