package cn.nukkit.event.entity;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;
import cn.nukkit.item.Item;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class EntityInventoryChangeEvent extends EntityEvent implements Cancellable {

    private final Item oldItem;
    private Item newItem;
    private final int slot;

    public EntityInventoryChangeEvent(Entity entity, Item oldItem, Item newItem, int slot) {
        this.entity = entity;
        this.oldItem = oldItem;
        this.newItem = newItem;
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public Item getNewItem() {
        return newItem;
    }

    public void setNewItem(Item newItem) {
        this.newItem = newItem;
    }

    public Item getOldItem() {
        return oldItem;
    }
}
