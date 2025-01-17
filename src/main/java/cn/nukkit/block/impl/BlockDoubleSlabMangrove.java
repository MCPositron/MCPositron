package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockDoubleSlabBase;
import cn.nukkit.item.ItemTool;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class BlockDoubleSlabMangrove extends BlockDoubleSlabBase {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockDoubleSlabMangrove() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    protected BlockDoubleSlabMangrove(int meta) {
        super(meta);
    }

    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return "Double Mangrove Slab";
    }

    @Override
    public int getId() {
        return DOUBLE_MANGROVE_SLAB;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public int getSingleSlabId() {
        return MANGROVE_SLAB;
    }
}
