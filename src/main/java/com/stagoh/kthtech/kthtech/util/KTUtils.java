package com.stagoh.kthtech.kthtech.util;

import com.stagoh.kthtech.kthtech.KthTech;

import net.minecraft.resources.ResourceLocation;

public final class KTUtils
{
    private KTUtils()
    {
        throw new IllegalAccessError("Utility class");
    }

    public static ResourceLocation ktRLoc(String name)
    {
        return new ResourceLocation(KthTech.MODID, name);
    }
}
