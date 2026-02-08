package com.pheonix.smartcrosshair;

import net.minecraft.client.gui.screen.Screen;

// If you want ClothConfig integration in the future, add it here!
public class ConfigScreenCompat {
    public static Screen create(Screen parent) {
        // If you add ClothConfig later, check Compat.CLOTH_CONFIG here
        return parent; // Just fallback to parent if not present
    }
}
