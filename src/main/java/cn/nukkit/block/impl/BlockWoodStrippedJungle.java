package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockWoodStripped;
import cn.nukkit.block.property.value.WoodType;

@PowerNukkitOnly
public class BlockWoodStrippedJungle extends BlockWoodStripped {
    @PowerNukkitOnly
    public BlockWoodStrippedJungle() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockWoodStrippedJungle(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRIPPED_JUNGLE_LOG;
    }

    @PowerNukkitOnly
    @Override
    public WoodType getWoodType() {
        return WoodType.JUNGLE;
    }
}
