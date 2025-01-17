package cn.nukkit.event.scoreboard;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.scoreboard.scoreboard.IScoreboard;

@PowerNukkitXOnly
@Since("1.19.30-r2")
public class ScoreboardObjectiveChangeEvent extends ScoreboardEvent {

    private final ActionType actionType;

    public ScoreboardObjectiveChangeEvent(IScoreboard scoreboard, ActionType actionType) {
        super(scoreboard);
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public enum ActionType {
        ADD,
        REMOVE
    }
}
