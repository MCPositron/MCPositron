package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockWoodStripped;
import cn.nukkit.block.property.value.WoodType;

@PowerNukkitOnly
public class BlockWoodStrippedSpruce extends BlockWoodStripped {
    @PowerNukkitOnly
    public BlockWoodStrippedSpruce() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockWoodStrippedSpruce(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRIPPED_SPRUCE_LOG;
    }

    @PowerNukkitOnly
    @Override
    public WoodType getWoodType() {
        return WoodType.SPRUCE;
    }
}
