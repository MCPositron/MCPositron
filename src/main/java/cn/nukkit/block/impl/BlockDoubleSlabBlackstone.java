package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockDoubleSlabBase;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockDoubleSlabBlackstone extends BlockDoubleSlabBase {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockDoubleSlabBlackstone() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    protected BlockDoubleSlabBlackstone(int meta) {
        super(meta);
    }

    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return "Double Blackstone Slab";
    }

    @Override
    public int getId() {
        return BLACKSTONE_DOUBLE_SLAB;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @PowerNukkitOnly
    @Override
    public int getSingleSlabId() {
        return BLACKSTONE_SLAB;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
