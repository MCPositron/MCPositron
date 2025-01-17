package cn.nukkit.block.impl;

import static cn.nukkit.block.property.CommonBlockProperties.PILLAR_AXIS;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.MinecraftItemID;
import org.jetbrains.annotations.NotNull;

/**
 * @author LoboMetalurgico
 * @since 08/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDeepslate extends BlockSolid {
    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    public static final BlockProperties PROPERTIES = new BlockProperties(PILLAR_AXIS);

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDeepslate() {
        // Does nothing
    }

    @Override
    public String getName() {
        return "Deepslate";
    }

    @Override
    public int getId() {
        return DEEPSLATE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (!canHarvest(item)) {
            return Item.EMPTY_ARRAY;
        }

        return new Item[] {MinecraftItemID.COBBLED_DEEPSLATE.get(1)};
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Since("1.6.0.0-PNX")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }
}
