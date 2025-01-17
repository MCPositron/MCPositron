package cn.nukkit.event.level;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class SpawnChangeEvent extends LevelEvent {

    private final Position previousSpawn;

    public SpawnChangeEvent(Level level, Position previousSpawn) {
        super(level);
        this.previousSpawn = previousSpawn;
    }

    public Position getPreviousSpawn() {
        return previousSpawn;
    }
}
