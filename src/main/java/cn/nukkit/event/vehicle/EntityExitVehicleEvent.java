package cn.nukkit.event.vehicle;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;
import cn.nukkit.player.Player;

public class EntityExitVehicleEvent extends VehicleEvent implements Cancellable {

    private final cn.nukkit.entity.Entity riding;

    public EntityExitVehicleEvent(cn.nukkit.entity.Entity riding, Entity vehicle) {
        super(vehicle);
        this.riding = riding;
    }

    public cn.nukkit.entity.Entity getEntity() {
        return riding;
    }

    public boolean isPlayer() {
        return riding instanceof Player;
    }
}
