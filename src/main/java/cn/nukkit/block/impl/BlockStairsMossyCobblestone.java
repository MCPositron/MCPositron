package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockStairs;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockStairsMossyCobblestone extends BlockStairs {
    @PowerNukkitOnly
    public BlockStairsMossyCobblestone() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockStairsMossyCobblestone(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return MOSSY_COBBLESTONE_STAIRS;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 30;
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
    public String getName() {
        return "Mossy Cobblestone Stairs";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
