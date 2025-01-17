package cn.nukkit.level.format.updater;

import static cn.nukkit.block.property.CommonBlockProperties.DEPRECATED;
import static cn.nukkit.block.property.CommonBlockProperties.PILLAR_AXIS;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.state.BlockState;
import cn.nukkit.level.format.ChunkSection;

/**
 * @author joserobjr
 */
@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class StemStrippedUpdater implements Updater {
    private final ChunkSection section;
    private final BlockProperties oldProperties = new BlockProperties(PILLAR_AXIS, DEPRECATED);

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public StemStrippedUpdater(ChunkSection section) {
        this.section = section;
    }

    @PowerNukkitOnly
    @Override
    public boolean update(int offsetX, int offsetY, int offsetZ, int x, int y, int z, BlockState state) {
        switch (state.getBlockId()) {
            case BlockID.STRIPPED_WARPED_HYPHAE:
            case BlockID.STRIPPED_WARPED_STEM:
            case BlockID.STRIPPED_CRIMSON_HYPHAE:
            case BlockID.STRIPPED_CRIMSON_STEM:
                break;
            default:
                return false;
        }

        int currentStorage = state.getExactIntStorage();
        if (oldProperties.getIntValue(currentStorage, DEPRECATED.getName()) == 0) {
            return false;
        }

        int newStorage = oldProperties.setIntValue(currentStorage, DEPRECATED.getName(), 0);
        section.setBlockState(x, y, z, state.withData(newStorage));
        return true;
    }
}
