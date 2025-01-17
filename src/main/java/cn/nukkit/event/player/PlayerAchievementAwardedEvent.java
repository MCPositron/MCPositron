package cn.nukkit.event.player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.player.Player;

public class PlayerAchievementAwardedEvent extends PlayerEvent implements Cancellable {

    protected final String achievement;

    public PlayerAchievementAwardedEvent(Player player, String achievementId) {
        this.player = player;
        this.achievement = achievementId;
    }

    public String getAchievement() {
        return this.achievement;
    }
}
