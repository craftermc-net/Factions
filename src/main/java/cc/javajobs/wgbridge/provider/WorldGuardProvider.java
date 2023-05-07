package cc.javajobs.wgbridge.provider;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum WorldGuardProvider {
    SIX("6.", "cc.javajobs.wgbridge.infrastructure.impl.v6.WGBridgeV6"),
    SEVEN("7.", "cc.javajobs.wgbridge.infrastructure.impl.v7.WGBridgeV7");

    private final String versionPrefix;
    private final String wgWrapper;

    private WorldGuardProvider(String versionPrefix, String wgWrapper) {
        this.versionPrefix = versionPrefix;
        this.wgWrapper = wgWrapper;
    }

    public @NotNull String getVersionPrefix() {
        return this.versionPrefix;
    }

    public @Nullable WGBridgeAPI getWorldGuardWrapper() {
        WGBridgeAPI api = null;

        try {
            Class<?> clazz = Class.forName(this.wgWrapper);
            if (WGBridgeAPI.class.isAssignableFrom(clazz)) {
                api = (WGBridgeAPI)clazz.getConstructor().newInstance();
            }
        } catch (ReflectiveOperationException var3) {
        }

        return api;
    }

    public boolean isWorldGuardRunning() {
        Plugin worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return worldGuard != null && worldGuard.isEnabled() && worldGuard.getDescription().getVersion().startsWith(this.getVersionPrefix());
    }
}
