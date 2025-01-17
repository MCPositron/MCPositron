package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

@Since("1.4.0.0-PN")
@PowerNukkitOnly
public class BlockFenceGateCrimson extends BlockFenceGate {
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockFenceGateCrimson() {
        this(0);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockFenceGateCrimson(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CRIMSON_FENCE_GATE;
    }

    @Override
    public String getName() {
        return "Crimson Fence Gate";
    }

    @Override
    public int getBurnChance() {
        return 0;
    }

    @Override
    public int getBurnAbility() {
        return 0;
    }
}
