package cn.nukkit.level.generator.populator.impl.tree;

import cn.nukkit.block.Block;
import cn.nukkit.block.impl.BlockSapling;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.object.tree.ObjectBigSpruceTree;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

public class SpruceBigTreePopulator extends Populator {
    private final int type;
    private ChunkManager level;
    private int randomAmount;
    private int baseAmount;

    public SpruceBigTreePopulator() {
        this(BlockSapling.SPRUCE);
    }

    private SpruceBigTreePopulator(int type) {
        this.type = type;
    }

    public void setRandomAmount(int randomAmount) {
        this.randomAmount = randomAmount;
    }

    public void setBaseAmount(int baseAmount) {
        this.baseAmount = baseAmount;
    }

    @Override
    public void populate(ChunkManager level, int chunkX, int chunkZ, NukkitRandom random, FullChunk chunk) {
        this.level = level;
        int amount = random.nextBoundedInt(this.randomAmount + 1) + this.baseAmount;
        Vector3 v = new Vector3();

        for (int i = 0; i < amount; ++i) {
            int x = NukkitMath.randomRange(random, chunkX << 4, (chunkX << 4) + 15);
            int z = NukkitMath.randomRange(random, chunkZ << 4, (chunkZ << 4) + 15);
            int y = this.getHighestWorkableBlock(x, z);
            if (y == -1) {
                continue;
            }
            v.setX(x);
            v.setY(y);
            v.setZ(y);
            new ObjectBigSpruceTree(3 / 4f, 4)
                    .placeObject(this.level, (int) (v.x()), (int) (v.y()), (int) (v.z()), random);
        }
    }

    private int getHighestWorkableBlock(int x, int z) {
        int y;
        for (y = 255; y > 0; --y) {
            int b = this.level.getBlockIdAt(x, y, z);
            if (b == Block.DIRT || b == Block.GRASS) {
                break;
            } else if (b != Block.AIR && b != Block.SNOW_LAYER) {
                return -1;
            }
        }

        return ++y;
    }
}
