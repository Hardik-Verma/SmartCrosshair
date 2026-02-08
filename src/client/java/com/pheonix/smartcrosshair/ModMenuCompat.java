package com.pheonix.smartcrosshair;

public class ModMenuCompat {
    public static boolean available() {
        try {
            Class.forName("com.terraformersmc.modmenu.api.ModMenuApi");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
