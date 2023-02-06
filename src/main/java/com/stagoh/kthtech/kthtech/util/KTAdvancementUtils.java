package com.stagoh.kthtech.kthtech.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.Item;

public final class KTAdvancementUtils extends KTUtils
{
    private KTAdvancementUtils()
    {
    }

    public static InventoryChangeTrigger.TriggerInstance haveLeastOne(Item item)
    {
        return CriteriaTriggers.INVENTORY_CHANGED.createInstance(
            new Gson().toJsonTree(
                Map.of(
                    "items", new JsonElement[] {
                        ItemPredicate.Builder.item()
                            .of(item)
                            .withCount(MinMaxBounds.Ints.atLeast(1))
                            .build()
                            .serializeToJson()
                    }
                )
            ).getAsJsonObject(),
            EntityPredicate.Composite.ANY,
            null
        );
    }
}
