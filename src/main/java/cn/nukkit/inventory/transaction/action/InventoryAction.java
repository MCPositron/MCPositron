package cn.nukkit.inventory.transaction.action;

import cn.nukkit.inventory.transaction.InventoryTransaction;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import lombok.ToString;

/**
 * @author CreeperFace
 */
@ToString
public abstract class InventoryAction {

    private long creationTime;

    protected Item sourceItem;

    protected Item targetItem;

    public InventoryAction(Item sourceItem, Item targetItem) {
        this.sourceItem = sourceItem;
        this.targetItem = targetItem;

        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Returns the item that was present before the action took place.
     *
     * @return source item
     */
    public Item getSourceItem() {
        return sourceItem.clone();
    }

    /**
     * Returns the item that the action attempted to replace the source item with.
     *
     * @return target item
     */
    public Item getTargetItem() {
        return targetItem.clone();
    }

    /**
     * Called by inventory transactions before any actions are processed. If this returns false, the transaction will
     * be cancelled.
     *
     * @param source player
     * @return cancelled
     */
    public boolean onPreExecute(Player source) {
        return true;
    }

    /**
     * Returns whether this action is currently valid. This should perform any necessary sanity checks.
     *
     * @param source player
     * @return valid
     */
    public abstract boolean isValid(Player source);

    /**
     * Called when the action is added to the specified InventoryTransaction.
     *
     * @param transaction to add
     */
    public void onAddToTransaction(InventoryTransaction transaction) {}

    /**
     * Performs actions needed to complete the inventory-action server-side. Returns if it was successful. Will return
     * false if plugins cancelled events. This will only be called if the transaction which it is part of is considered
     * valid.
     *
     * @param source player
     * @return successfully executed
     */
    public abstract boolean execute(Player source);

    /**
     * Performs additional actions when this inventory-action completed successfully.
     *
     * @param source player
     */
    public abstract void onExecuteSuccess(Player source);

    /**
     * Performs additional actions when this inventory-action did not complete successfully.
     *
     * @param source player
     */
    public abstract void onExecuteFail(Player source);
}
