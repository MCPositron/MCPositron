package cn.nukkit.entity.ai.executor;

import static cn.nukkit.entity.Entity.DATA_FLAGS;
import static cn.nukkit.entity.Entity.DATA_FLAG_IGNITED;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityIntelligent;
import cn.nukkit.entity.ai.memory.MemoryType;
import cn.nukkit.entity.data.IntEntityData;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.level.Explosion;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Sound;
import cn.nukkit.level.particle.HugeExplodeSeedParticle;
import org.jetbrains.annotations.Nullable;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class EntityExplosionExecutor implements IBehaviorExecutor {

    protected int explodeTime;
    protected int explodeForce;
    protected int currentTick = 0;

    @Nullable protected MemoryType<Boolean> flagMemory;

    public EntityExplosionExecutor(int explodeTime, int explodeForce) {
        this(explodeTime, explodeForce, null);
    }

    public EntityExplosionExecutor(int explodeTime, int explodeForce, @Nullable MemoryType<Boolean> flagMemory) {
        this.explodeTime = explodeTime;
        this.explodeForce = explodeForce;
        this.flagMemory = flagMemory;
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        // check flag
        if (flagMemory != null && entity.getMemoryStorage().compareDataTo(flagMemory, false)) {
            return false;
        }

        currentTick++;
        if (explodeTime > currentTick) {
            entity.getLevel().addSound(entity, Sound.RANDOM_FUSE);
            entity.setDataProperty(new IntEntityData(Entity.DATA_FUSE_LENGTH, currentTick));
            entity.setDataFlag(DATA_FLAGS, DATA_FLAG_IGNITED, true);
            return true;
        } else {
            explode(entity);
            return false;
        }
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        entity.setDataFlag(DATA_FLAGS, DATA_FLAG_IGNITED, false);
        currentTick = 0;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        entity.setDataFlag(DATA_FLAGS, DATA_FLAG_IGNITED, false);
        currentTick = 0;
    }

    protected void explode(EntityIntelligent entity) {
        EntityExplosionPrimeEvent event = new EntityExplosionPrimeEvent(
                entity,
                entity instanceof EntityCreeper creeper
                        ? creeper.isPowered() ? explodeForce * 2 : explodeForce
                        : explodeForce);

        if (!entity.getLevel().gameRules.getBoolean(GameRule.MOB_GRIEFING)) {
            event.setBlockBreaking(false);
        }

        event.call();

        if (!event.isCancelled()) {
            Explosion explosion = new Explosion(entity, (float) event.getForce(), entity);

            if (event.isBlockBreaking() && entity.getLevel().getGameRules().getBoolean(GameRule.MOB_GRIEFING)) {
                explosion.explodeA();
            }

            explosion.explodeB();
            entity.getLevel().addParticle(new HugeExplodeSeedParticle(entity));
        }

        entity.close();
    }
}
