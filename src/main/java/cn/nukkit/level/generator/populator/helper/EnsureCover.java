package cn.nukkit.level.generator.populator.helper;

import static cn.nukkit.block.BlockID.*;

import cn.nukkit.level.format.FullChunk;

/**
 * @author DaPorkchop_
 */
public interface EnsureCover {
    static boolean ensureCover(int x, int y, int z, FullChunk chunk) {
        int id = chunk.getBlockId(x, y, z);
        return id == AIR || id == SNOW_LAYER || id == TALL_GRASS;
    }
}
