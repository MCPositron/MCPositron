package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockLog;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.state.BlockState;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import org.jetbrains.annotations.NotNull;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockStrippedBambooBlock extends BlockLog {
    public BlockStrippedBambooBlock() {
        super(0);
    }

    public BlockStrippedBambooBlock(int meta) {
        super(meta);
    }

    public int getId() {
        return STRIPPED_BAMBOO_BLOCK;
    }

    public String getName() {
        return "Stripped Bamboo Block";
    }

    @NotNull @Override
    public BlockProperties getProperties() {
        return PILLAR_PROPERTIES;
    }

    @Override
    public BlockState getStrippedState() {
        return getCurrentState();
    }

    @Override
    public boolean canBeActivated() {
        return false;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        return false;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 20;
    }
}
