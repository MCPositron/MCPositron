package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockWallBase;
import cn.nukkit.item.ItemTool;

/**
 * @author GoodLucky777
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockWallBrickDeepslate extends BlockWallBase {

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockWallBrickDeepslate() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockWallBrickDeepslate(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DEEPSLATE_BRICK_WALL;
    }

    @Override
    public String getName() {
        return "Deepslate Brick Wall";
    }

    @Override
    public double getHardness() {
        return 3.5;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }
}
