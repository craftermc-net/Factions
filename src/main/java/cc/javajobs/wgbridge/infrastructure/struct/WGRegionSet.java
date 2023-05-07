package cc.javajobs.wgbridge.infrastructure.struct;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface WGRegionSet {
    @NotNull Set<WGRegion> getRegions();

    default boolean checkValue(@NotNull String flagName, @NotNull Object value) {
        return this.getRegions().stream().allMatch((region) -> {
            return region.checkValue(flagName, value);
        });
    }
}
