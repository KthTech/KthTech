package com.stagoh.kthtech.kthtech;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.mojang.logging.LogUtils;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = KthTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KthTechSubstance
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public static class Substance
    {
        public String name;
        public int color;
        public boolean has_dust;
        public boolean has_ingot;

        public Substance()
        {
            this.name = "undefined";
            this.color = 0xffffff;
            this.has_dust = false;
            this.has_ingot = false;
        }
    }

    private static final Substance UNDEFINED = new Substance();
    private static final ArrayList<Substance> SUBSTANCES = new ArrayList<>();

    @SubscribeEvent
    public static void readSubstances(FMLConstructModEvent e)
    {
        LOGGER.info("Start reading substances from resource file");

        InputStream is = KthTechSubstance.class.getClassLoader().getResourceAsStream("assets/kthtech/substances.json");
        SUBSTANCES.addAll(Arrays.asList(new Gson().fromJson(new InputStreamReader(is), Substance[].class)));

        LOGGER.info(String.format("%d substances read from resource file", SUBSTANCES.size()));
    }

    /**
     * @param index
     *            1-indexed
     * @return If the index is valid, return the corresponding substance; otherwise,
     *         return undefined substance
     */
    public static Substance get(int index)
    {
        if (index <= 0 || index > SUBSTANCES.size()) return UNDEFINED;
        return SUBSTANCES.get(index - 1);
    }

    public static int size()
    {
        return SUBSTANCES.size();
    }
}
