package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSlab;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.value.StoneSlab4Type;
import cn.nukkit.item.ItemTool;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
public class BlockSlabStone4 extends BlockSlab {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES =
            new BlockProperties(StoneSlab4Type.PROPERTY, CommonBlockProperties.VERTICAL_HALF);

    @PowerNukkitOnly
    public static final int MOSSY_STONE_BRICKS = 0;

    @PowerNukkitOnly
    public static final int SMOOTH_QUARTZ = 1;

    @PowerNukkitOnly
    public static final int STONE = 2;

    @PowerNukkitOnly
    public static final int CUT_SANDSTONE = 3;

    @PowerNukkitOnly
    public static final int CUT_RED_SANDSTONE = 4;

    @PowerNukkitOnly
    public BlockSlabStone4() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockSlabStone4(int meta) {
        super(meta, DOUBLE_STONE_SLAB4);
    }

    @Override
    public int getId() {
        return STONE_SLAB4;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public StoneSlab4Type getSlabType() {
        return getPropertyValue(StoneSlab4Type.PROPERTY);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setSlabType(StoneSlab4Type type) {
        setPropertyValue(StoneSlab4Type.PROPERTY, type);
    }

    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return getSlabType().getEnglishName();
    }

    @PowerNukkitOnly
    @Override
    public boolean isSameType(BlockSlab slab) {
        return slab.getId() == getId() && getSlabType().equals(slab.getPropertyValue(StoneSlab4Type.PROPERTY));
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
