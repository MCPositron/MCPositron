package cn.nukkit.inventory;

import cn.nukkit.player.Player;

/**
 * @author CreeperFace
 */
public class PlayerCursorInventory extends PlayerUIComponent {
    private final PlayerUIInventory playerUI;

    PlayerCursorInventory(PlayerUIInventory playerUI) {
        super(playerUI, 0, 1);
        this.playerUI = playerUI;
    }

    /**
     * This override is here for documentation and code completion purposes only.
     *
     * @return Player
     */
    @Override
    public Player getHolder() {
        return playerUI.getHolder();
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CURSOR;
    }

    @Override
    public void sendContents(Player... players) {
        playerUI.sendSlot(0, players);
    }
}
