package cn.nukkit.event.inventory;

import cn.nukkit.event.Cancellable;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.player.Player;

/**
 * @author Box (Nukkit Project)
 */
public class InventoryOpenEvent extends InventoryEvent implements Cancellable {

    private final Player who;

    public InventoryOpenEvent(Inventory inventory, Player who) {
        super(inventory);
        this.who = who;
    }

    public Player getPlayer() {
        return this.who;
    }
}
