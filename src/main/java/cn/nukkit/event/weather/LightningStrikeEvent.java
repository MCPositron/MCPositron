package cn.nukkit.event.weather;

import cn.nukkit.entity.weather.EntityLightningStrike;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.level.WeatherEvent;
import cn.nukkit.level.Level;

/**
 * @author funcraft (Nukkit Project)
 */
public class LightningStrikeEvent extends WeatherEvent implements Cancellable {

    private final EntityLightningStrike bolt;

    public LightningStrikeEvent(Level level, final EntityLightningStrike bolt) {
        super(level);
        this.bolt = bolt;
    }

    /**
     * Gets the bolt which is striking the earth.
     * @return lightning entity
     */
    public EntityLightningStrike getLightning() {
        return bolt;
    }
}
