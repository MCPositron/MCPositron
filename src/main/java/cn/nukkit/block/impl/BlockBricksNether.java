package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.ItemTool;

/**
 * @author xtypr
 * @since 2015/12/7
 */
public class BlockBricksNether extends BlockSolid {

    public BlockBricksNether() {}

    @Override
    public String getName() {
        return "Nether Brick";
    }

    @Override
    public int getId() {
        return NETHER_BRICK_BLOCK;
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
        return 2;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @PowerNukkitDifference(since = "1.4.0.0-PN", info = "Will return false as expected")
    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
