package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.ItemTool;

/**
 * @author Angelic47 (Nukkit Project)
 */
public class BlockGold extends BlockSolid {

    public BlockGold() {}

    @Override
    public int getId() {
        return GOLD_BLOCK;
    }

    @Override
    public String getName() {
        return "Gold Block";
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 30;
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_IRON;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
