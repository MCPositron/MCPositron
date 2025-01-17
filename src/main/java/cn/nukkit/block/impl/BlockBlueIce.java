package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;

@PowerNukkitOnly
public class BlockBlueIce extends BlockIcePacked {

    @PowerNukkitOnly
    public BlockBlueIce() {}

    @Override
    public int getId() {
        return BLUE_ICE;
    }

    @Override
    public String getName() {
        return "Blue Ice";
    }

    @Override
    public double getFrictionFactor() {
        return 0.989;
    }

    @Override
    public double getHardness() {
        return 2.8;
    }

    @Override
    public double getResistance() {
        return 14;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public int getLightLevel() {
        return 4;
    }
}
