package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockTrapdoor;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockTrapdoorCherry extends BlockTrapdoor {
    public BlockTrapdoorCherry() {
        this(0);
    }

    public BlockTrapdoorCherry(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CHERRY_TRAPDOOR;
    }

    @Override
    public String getName() {
        return "Cherry Trapdoor";
    }
}
