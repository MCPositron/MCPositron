package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockStairs;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockStairsAndesite extends BlockStairs {
    @PowerNukkitOnly
    public BlockStairsAndesite() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockStairsAndesite(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ANDESITE_STAIRS;
    }

    @Override
    public double getHardness() {
        return 1.5;
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
        return "Andesite Stairs";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
