package cc.javajobs.wgbridge.infrastructure.struct;

import org.jetbrains.annotations.NotNull;

public interface WGFlag {
    @NotNull String getName();

    @NotNull WGState getState();

    @NotNull Object getValue();
}
