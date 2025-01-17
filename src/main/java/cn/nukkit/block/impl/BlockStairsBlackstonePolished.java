package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockStairsBlackstonePolished extends BlockStairsBlackstone {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStairsBlackstonePolished() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStairsBlackstonePolished(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Polished Blackstone Stairs";
    }

    @Override
    public int getId() {
        return POLISHED_BLACKSTONE_STAIRS;
    }

    @Override
    public double getHardness() {
        return 1.5;
    }
}
