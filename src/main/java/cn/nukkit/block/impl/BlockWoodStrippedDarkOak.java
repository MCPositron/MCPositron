package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockWoodStripped;
import cn.nukkit.block.property.value.WoodType;

@PowerNukkitOnly
public class BlockWoodStrippedDarkOak extends BlockWoodStripped {
    @PowerNukkitOnly
    public BlockWoodStrippedDarkOak() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockWoodStrippedDarkOak(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRIPPED_DARK_OAK_LOG;
    }

    @PowerNukkitOnly
    @Override
    public WoodType getWoodType() {
        return WoodType.DARK_OAK;
    }
}
