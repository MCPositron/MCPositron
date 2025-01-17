package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockLog;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class BlockMangroveLog extends BlockLog {

    public BlockMangroveLog() {
        this(0);
    }

    public BlockMangroveLog(int meta) {
        super(meta);
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 2;
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 10;
    }

    @Override
    public String getName() {
        return "mangrove log";
    }

    @Override
    public int getId() {
        return MANGROVE_LOG;
    }

    @NotNull @Override
    public BlockProperties getProperties() {
        return PILLAR_PROPERTIES;
    }

    @Override
    public BlockState getStrippedState() {
        return getCurrentState().withBlockId(STRIPPED_MANGROVE_LOG);
    }
}
