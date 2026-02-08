package com.pheonix.smartcrosshair.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.pheonix.smartcrosshair.SmartCrosshairConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    private void beforeRenderCrosshair(DrawContext ctx, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        if (!SmartCrosshairConfig.enabled) return;
        // Tinting logic (like in first person, but you can change the conditions to allow all perspectives)
        float r = 1f, g = 1f, b = 1f, a = 1f;
        if (client.crosshairTarget != null && client.crosshairTarget.getType() == HitResult.Type.ENTITY) {
            EntityHitResult ehr = (EntityHitResult) client.crosshairTarget;
            double distance = client.player.distanceTo(ehr.getEntity());

            if (distance < 2.0) {
                // Very close = dark/maroon red
                r = 0.5f; g = 0.05f; b = 0.05f; a = 1.0f;
            } else if (distance < 5.0) {
                // Close = standard red
                r = 1.0f; g = 0f; b = 0f; a = 1.0f;
            } else {
                // Far = light faded red (pinkish)
                r = 1.0f; g = 0.6f; b = 0.6f; a = 1.0f;
            }
        }
        RenderSystem.setShaderColor(r, g, b, a);
    }
    @Inject(method = "renderCrosshair", at = @At("RETURN"))
    private void afterRenderCrosshair(DrawContext ctx, RenderTickCounter tickCounter, CallbackInfo ci) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}
