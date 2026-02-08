package com.pheonix.smartcrosshair;

import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class VersionChecker {
    private static boolean shownThisSession = false;
    private static final String MODRINTH_API = "https://api.modrinth.com/v2/project/SmartCrosshair/version";

    @Environment(EnvType.CLIENT)
    public static void check(String currentVersion) {
        if (shownThisSession) return;
        shownThisSession = true;
        new Thread(() -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(MODRINTH_API).openConnection();
                conn.setRequestProperty("User-Agent", "SmartCrosshairMod-VersionCheck");
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    JsonArray arr = JsonParser.parseReader(reader).getAsJsonArray();
                    if (arr.size() == 0) return;
                    String latest = arr.get(0).getAsJsonObject().get("version_number").getAsString();
                    if (!latest.equalsIgnoreCase(currentVersion)) {
                        MinecraftClient mc = MinecraftClient.getInstance();
                        // Show the update popup before the main menu
                        while (mc.currentScreen == null) {
                            Thread.sleep(400); // Wait until main screen available
                        }
                        mc.execute(() -> mc.setScreen(new UpdatePromptScreen(latest)));
                    }
                }
            } catch (Exception ignored) {}
        }, "SmartCrosshair Version Checker").start();
    }
}
