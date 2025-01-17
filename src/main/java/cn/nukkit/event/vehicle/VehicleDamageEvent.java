package cn.nukkit.event.vehicle;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.entity.item.EntityVehicle;
import cn.nukkit.event.Cancellable;

/**
 * Is called when an entity takes damage
 */
public class VehicleDamageEvent extends VehicleEvent implements Cancellable {

    private double damage;

    /**
     * Constructor for the VehicleDamageEvent
     *
     * @param vehicle the damaged vehicle
     * @param damage  the caused damage on the vehicle
     */
    @PowerNukkitOnly
    public VehicleDamageEvent(final EntityVehicle vehicle, final double damage) {
        super(vehicle);

        this.damage = damage;
    }

    /**
     * Returns the caused damage on the vehicle
     *
     * @return caused damage on the vehicle
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets the damage caused on the vehicle
     *
     * @param damage the caused damage
     */
    public void setDamage(final double damage) {
        this.damage = damage;
    }
}
