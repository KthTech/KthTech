package com.stagoh.kthtech.kthtech;

import java.util.Arrays;
import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public final class KthTechUtils
{
    private KthTechUtils()
    {
        throw new IllegalAccessError("Utility class");
    }

    public static ResourceLocation ktResLoc(String name)
    {
        return new ResourceLocation(KthTech.MODID, name);
    }

    public static <T> T regdObj(IForgeRegistry<T> registry, String name)
    {
        return registry.getValue(ktResLoc(name));
    }

    public static <T> List<T> regdObjs(IForgeRegistry<T> registry, String[] names)
    {
        return Arrays.stream(names).map((name) -> regdObj(registry, name)).toList();
    }
}
