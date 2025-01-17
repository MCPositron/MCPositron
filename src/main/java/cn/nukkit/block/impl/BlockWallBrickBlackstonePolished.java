package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockWallBrickBlackstonePolished extends BlockWallBlackstonePolished {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockWallBrickBlackstonePolished() {}

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockWallBrickBlackstonePolished(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POLISHED_BLACKSTONE_BRICK_WALL;
    }

    @Override
    public String getName() {
        return "Polished Blackstone Brick Wall";
    }

    @Override
    public double getHardness() {
        return 1.5;
    }
}
