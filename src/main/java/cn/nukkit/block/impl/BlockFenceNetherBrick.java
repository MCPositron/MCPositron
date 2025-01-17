package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockFenceBase;
import cn.nukkit.item.ItemTool;

/**
 * @author xtypr
 * @since 2015/12/7
 */
@PowerNukkitDifference(info = "Extends BlockFenceBase instead of BlockFence only in PowerNukkit", since = "1.4.0.0-PN")
public class BlockFenceNetherBrick extends BlockFenceBase {

    public BlockFenceNetherBrick() {
        this(0);
    }

    public BlockFenceNetherBrick(int meta) {
        super(meta);
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        return "Nether Brick Fence";
    }

    @Override
    public int getId() {
        return NETHER_BRICK_FENCE;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Since("1.2.1.0-PN")
    @Override
    public int getBurnChance() {
        return 0;
    }

    @Since("1.2.1.0-PN")
    @Override
    public int getBurnAbility() {
        return 0;
    }
}
