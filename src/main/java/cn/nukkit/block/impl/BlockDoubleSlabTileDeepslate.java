package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockDoubleSlabBase;
import cn.nukkit.block.BlockSlab;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.item.ItemTool;
import org.jetbrains.annotations.NotNull;

/**
 * @autor GoodLucky777
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDoubleSlabTileDeepslate extends BlockDoubleSlabBase {

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabTileDeepslate() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    protected BlockDoubleSlabTileDeepslate(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DEEPSLATE_TILE_DOUBLE_SLAB;
    }

    @Override
    public int getSingleSlabId() {
        return DEEPSLATE_TILE_SLAB;
    }

    @Override
    public String getSlabName() {
        return "Double Deepslate Tile Slab";
    }

    @NotNull @Override
    public BlockProperties getProperties() {
        return BlockSlab.SIMPLE_SLAB_PROPERTIES;
    }

    @Override
    public double getHardness() {
        return 3.5;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }
}
