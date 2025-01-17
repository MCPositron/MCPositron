package cn.nukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import cn.nukkit.block.impl.BlockUnknown;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

@ExtendWith(PowerNukkitExtension.class)
class BlockUnknownTest {
    BlockUnknown block;

    @Test
    void constructor() {
        block = new BlockUnknown(1, (Number) null);
        assertEquals(0, block.getExactIntStorage());

        block = new BlockUnknown(1, null);
        assertEquals(0, block.getExactIntStorage());

        block = new BlockUnknown(1, 2);
        assertEquals(2, block.getExactIntStorage());

        block = new BlockUnknown(1, 2000000000L);
        assertEquals(2000000000L, block.getDataStorage());
    }
}
