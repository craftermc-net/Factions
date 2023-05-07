package cc.javajobs.wgbridge.infrastructure.struct;

import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WGRegion {
    @NotNull Set<WGFlag> getFlags();

    default @Nullable WGFlag getFlagByName(@NotNull String name) {
        return (WGFlag)this.getFlags().stream().filter((flag) -> {
            return flag.getName().equalsIgnoreCase(name);
        }).findFirst().orElse(null);
    }

    default @NotNull WGState getStateFor(@NotNull String flagName) {
        WGFlag flag = this.getFlagByName(flagName);
        return flag == null ? WGState.ALLOW : this.getStateFor(flag);
    }

    default WGState getStateFor(WGFlag flag) {
        return flag.getState();
    }

    default boolean checkValue(@NotNull String flagName, @NotNull Object value) {
        WGFlag flag = this.getFlagByName(flagName);
        if (flag == null) {
            return false;
        } else {
            return flag.getValue().equals(value) || flag.getValue().equals(WGState.getStateFrom(value));
        }
    }
}
