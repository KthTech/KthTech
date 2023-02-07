package com.stagoh.kthtech.kthtech.util;

import com.stagoh.kthtech.kthtech.KthTech;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

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

    public static Item ktItem(String name)
    {
        return ForgeRegistries.ITEMS.getValue(ktRLoc(name));
    }
}
