package com.pheonix.smartcrosshair;

import net.fabricmc.api.ClientModInitializer;

public class SmartCrosshairClient implements ClientModInitializer {
    public static final String VERSION = "1.0.0"; // Set your release version here

    @Override
    public void onInitializeClient() {
        SmartCrosshairConfig.load();
        Keybinds.register();
        VersionChecker.check(VERSION); // This will automatically check Modrinth on client init
    }
}

