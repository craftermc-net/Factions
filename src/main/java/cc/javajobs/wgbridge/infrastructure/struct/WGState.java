package cc.javajobs.wgbridge.infrastructure.struct;

import java.util.Arrays;
import org.jetbrains.annotations.Nullable;

public enum WGState {
    ALLOW,
    DENY,
    NOT_STATE_FLAG;

    private WGState() {
    }

    public static WGState getStateFrom(@Nullable Object object) {
        if (object == null) {
            return ALLOW;
        } else {
            String value;
            if (object.getClass().isEnum()) {
                value = ((Enum)object).name();
            } else if (object instanceof String) {
                value = (String)object;
            } else if (object instanceof Boolean) {
                value = (Boolean)object ? "allow" : "deny";
            } else if (object instanceof Integer) {
                value = (Integer)object == 1 ? "allow" : "deny";
            } else {
                value = "n/a";
            }

            return (WGState)Arrays.stream(values()).filter((wgState) -> {
                return wgState.name().equalsIgnoreCase(value);
            }).findFirst().orElse(ALLOW);
        }
    }
}
