package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.ItemTool;

/**
 * @author Angelic47 (Nukkit Project)
 */
public class BlockLapis extends BlockSolid {

    public BlockLapis() {}

    @Override
    public int getId() {
        return LAPIS_BLOCK;
    }

    @Override
    public String getName() {
        return "Lapis Lazuli Block";
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
        return 5;
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
