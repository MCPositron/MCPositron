package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSlab;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.value.StoneSlab1Type;
import cn.nukkit.item.ItemTool;
import org.jetbrains.annotations.NotNull;

/**
 * @author CreeperFace
 * @since 26. 11. 2016
 */
public class BlockSlabStone extends BlockSlab {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(StoneSlab1Type.PROPERTY, TOP_SLOT_PROPERTY);

    public static final int STONE = 0;
    public static final int SANDSTONE = 1;
    public static final int WOODEN = 2;
    public static final int COBBLESTONE = 3;
    public static final int BRICK = 4;
    public static final int STONE_BRICK = 5;
    public static final int QUARTZ = 6;
    public static final int NETHER_BRICK = 7;

    public BlockSlabStone() {
        this(0);
    }

    public BlockSlabStone(int meta) {
        super(meta, DOUBLE_STONE_SLAB);
    }

    @Override
    public int getId() {
        return STONE_SLAB;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return getSlabType().getEnglishName();
    }

    @PowerNukkitOnly
    @Override
    public boolean isSameType(BlockSlab slab) {
        return slab.getId() == getId() && getSlabType().equals(slab.getPropertyValue(StoneSlab1Type.PROPERTY));
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

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public StoneSlab1Type getSlabType() {
        return getPropertyValue(StoneSlab1Type.PROPERTY);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setSlabType(StoneSlab1Type type) {
        setPropertyValue(StoneSlab1Type.PROPERTY, type);
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
