package cn.nukkit.event.player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.item.food.Food;
import cn.nukkit.player.Player;

/**
 * @author Snake1999
 * @since 2016/1/14
 */
public class PlayerEatFoodEvent extends PlayerEvent implements Cancellable {

    private Food food;

    public PlayerEatFoodEvent(Player player, Food food) {
        this.player = player;
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
