package com.stagoh.kthtech.kthtech.registry;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.KthTechSubstance;
import com.stagoh.kthtech.kthtech.KthTechUtils;
import com.stagoh.kthtech.kthtech.item.KthTechMetaItem;

import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechTabRegistry
{
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register e)
    {
        LOGGER.info("Start registering creativetabs");

        for (var mitem : KthTechMetaItem.META_ITEMS)
        {
            var resLoc = KthTechUtils.ktResLoc(mitem);
            var item = KthTechUtils.regdObj(ForgeRegistries.ITEMS, mitem);
            var fieldName = "has_" + mitem;

            Field field = null;
            boolean[] has = new boolean[KthTechSubstance.size() + 1];
            try
            {
                field = KthTechSubstance.Substance.class.getField(fieldName);
                for (int i = 1; i <= KthTechSubstance.size(); i++)
                    has[i] = field.getBoolean(KthTechSubstance.get(i));
            }
            catch (Exception exception)
            {
                LOGGER.error(String.format("Field error: <%s>", fieldName));
                exception.printStackTrace();
                continue;
            }

            e.registerCreativeModeTab(resLoc, builder ->
            {
                builder.title(Component.translatable(resLoc.toLanguageKey("item_group")))
                    .icon(() -> item.getDefaultInstance())
                    .displayItems((enabledFlags, populator, hasPermissions) ->
                    {
                        for (int i = 1; i <= KthTechSubstance.size(); i++)
                        {
                            if (!has[i]) continue;
                            var is = item.getDefaultInstance();
                            is.addTagElement("substance", IntTag.valueOf(i));
                            populator.accept(is);
                        }
                    });
            });
        }

        LOGGER.info("All creativetabs registered");
    }
}
