package cn.nukkit.block.impl;

import cn.nukkit.block.BlockTerracottaGlazed;
import cn.nukkit.utils.DyeColor;

/**
 * @author CreeperFace
 * @since 2.6.2017
 */
public class BlockTerracottaGlazedYellow extends BlockTerracottaGlazed {

    public BlockTerracottaGlazedYellow() {
        this(0);
    }

    public BlockTerracottaGlazedYellow(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return YELLOW_GLAZED_TERRACOTTA;
    }

    @Override
    public String getName() {
        return "Yellow Glazed Terracotta";
    }

    public DyeColor getDyeColor() {
        return DyeColor.YELLOW;
    }
}
