package cn.nukkit.block.impl;

import cn.nukkit.block.BlockTerracottaGlazed;
import cn.nukkit.utils.DyeColor;

/**
 * @author CreeperFace
 * @since 2.6.2017
 */
public class BlockTerracottaGlazedOrange extends BlockTerracottaGlazed {

    public BlockTerracottaGlazedOrange() {
        this(0);
    }

    public BlockTerracottaGlazedOrange(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ORANGE_GLAZED_TERRACOTTA;
    }

    @Override
    public String getName() {
        return "Orange Glazed Terracotta";
    }

    public DyeColor getDyeColor() {
        return DyeColor.ORANGE;
    }
}
