// smartCrosshairConfigScreen.java

package com.pheonix.smartcrosshair;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SmartCrosshairConfigScreen extends Screen {
    private final Screen parent;

    protected SmartCrosshairConfigScreen(Screen parent) {
        super(Text.literal("smart Crosshair Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("Crosshair Enabled: " + SmartCrosshairConfig.enabled),
                        button -> {
                            SmartCrosshairConfig.enabled = !SmartCrosshairConfig.enabled;
                            SmartCrosshairConfig.save();
                            button.setMessage(Text.literal("Crosshair Enabled: " + SmartCrosshairConfig.enabled));
                        })
                .position(this.width / 2 - 100, this.height / 2 - 24)
                .size(200, 20)
                .build()
        );

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("Outline: " + SmartCrosshairConfig.showOutline),
                        button -> {
                            SmartCrosshairConfig.showOutline = !SmartCrosshairConfig.showOutline;
                            SmartCrosshairConfig.save();
                            button.setMessage(Text.literal("Outline: " + SmartCrosshairConfig.showOutline));
                        })
                .position(this.width / 2 - 100, this.height / 2 + 2)
                .size(200, 20)
                .build()
        );

        this.addDrawableChild(ButtonWidget.builder(
                        Text.translatable("gui.done"),
                        button -> this.client.setScreen(parent))
                .position(this.width / 2 - 100, this.height / 2 + 50)
                .size(200, 20)
                .build()
        );
    }
}
