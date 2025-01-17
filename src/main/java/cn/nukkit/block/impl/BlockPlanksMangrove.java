package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.ItemTool;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class BlockPlanksMangrove extends BlockSolid {
    public BlockPlanksMangrove() {}

    @Override
    public int getId() {
        return MANGROVE_PLANKS;
    }

    @Override
    public String getName() {
        return "Mangrove Planks";
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
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 20;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }
}
