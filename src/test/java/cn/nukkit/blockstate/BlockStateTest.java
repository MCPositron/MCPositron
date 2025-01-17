package cn.nukkit.blockstate;

import static org.junit.jupiter.api.Assertions.*;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.impl.BlockStone;
import cn.nukkit.block.property.value.StoneType;
import cn.nukkit.block.state.BlockState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

@ExtendWith(PowerNukkitExtension.class)
class BlockStateTest {
    @Test
    void ofFullStateId() {
        assertEquals(BlockState.of(BlockID.STONE), BlockState.of("minecraft:stone;stone_type=stone", false));
        assertThrows(IllegalArgumentException.class, () -> BlockState.of("minecraft:stone", false));

        assertEquals(
                BlockState.of(BlockID.STONE).withProperty(BlockStone.STONE_TYPE, StoneType.GRANITE),
                BlockState.of("minecraft:stone;unknown=1"));

        assertEquals(
                BlockState.of(BlockID.STONE).withProperty(BlockStone.STONE_TYPE, StoneType.GRANITE),
                BlockState.of("minecraft:stone;nukkit-unknown=1"));
    }
}
