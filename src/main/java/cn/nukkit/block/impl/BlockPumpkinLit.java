package cn.nukkit.block.impl;

import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author xtypr
 * @since 2015/12/8
 */
public class BlockPumpkinLit extends BlockPumpkin {
    public BlockPumpkinLit() {
        this(0);
    }

    public BlockPumpkinLit(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Jack o'Lantern";
    }

    @Override
    public int getId() {
        return LIT_PUMPKIN;
    }

    @Override
    public int getLightLevel() {
        return 15;
    }

    @Override
    public boolean canBeActivated() {
        return false;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        return false;
    }
}
