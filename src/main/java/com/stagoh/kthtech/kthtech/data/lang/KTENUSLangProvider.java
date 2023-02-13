package com.stagoh.kthtech.kthtech.data.lang;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.data.PackOutput;

public class KTENUSLangProvider extends KTAbstractLangProvider
{
    public KTENUSLangProvider(PackOutput output)
    {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        add("item_group." + KthTech.MODID, "KthTech");

        add(KTItems.COKE.get(), "Coke");
    }
}
