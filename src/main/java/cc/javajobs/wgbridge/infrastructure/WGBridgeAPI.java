package cc.javajobs.wgbridge.infrastructure;

import cc.javajobs.wgbridge.infrastructure.struct.WGRegionSet;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WGBridgeAPI {
    @Nullable WGRegionSet getRegions(@NotNull Location var1);

    boolean checkForRegionsInChunk(Chunk var1);

    default @Nullable WGRegionSet getRegions(@NotNull Block block) {
        return this.getRegions(block.getLocation());
    }

    default boolean checkRegionForFlagState(Location location, String flagName, String value) {
        return this.getRegions(location).checkValue(flagName, value);
    }

    default boolean canPlayerBuild(Location location) {
        return this.getRegions(location).checkValue("build", "allow");
    }
}
