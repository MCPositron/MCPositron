package cn.nukkit.event.player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.player.Player;

/**
 * Called when the player logs in, before things have been set up
 */
public class PlayerPreLoginEvent extends PlayerEvent implements Cancellable {

    protected String kickMessage;

    public PlayerPreLoginEvent(Player player, String kickMessage) {
        this.player = player;
        this.kickMessage = kickMessage;
    }

    public void setKickMessage(String kickMessage) {
        this.kickMessage = kickMessage;
    }

    public String getKickMessage() {
        return this.kickMessage;
    }
}
