package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockBricksBlackstonePolished extends BlockBlackstonePolished {

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockBricksBlackstonePolished() {
        // Does nothing
    }

    @Override
    public int getId() {
        return POLISHED_BLACKSTONE_BRICKS;
    }

    @Override
    public String getName() {
        return "Polished Blackstone Bricks";
    }

    @Override
    public double getHardness() {
        return 1.5;
    }
}
