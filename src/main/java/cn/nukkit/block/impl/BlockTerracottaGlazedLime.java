package cn.nukkit.block.impl;

import cn.nukkit.block.BlockTerracottaGlazed;
import cn.nukkit.utils.DyeColor;

/**
 * @author CreeperFace
 * @since 2.6.2017
 */
public class BlockTerracottaGlazedLime extends BlockTerracottaGlazed {

    public BlockTerracottaGlazedLime() {
        this(0);
    }

    public BlockTerracottaGlazedLime(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return LIME_GLAZED_TERRACOTTA;
    }

    @Override
    public String getName() {
        return "Lime Glazed Terracotta";
    }

    public DyeColor getDyeColor() {
        return DyeColor.LIME;
    }
}
