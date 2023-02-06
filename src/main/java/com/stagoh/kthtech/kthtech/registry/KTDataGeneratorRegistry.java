package com.stagoh.kthtech.kthtech.registry;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.data.KTRecipeDataGenerator;

import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTDataGeneratorRegistry
{
    @SubscribeEvent
    public static void registerDataGenerators(GatherDataEvent event)
    {
        event.getGenerator().addProvider(
            event.includeServer(),
            (DataProvider.Factory<RecipeProvider>)KTRecipeDataGenerator::new
        );
    }
}
