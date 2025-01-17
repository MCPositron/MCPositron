package cn.nukkit.event.block;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;
import javax.annotation.Nullable;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class FarmLandDecayEvent extends BlockEvent implements Cancellable {

    private final Entity entity;

    public FarmLandDecayEvent(@Nullable Entity entity, Block farm) {
        super(farm);
        this.entity = entity;
    }

    @Nullable public Entity getEntity() {
        return entity;
    }
}
