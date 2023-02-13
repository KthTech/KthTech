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
                .displayItems((enabledFlags, populator, hasPermissions) -> {
                    populator.accept(KTItems.COKE.get());
                    populator.accept(KTItems.CRUSHED_RAW_IRON.get());
                    // populator.accept(KTBlocks.CRUSHER.get());
                });
        });
    }
}
