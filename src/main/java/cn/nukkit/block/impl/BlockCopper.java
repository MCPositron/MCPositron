package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockCopperBase;
import cn.nukkit.block.property.value.OxidizationLevel;
import org.jetbrains.annotations.NotNull;

/**
 * @author LoboMetalurgico
 * @since 11/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockCopper extends BlockCopperBase {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockCopper() {
        // Does nothing
    }

    @Override
    public String getName() {
        return "Block of Copper";
    }

    @Override
    public int getId() {
        return COPPER_BLOCK;
    }

    @Since("FUTURE")
    @PowerNukkitOnly
    @NotNull @Override
    public OxidizationLevel getOxidizationLevel() {
        return OxidizationLevel.UNAFFECTED;
    }
}
