package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockWoodStripped;
import cn.nukkit.block.property.value.WoodType;

@PowerNukkitOnly
public class BlockWoodStrippedAcacia extends BlockWoodStripped {

    @PowerNukkitOnly
    public BlockWoodStrippedAcacia() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockWoodStrippedAcacia(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRIPPED_ACACIA_LOG;
    }

    @PowerNukkitOnly
    @Override
    public WoodType getWoodType() {
        return WoodType.ACACIA;
    }
}
