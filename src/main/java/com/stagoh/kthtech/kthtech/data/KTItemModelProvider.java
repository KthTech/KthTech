package com.stagoh.kthtech.kthtech.data;

import com.stagoh.kthtech.kthtech.KthTech;
import com.stagoh.kthtech.kthtech.registry.KTItems;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class KTItemModelProvider extends ItemModelProvider
{
    public KTItemModelProvider(PackOutput output, ExistingFileHelper efHelper)
    {
        super(output, KthTech.MODID, efHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(KTItems.COKE.get());
        basicItem(KTItems.CRUSHED_RAW_IRON.get());
        basicItem(KTItems.CRUSHED_RAW_COPPER.get());
        basicItem(KTItems.CRUSHED_RAW_GOLD.get());
    }
}
