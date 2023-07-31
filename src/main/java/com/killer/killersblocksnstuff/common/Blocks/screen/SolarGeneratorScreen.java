package com.killer.killersblocksnstuff.common.Blocks.screen;

import com.killer.killersblocksnstuff.*;
import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.player.*;

public class SolarGeneratorScreen extends AbstractContainerScreen<SolarGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(KBNS.MOD_ID, "textures/gui/solar_generator_gui.png");

    public SolarGeneratorScreen(SolarGeneratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);


        //Energy Bar
        if (menu.isGenerating()){
            this.blit(pPoseStack, x + 106, y + 36, 176, 0, 10, 10);
        } else {
            this.blit(pPoseStack, x + 106, y + 36, 176, 10, 10, 10);
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
