package com.pheonix.smartcrosshair;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    private static KeyBinding toggle;

    public static void register() {
        toggle = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.smartcrosshair.toggle",
                        GLFW.GLFW_KEY_GRAVE_ACCENT, // default: "~"
                        "category.smartcrosshair"
                )
        );

        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                SmartCrosshairConfig.enabled = !SmartCrosshairConfig.enabled;
                SmartCrosshairConfig.save();
            }
        });
    }
}
