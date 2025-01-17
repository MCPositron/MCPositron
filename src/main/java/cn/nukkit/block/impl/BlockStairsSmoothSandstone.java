package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockStairs;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockStairsSmoothSandstone extends BlockStairs {
    @PowerNukkitOnly
    public BlockStairsSmoothSandstone() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockStairsSmoothSandstone(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SMOOTH_SANDSTONE_STAIRS;
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
        return "Smooth Sandstone Stairs";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
