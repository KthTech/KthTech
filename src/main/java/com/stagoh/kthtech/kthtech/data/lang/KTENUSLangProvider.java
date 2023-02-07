package com.stagoh.kthtech.kthtech.data.lang;

import static com.stagoh.kthtech.kthtech.util.KTUtils.ktItem;

import com.stagoh.kthtech.kthtech.KthTech;

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

        add(ktItem("coke"), "Coke");
    }
}
