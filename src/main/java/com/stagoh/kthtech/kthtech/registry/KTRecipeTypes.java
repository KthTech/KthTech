package com.stagoh.kthtech.kthtech.registry;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.recipe.KTCrusherRecipe;
import com.stagoh.kthtech.kthtech.util.KTUtils;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTRecipeTypes
{
    public static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(
        ForgeRegistries.RECIPE_TYPES,
        KthTech.MODID
    );

    public static final RegistryObject<RecipeType<KTCrusherRecipe>> CRUSHER = REGISTER.register(
        "crusher",
        () -> RecipeType.simple(KTUtils.ktRLoc("recipe.crusher"))
    );

    @SubscribeEvent
    public static void register(FMLConstructModEvent event)
    {
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
