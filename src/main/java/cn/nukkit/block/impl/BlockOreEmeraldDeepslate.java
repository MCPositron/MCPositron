package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

/**
 * @author joserobjr
 * @since 2021-06-13
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockOreEmeraldDeepslate extends BlockOreEmerald {

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockOreEmeraldDeepslate() {
        // Does nothing
    }

    @Override
    public int getId() {
        return DEEPSLATE_EMERALD_ORE;
    }

    @Override
    public double getHardness() {
        return 4.5;
    }

    @Override
    public String getName() {
        return "Deepslate Emerald Ore";
    }
}
