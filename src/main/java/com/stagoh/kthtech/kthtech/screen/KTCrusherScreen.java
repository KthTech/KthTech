package com.stagoh.kthtech.kthtech.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.stagoh.kthtech.kthtech.menu.KTCrusherMenu;
import com.stagoh.kthtech.kthtech.util.KTUtils;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KTCrusherScreen extends AbstractContainerScreen<KTCrusherMenu>
{
    private static final ResourceLocation BACKGROUND_LOCATION = KTUtils.ktRLoc("textures/gui/crusher.png");

    public KTCrusherScreen(KTCrusherMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
    }

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partialTick)
    {
        this.renderBackground(pose);
        super.render(pose, mouseX, mouseY, partialTick);
        this.renderTooltip(pose, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack pose, float partialTick, int mouseX, int mouseY)
    {
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        int x = this.leftPos, y = this.topPos;
        blit(pose, x, y, 0, 0, this.imageWidth, this.imageHeight);
        var p = this.menu.getProcess();
        blit(pose, x + 76, y + 36, 176, 0, p, 16);
    }
}
