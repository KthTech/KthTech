package com.stagoh.kthtech.kthtech.data.lang;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.data.PackOutput;

public class KTZHCNLangProvider extends KTAbstractLangProvider
{
    public KTZHCNLangProvider(PackOutput output)
    {
        super(output, "zh_cn");
    }

    @Override
    protected void addTranslations()
    {
        add("item_group." + KthTech.MODID, "K 代科技");

        add(KTItems.COKE.get(), "焦炭");
    }
}
