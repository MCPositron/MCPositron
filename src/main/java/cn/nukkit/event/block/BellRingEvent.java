package cn.nukkit.event.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.impl.BlockBell;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;

@PowerNukkitOnly
public class BellRingEvent extends BlockEvent implements Cancellable {

    private final RingCause cause;
    private final Entity entity;

    @PowerNukkitOnly
    public BellRingEvent(BlockBell bell, RingCause cause, Entity entity) {
        super(bell);
        this.cause = cause;
        this.entity = entity;
    }

    @Override
    public BlockBell getBlock() {
        return (BlockBell) super.getBlock();
    }

    @PowerNukkitOnly
    public Entity getEntity() {
        return entity;
    }

    @PowerNukkitOnly
    public RingCause getCause() {
        return cause;
    }

    @PowerNukkitOnly
    public enum RingCause {
        @PowerNukkitOnly
        HUMAN_INTERACTION,
        @PowerNukkitOnly
        REDSTONE,
        @PowerNukkitOnly
        PROJECTILE,
        @PowerNukkitOnly
        DROPPED_ITEM,
        @PowerNukkitOnly
        UNKNOWN
    }
}
