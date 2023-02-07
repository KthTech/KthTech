package com.stagoh.kthtech.kthtech.data;

import static com.stagoh.kthtech.kthtech.util.KTUtils.ktItem;

import com.stagoh.kthtech.kthtech.KthTech;

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
        basicItem(ktItem("coke"));
    }
}
