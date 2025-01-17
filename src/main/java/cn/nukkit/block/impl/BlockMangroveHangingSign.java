package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockHangingSign;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockMangroveHangingSign extends BlockHangingSign {
    public BlockMangroveHangingSign() {}

    public int getId() {
        return MANGROVE_HANGING_SIGN;
    }

    public String getName() {
        return "Mangrove Hanging Sign";
    }
}
