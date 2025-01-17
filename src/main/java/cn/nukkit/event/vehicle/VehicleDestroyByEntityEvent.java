package cn.nukkit.event.vehicle;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;

/**
 * Is called when an entity destroyed a vehicle
 *
 * @author TrainmasterHD
 * @since 09.09.2019
 */
@PowerNukkitOnly
public final class VehicleDestroyByEntityEvent extends VehicleDestroyEvent implements Cancellable {

    private final Entity destroyer;

    /**
     * Constructor for the VehicleDestroyByEntityEvent
     *
     * @param vehicle   the destroyed vehicle
     * @param destroyer the destroying entity
     */
    @PowerNukkitOnly
    public VehicleDestroyByEntityEvent(final Entity vehicle, final Entity destroyer) {
        super(vehicle);

        this.destroyer = destroyer;
    }

    /**
     * Returns the destroying entity
     *
     * @return destroying entity
     */
    @PowerNukkitOnly
    public Entity getDestroyer() {
        return destroyer;
    }
}
