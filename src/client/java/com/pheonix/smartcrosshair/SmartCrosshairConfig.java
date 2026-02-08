package com.pheonix.smartcrosshair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SmartCrosshairConfig {

    private static final File CONFIG_FILE = new File(MinecraftClient.getInstance().runDirectory, "config/Smartcrosshair.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Config values
    public static boolean enabled = true;
    public static boolean showOutline = true;
    public static float stillAlpha = 1.0f;
    public static float movingAlpha = 0.7f;
    public static int size = 8;

    // Load config from JSON
    public static void load() {
        try {
            if (!CONFIG_FILE.exists()) return;
            SmartCrosshairConfig cfg = GSON.fromJson(new FileReader(CONFIG_FILE), SmartCrosshairConfig.class);
            enabled = cfg.enabled;
            showOutline = cfg.showOutline;
            stillAlpha = cfg.stillAlpha;
            movingAlpha = cfg.movingAlpha;
            size = cfg.size;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save config to JSON
    public static void save() {
        try {
            CONFIG_FILE.getParentFile().mkdirs();
            GSON.toJson(new SmartCrosshairConfig(), new FileWriter(CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
