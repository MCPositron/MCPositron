package cn.nukkit.inventory.transaction.action;

import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import lombok.ToString;

/**
 * @author CreeperFace
 */
@ToString(callSuper = true)
public class DropItemAction extends InventoryAction {

    public DropItemAction(Item source, Item target) {
        super(source, target);
    }

    /**
     * Verifies that the source item of a drop-item action must be air. This is not strictly necessary, just a sanity
     * check.
     */
    @Override
    public boolean isValid(Player source) {
        return this.sourceItem.isNull();
    }

    @Override
    public boolean onPreExecute(Player source) {
        PlayerDropItemEvent event = new PlayerDropItemEvent(source, this.targetItem);
        event.call();

        if (event.isCancelled()) {
            source.stopAction();
        }

        return !event.isCancelled();
    }

    /**
     * Drops the target item in front of the player.
     */
    @Override
    public boolean execute(Player source) {
        return source.dropItem(this.targetItem);
    }

    @Override
    public void onExecuteSuccess(Player source) {}

    @Override
    public void onExecuteFail(Player source) {}
}
