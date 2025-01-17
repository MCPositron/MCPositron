package cn.nukkit.metadata;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.plugin.Plugin;

/**
 * A FixedMetadataValue is a special case metadata item that contains the same
 * value forever after initialization. Invalidating a FixedMetadataValue has
 * no effect.
 * <p>
 * This class extends LazyMetadataValue for historical reasons, even though it
 * overrides all the implementation methods. it is possible that in the future
 * that the inheritance hierarchy may change.
 */
@PowerNukkitXOnly
@Since("1.19.63-r2")
public class FixedMetadataValue extends LazyMetadataValue {

    /**
     * Store the internal value that is represented by this fixed value.
     */
    private final Object internalValue;

    /**
     * Initializes a FixedMetadataValue with an Object
     *
     * @param owningPlugin the {@link Plugin} that created this metadata value
     * @param value        the value assigned to this metadata value
     */
    public FixedMetadataValue(Plugin owningPlugin, final Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    @Override
    public void invalidate() {}

    @Override
    public Object value() {
        return internalValue;
    }
}
