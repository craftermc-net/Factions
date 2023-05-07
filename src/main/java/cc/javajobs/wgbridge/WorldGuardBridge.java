package cc.javajobs.wgbridge;

import cc.javajobs.wgbridge.infrastructure.WGBridgeAPI;
import cc.javajobs.wgbridge.provider.WorldGuardProvider;
import cc.javajobs.wgbridge.provider.WorldGuardProviderManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WorldGuardBridge {
    private static WorldGuardBridge instance;
    private WGBridgeAPI worldGuardWrapper;

    public WorldGuardBridge() {
    }

    public void connect(@NotNull JavaPlugin plugin, boolean messages) {
        Plugin worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (worldGuard != null && worldGuard.isEnabled()) {
            if (messages) {
                plugin.getLogger().info("Found WorldGuard v" + worldGuard.getDescription().getVersion() + "!");
            }

            WorldGuardProviderManager manager = new WorldGuardProviderManager();
            WorldGuardProvider discover = manager.discover();
            if (discover == null) {
                if (messages) {
                    plugin.getLogger().severe("Failed to hook into WorldGuard!");
                }

                this.worldGuardWrapper = null;
            } else {
                if (messages) {
                    plugin.getLogger().info("Hooked into WorldGuard!");
                }

                this.worldGuardWrapper = discover.getWorldGuardWrapper();
            }
        } else if (messages) {
            plugin.getLogger().warning("Failed to find WorldGuard. WorldGuardBridge shutting down.");
        }

        if (instance == null) {
            instance = this;
        }

    }

    public static @Nullable WorldGuardBridge getInstance() {
        return instance;
    }

    public @Nullable WGBridgeAPI getAPI() {
        return this.worldGuardWrapper;
    }
}
