package cn.nukkit.block.impl;

import cn.nukkit.block.BlockTerracottaGlazed;
import cn.nukkit.utils.DyeColor;

/**
 * @author CreeperFace
 * @since 2.6.2017
 */
public class BlockTerracottaGlazedGray extends BlockTerracottaGlazed {

    public BlockTerracottaGlazedGray() {
        this(0);
    }

    public BlockTerracottaGlazedGray(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return GRAY_GLAZED_TERRACOTTA;
    }

    @Override
    public String getName() {
        return "Gray Glazed Terracotta";
    }

    public DyeColor getDyeColor() {
        return DyeColor.GRAY;
    }
}
