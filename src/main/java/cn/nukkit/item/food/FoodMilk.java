package cn.nukkit.item.food;

import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.player.Player;

/**
 * @author Snake1999
 * @since 2016/1/21
 */
public class FoodMilk extends Food {
    @Override
    protected boolean onEatenBy(Player player) {
        super.onEatenBy(player);
        player.getInventory().addItem(MinecraftItemID.BUCKET.get(1));
        player.removeAllEffects();
        return true;
    }
}
