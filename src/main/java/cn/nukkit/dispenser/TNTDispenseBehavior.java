package cn.nukkit.dispenser;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.impl.BlockDispenser;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;

@PowerNukkitOnly
public class TNTDispenseBehavior extends DefaultDispenseBehavior {

    @PowerNukkitOnly
    public TNTDispenseBehavior() {
        super();
    }

    @PowerNukkitOnly
    @Override
    public Item dispense(BlockDispenser block, BlockFace face, Item item) {
        Vector3 pos = block.getSide(face).add(0.5, 0, 0.5);

        EntityPrimedTNT tnt = new EntityPrimedTNT(
                block.getLevel().getChunk(pos.getChunkX(), pos.getChunkZ()), Entity.getDefaultNBT(pos));
        tnt.spawnToAll();

        return null;
    }
}
