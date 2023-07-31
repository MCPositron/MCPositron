package cn.nukkit.blockproperty;

import static cn.nukkit.block.property.CommonBlockProperties.FACING_DIRECTION;
import static org.junit.jupiter.api.Assertions.*;

import cn.nukkit.block.property.BlockProperty;
import cn.nukkit.block.property.exception.InvalidBlockPropertyMetaException;
import cn.nukkit.math.BlockFace;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class BlockPropertyTest {
    BlockProperty<BlockFace> direction = FACING_DIRECTION;

    @Test
    void isDefaultPersistentValue() {
        var defaultValue = direction.getDefaultValue();
        var defaultMeta = direction.getMetaForValue(defaultValue);
        var defaultPersistenceValue = direction.getPersistenceValueForMeta(defaultMeta);
        assertTrue(direction.isDefaultPersistentValue(defaultPersistenceValue));
    }

    @Test
    void validateMeta() {
        assertThrows(InvalidBlockPropertyMetaException.class, () -> direction.validateMeta(7, 0));
        assertThrows(InvalidBlockPropertyMetaException.class, () -> direction.validateMeta(7L, 0));
        assertThrows(InvalidBlockPropertyMetaException.class, () -> direction.validateMeta(BigInteger.valueOf(7), 0));
    }

    @Test
    void getValue() {
        assertEquals(BlockFace.EAST, direction.getValue(13, 0));
        assertEquals(BlockFace.EAST, direction.getValue(13L, 0));
        assertEquals(BlockFace.EAST, direction.getValue(BigInteger.valueOf(13), 0));
    }

    @Test
    void setValue() {
        assertEquals(12, direction.setValue(13, 0, BlockFace.WEST));
        assertEquals(12L, direction.setValue(13L, 0, BlockFace.WEST));
        assertEquals(BigInteger.valueOf(12), direction.setValue(BigInteger.valueOf(13), 0, BlockFace.WEST));
    }

    @Test
    void setIntValue() {
        assertEquals(2, direction.getIntValue(-21, 2));
        assertEquals(2, direction.getIntValue(-21L, 2));
        assertEquals(2, direction.getIntValue(BigInteger.valueOf(-21), 2));
    }
}
