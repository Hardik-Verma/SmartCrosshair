package com.pheonix.smartcrosshair;

import net.fabricmc.loader.api.FabricLoader;

public final class Compat {
    public static final boolean CLOTH_CONFIG =
            FabricLoader.getInstance().isModLoaded("cloth-config");
    public static final boolean MODMENU =
            FabricLoader.getInstance().isModLoaded("modmenu");

    private Compat() {}
}
