package com.stagoh.kthtech.kthtech.registry;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.data.KTItemModelProvider;
import com.stagoh.kthtech.kthtech.data.KTRecipeProvider;
import com.stagoh.kthtech.kthtech.data.lang.KTENUSLangProvider;
import com.stagoh.kthtech.kthtech.data.lang.KTZHCNLangProvider;

import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTDataProviderRegistry
{
    @SubscribeEvent
    public static void registerDataGenerators(GatherDataEvent event)
    {
        final var GENERATOR = event.getGenerator();
        final var EFHELPER = event.getExistingFileHelper();

        GENERATOR.addProvider(
            event.includeServer(),
            (DataProvider.Factory<RecipeProvider>)KTRecipeProvider::new
        );

        GENERATOR.addProvider(
            event.includeClient(),
            (DataProvider.Factory<LanguageProvider>)KTENUSLangProvider::new
        );
        GENERATOR.addProvider(
            event.includeClient(),
            (DataProvider.Factory<LanguageProvider>)KTZHCNLangProvider::new
        );

        GENERATOR.addProvider(
            event.includeClient(),
            (DataProvider.Factory<ItemModelProvider>)(output) -> new KTItemModelProvider(output, EFHELPER)
        );
    }
}
