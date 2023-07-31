package cn.nukkit.block.impl;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolidMeta;
import cn.nukkit.block.property.ArrayBlockProperty;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BlockProperty;
import cn.nukkit.block.property.value.PrismarineBlockType;
import cn.nukkit.item.ItemTool;
import org.jetbrains.annotations.NotNull;

public class BlockPrismarine extends BlockSolidMeta {
    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperty<PrismarineBlockType> PRISMARINE_BLOCK_TYPE =
            new ArrayBlockProperty<>("prismarine_block_type", true, PrismarineBlockType.class);

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(PRISMARINE_BLOCK_TYPE);

    @Deprecated
    @DeprecationDetails(
            since = "1.5.0.0-PN",
            replaceWith = "getPrismarineBlockType()",
            reason = "Use the BlockProperty API instead")
    public static final int NORMAL = 0;

    @DeprecationDetails(
            since = "1.5.0.0-PN",
            replaceWith = "getPrismarineBlockType()",
            reason = "Use the BlockProperty API instead")
    public static final int DARK = 1;

    @DeprecationDetails(
            since = "1.5.0.0-PN",
            replaceWith = "getPrismarineBlockType()",
            reason = "Use the BlockProperty API instead")
    public static final int BRICKS = 2;

    public BlockPrismarine() {
        this(0);
    }

    public BlockPrismarine(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PRISMARINE;
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
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
    public String getName() {
        return getPrismarineBlockType().getEnglishName();
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public void setPrismarineBlockType(PrismarineBlockType prismarineBlockType) {
        setPropertyValue(PRISMARINE_BLOCK_TYPE, prismarineBlockType);
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public PrismarineBlockType getPrismarineBlockType() {
        return getPropertyValue(PRISMARINE_BLOCK_TYPE);
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
}
