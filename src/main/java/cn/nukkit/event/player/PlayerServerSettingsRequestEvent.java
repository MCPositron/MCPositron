package cn.nukkit.event.player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.player.Player;
import java.util.Map;

/**
 * @author CreeperFace
 */
public class PlayerServerSettingsRequestEvent extends PlayerEvent implements Cancellable {

    private Map<Integer, FormWindow> settings;

    public PlayerServerSettingsRequestEvent(Player player, Map<Integer, FormWindow> settings) {
        this.player = player;
        this.settings = settings;
    }

    public Map<Integer, FormWindow> getSettings() {
        return settings;
    }

    public void setSettings(Map<Integer, FormWindow> settings) {
        this.settings = settings;
    }

    public void setSettings(int id, FormWindow window) {
        this.settings.put(id, window);
    }
}
