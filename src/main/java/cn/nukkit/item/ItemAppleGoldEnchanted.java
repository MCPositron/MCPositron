package cn.nukkit.item;

import cn.nukkit.math.Vector3;
import cn.nukkit.player.Player;

/**
 * @author Snake1999
 * @since 2016/1/14
 */
public class ItemAppleGoldEnchanted extends ItemEdible {
    public ItemAppleGoldEnchanted() {
        this(0, 1);
    }

    public ItemAppleGoldEnchanted(Integer meta) {
        this(meta, 1);
    }

    public ItemAppleGoldEnchanted(Integer meta, int count) {
        super(GOLDEN_APPLE_ENCHANTED, meta, count, "Enchanted Golden Apple");
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        return true;
    }
}
