package com.stagoh.kthtech.kthtech.item;

import com.stagoh.kthtech.kthtech.KthTechSubstance;

import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class KthTechMetaItem extends Item {
    public KthTechMetaItem(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId(ItemStack is) {
        var substanceName = KthTechSubstance.get(0).name;
        var tag = is.getTag();
        if (tag != null)
            substanceName = KthTechSubstance.get(tag.getInt("substanceID")).name;
        return Util.makeDescriptionId("item", ForgeRegistries.ITEMS.getKey(this).withPrefix(substanceName + "_"));
    }
}
