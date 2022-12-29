package com.stagoh.kthtech.kthtech.item;

import com.stagoh.kthtech.kthtech.KthTechSubstance;

import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class KthTechMetaItem extends Item {
    public static final String[] META_ITEMS = {
            "dust",
            "ingot"
    };

    public KthTechMetaItem(Properties properties) {
        super(properties);
    }

    public KthTechMetaItem() {
        this(new Item.Properties());
    }

    @Override
    public String getDescriptionId(ItemStack is) {
        var substanceName = KthTechSubstance.get(0).name;
        var tag = is.getTag();
        if (tag != null)
            substanceName = KthTechSubstance.get(tag.getInt("substance")).name;
        return Util.makeDescriptionId("item", ForgeRegistries.ITEMS.getKey(this).withPrefix(substanceName + "_"));
    }
}
