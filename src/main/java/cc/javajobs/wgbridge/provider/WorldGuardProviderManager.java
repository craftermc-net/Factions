package cc.javajobs.wgbridge.provider;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import org.jetbrains.annotations.Nullable;

public final class WorldGuardProviderManager {
    private WGBridgeAPI api = null;

    public WorldGuardProviderManager() {
    }

    public @Nullable WorldGuardProvider discover() {
        WorldGuardProvider[] var1 = WorldGuardProvider.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            WorldGuardProvider provider = var1[var3];
            if (provider.isWorldGuardRunning()) {
                this.api = provider.getWorldGuardWrapper();
                return provider;
            }
        }

        return null;
    }

    public @Nullable WGBridgeAPI getWorldGuardBridgeAPI() {
        return this.api;
    }
}
