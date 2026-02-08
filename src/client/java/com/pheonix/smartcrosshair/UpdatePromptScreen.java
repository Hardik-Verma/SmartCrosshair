package com.pheonix.smartcrosshair;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Util;

public class UpdatePromptScreen extends Screen {
    private final String latestVersion;

    public UpdatePromptScreen(String latestVersion) {
        super(Text.literal("SmartCrosshair Update Available!"));
        this.latestVersion = latestVersion;
    }

    @Override
    protected void init() {
        int w = this.width;
        int h = this.height;
        this.addDrawableChild(ButtonWidget.builder(
                                Text.literal("Yes, Update"),
                                btn -> Util.getOperatingSystem().open("https://modrinth.com/mod/SmartCrosshair")
                        ).position(w / 2 - 60, h / 2 - 10)
                        .size(120, 20)
                        .build()
        );
        this.addDrawableChild(ButtonWidget.builder(
                                Text.literal("Later"),
                                btn -> MinecraftClient.getInstance().setScreen(new TitleScreen())
                        ).position(w / 2 - 60, h / 2 + 15)
                        .size(120, 20)
                        .build()
        );
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.render(ctx, mouseX, mouseY, delta);

        Text msg1 = Text.literal("A new SmartCrosshair version (" + latestVersion + ") is available!");
        Text msg2 = Text.literal("Would you like to update?");

        int x1 = (this.width - this.textRenderer.getWidth(msg1)) / 2;
        int x2 = (this.width - this.textRenderer.getWidth(msg2)) / 2;

        ctx.drawText(this.textRenderer, msg1, x1, this.height / 2 - 40, 0xFF4C4C, false);
        ctx.drawText(this.textRenderer, msg2, x2, this.height / 2 - 25, 0xFFFFFF, false);
    }
}
