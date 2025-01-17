package cn.nukkit.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powernukkit.tests.api.MockPlayer;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

/**
 * @author joserobjr
 * @since 2021-12-20
 */
@PowerNukkitOnly
@Since("FUTURE")
@ExtendWith(PowerNukkitExtension.class)
class DummyBossBarTest {
    @MockPlayer
    Player player;

    DummyBossBar bossBar;

    @Test
    void color() {
        bossBar = new DummyBossBar.Builder(player)
                .color(BossBarColor.BLUE)
                .text("Testing")
                .build();
        assertEquals(BossBarColor.BLUE, bossBar.getColor());
        bossBar.setColor(BossBarColor.GREEN);
        assertEquals(BossBarColor.GREEN, bossBar.getColor());
        bossBar.setColor(null);
        assertNull(bossBar.getColor());
        bossBar.setColor(BossBarColor.RED);
        assertEquals(BossBarColor.RED, bossBar.getColor());
        bossBar.setColor(BossBarColor.RED);
        assertEquals(BossBarColor.RED, bossBar.getColor());
    }
}
