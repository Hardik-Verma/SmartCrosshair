package com.pheonix;

import net.fabricmc.api.ModInitializer;

public class SmartCrosshair implements ModInitializer {

    public static final String MOD_ID = "dynamiccrosshair";

    @Override
    public void onInitialize() {
        // NOTHING client-only here
        // This keeps dedicated servers happy
    }
}
