package com.stagoh.kthtech.kthtech.data.lang;

import com.stagoh.kthtech.kthtech.KthTech;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class KTAbstractLangProvider extends LanguageProvider
{
    public KTAbstractLangProvider(PackOutput output, String locale)
    {
        super(output, KthTech.MODID, locale);
    }
}
