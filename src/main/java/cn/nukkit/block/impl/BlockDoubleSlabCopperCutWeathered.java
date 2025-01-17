package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.property.value.OxidizationLevel;
import org.jetbrains.annotations.NotNull;

/**
 * @author joserobjr
 * @since 2021-06-14
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDoubleSlabCopperCutWeathered extends BlockDoubleSlabCopperCut {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabCopperCutWeathered() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabCopperCutWeathered(int meta) {
        super(meta);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getSingleSlabId() {
        return WEATHERED_CUT_COPPER_SLAB;
    }

    @Override
    public int getId() {
        return WEATHERED_DOUBLE_CUT_COPPER_SLAB;
    }

    @Since("FUTURE")
    @PowerNukkitOnly
    @NotNull @Override
    public OxidizationLevel getOxidizationLevel() {
        return OxidizationLevel.WEATHERED;
    }
}
