package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.ItemTool;

public class BlockBricksEndStone extends BlockSolid {

    public BlockBricksEndStone() {}

    @Override
    public String getName() {
        return "End Stone Bricks";
    }

    @Override
    public int getId() {
        return END_BRICKS;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public double getHardness() {
        return 0.8;
    }

    @Override
    public double getResistance() {
        return 4;
    }
}
