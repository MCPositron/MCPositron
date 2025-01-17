package cn.nukkit.level.format.updater;

import static cn.nukkit.block.property.CommonBlockProperties.DEPRECATED;
import static cn.nukkit.block.property.CommonBlockProperties.PILLAR_AXIS;
import static org.junit.jupiter.api.Assertions.*;

import cn.nukkit.block.BlockID;
import cn.nukkit.block.impl.BlockHyphaeStrippedCrimson;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.state.BlockState;
import cn.nukkit.block.state.MutableBlockState;
import cn.nukkit.level.format.anvil.ChunkSection;
import cn.nukkit.math.BlockFace;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

/**
 * @author joserobjr
 */
@ExtendWith(PowerNukkitExtension.class)
class StemStrippedUpdaterTest {
    @SuppressWarnings("Convert2MethodRef")
    @Test
    void testDebugPropertyRemoval() {
        BlockProperties oldProperties = new BlockProperties(PILLAR_AXIS, DEPRECATED);
        MutableBlockState mutableState = oldProperties.createMutableState(BlockID.STRIPPED_CRIMSON_HYPHAE);
        mutableState.setPropertyValue(PILLAR_AXIS, BlockFace.Axis.Z);
        mutableState.setPropertyValue(DEPRECATED, 1);
        BlockState oldState = mutableState.getCurrentState();

        ChunkSection chunkSection = new ChunkSection(0);
        chunkSection.setBlockState(1, 2, 3, BlockState.of(BlockID.STONE));
        chunkSection.delayPaletteUpdates();
        chunkSection.setBlockState(1, 2, 3, oldState);
        chunkSection.setContentVersion(9);

        StemStrippedUpdater updater = new StemStrippedUpdater(chunkSection);
        updater.update(0, 0, 0, 1, 2, 3, chunkSection.getBlockState(1, 2, 3));
        BlockState updated = chunkSection.getBlockState(1, 2, 3);
        assertEquals(
                BlockState.of(BlockID.STRIPPED_CRIMSON_HYPHAE).withProperty(PILLAR_AXIS, BlockFace.Axis.Z), updated);

        BlockHyphaeStrippedCrimson block = (BlockHyphaeStrippedCrimson) assertDoesNotThrow(() -> updated.getBlock());
        assertEquals(BlockFace.Axis.Z, block.getPillarAxis());
    }
}
