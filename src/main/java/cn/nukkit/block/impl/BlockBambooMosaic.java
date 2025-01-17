package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolid;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockBambooMosaic extends BlockSolid {
    public BlockBambooMosaic() {}

    public int getId() {
        return BAMBOO_MOSAIC;
    }

    public String getName() {
        return "Bamboo Mosaic";
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getBurnAbility() {
        return 20;
    }

    @Override
    public int getBurnChance() {
        return 5;
    }
}
