package cn.nukkit.level.generator.populator.impl;

import cn.nukkit.block.Block;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.populator.helper.EnsureCover;
import cn.nukkit.level.generator.populator.helper.EnsureGrassBelow;
import cn.nukkit.level.generator.populator.type.PopulatorSurfaceBlock;
import cn.nukkit.math.NukkitRandom;

/**
 * @author DaPorkchop_ (Nukkit Project)
 */
public class PopulatorDoublePlant extends PopulatorSurfaceBlock {

    private final int type;

    public PopulatorDoublePlant(int type) {
        this.type = type;
    }

    @Override
    protected boolean canStay(int x, int y, int z, FullChunk chunk) {
        return EnsureCover.ensureCover(x, y, z, chunk)
                && EnsureCover.ensureCover(x, y + 1, z, chunk)
                && EnsureGrassBelow.ensureGrassBelow(x, y, z, chunk);
    }

    @Override
    protected int getBlockId(int x, int z, NukkitRandom random, FullChunk chunk) {
        return (DOUBLE_PLANT << Block.DATA_BITS) | type;
    }

    @Override
    protected void placeBlock(int x, int y, int z, int id, FullChunk chunk, NukkitRandom random) {
        super.placeBlock(x, y, z, id, chunk, random);
        if (y < 255) {
            chunk.setFullBlockId(x, y + 1, z, 8 | id);
        }
    }
}
