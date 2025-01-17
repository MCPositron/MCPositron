package cn.nukkit.event.potion;

import cn.nukkit.entity.item.EntityPotion;
import cn.nukkit.event.Cancellable;
import cn.nukkit.potion.Potion;

/**
 * @author Snake1999
 * @since 2016/1/12
 */
public class PotionCollideEvent extends PotionEvent implements Cancellable {

    private final EntityPotion thrownPotion;

    public PotionCollideEvent(Potion potion, EntityPotion thrownPotion) {
        super(potion);
        this.thrownPotion = thrownPotion;
    }

    public EntityPotion getThrownPotion() {
        return thrownPotion;
    }
}
