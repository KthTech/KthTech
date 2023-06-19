package com.stagoh.kthtech.kthtech.registry;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.util.KTUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTTabRegistry
{
    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(KTUtils.ktRLoc(KthTech.MODID), builder -> {
            builder.title(Component.translatable("item_group." + KthTech.MODID))
                .icon(() -> new ItemStack(KTItems.COKE.get()))
                .displayItems((params, output) -> {
                    output.accept(KTItems.COKE.get());
                    output.accept(KTItems.CRUSHED_RAW_IRON.get());
                    output.accept(KTItems.CRUSHED_RAW_GOLD.get());
                    output.accept(KTItems.CRUSHED_RAW_COPPER.get());
                    output.accept(KTBlocks.CRUSHER.get());
                });
        });
    }
}
