package com.stagoh.kthtech.kthtech;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.fml.common.Mod;

@Mod(KthTech.MODID)
public class KthTech
{
    public static final String MODID = "kthtech";
    private static final Logger LOGGER = LogUtils.getLogger();

    public KthTech()
    {
        LOGGER.info("KthTech");
    }
}
