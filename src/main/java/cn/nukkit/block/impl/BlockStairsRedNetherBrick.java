package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.BlockStairs;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockStairsRedNetherBrick extends BlockStairs {
    @PowerNukkitOnly
    public BlockStairsRedNetherBrick() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockStairsRedNetherBrick(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return RED_NETHER_BRICK_STAIRS;
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
        return "Red Nether Brick Stairs";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
