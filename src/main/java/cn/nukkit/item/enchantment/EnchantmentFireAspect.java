package cn.nukkit.item.enchantment;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityCombustByEntityEvent;
import cn.nukkit.player.Player;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class EnchantmentFireAspect extends Enchantment {
    protected EnchantmentFireAspect() {
        super(ID_FIRE_ASPECT, "fire", Rarity.RARE, EnchantmentType.SWORD);
    }

    @Override
    public int getMinEnchantAbility(int level) {
        return 10 + (level - 1) * 20;
    }

    @Override
    public int getMaxEnchantAbility(int level) {
        return super.getMinEnchantAbility(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doAttack(Entity attacker, Entity entity) {
        if ((!(entity instanceof Player) || !((Player) entity).isCreative())) {
            int duration = Math.max(entity.fireTicks / 20, getLevel() << 2);

            EntityCombustByEntityEvent event = new EntityCombustByEntityEvent(attacker, entity, duration);
            event.call();

            if (!event.isCancelled()) {
                entity.setOnFire(event.getDuration());
            }
        }
    }
}
