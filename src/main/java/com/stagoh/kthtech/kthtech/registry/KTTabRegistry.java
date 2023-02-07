package com.stagoh.kthtech.kthtech.registry;

import static com.stagoh.kthtech.kthtech.util.KTUtils.ktRLoc;
import static com.stagoh.kthtech.kthtech.util.KTUtils.ktItem;

import com.stagoh.kthtech.kthtech.KthTech;

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
        event.registerCreativeModeTab(ktRLoc(KthTech.MODID), builder -> {
            builder.title(Component.translatable("item_group." + KthTech.MODID))
                .icon(() -> new ItemStack(ktItem("coke")))
                .displayItems((enabledFlags, populator, hasPermissions) -> {
                    populator.accept(ktItem("coke"));
                });
        });
    }
}
