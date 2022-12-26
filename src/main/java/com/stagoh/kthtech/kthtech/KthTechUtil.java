package com.stagoh.kthtech.kthtech;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public final class KthTechUtil {
    public static <T> T getRegisteredInstance(IForgeRegistry<T> registry, String name) {
        return registry.getValue(new ResourceLocation(KthTech.MODID, name));
    }
}
