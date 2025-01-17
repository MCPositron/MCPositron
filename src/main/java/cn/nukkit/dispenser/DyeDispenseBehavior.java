package cn.nukkit.dispenser;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.*;
import cn.nukkit.block.impl.BlockDispenser;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;

@PowerNukkitOnly
public class DyeDispenseBehavior extends DefaultDispenseBehavior {

    @PowerNukkitOnly
    public DyeDispenseBehavior() {
        super();
    }

    @PowerNukkitOnly
    @Override
    public Item dispense(BlockDispenser block, BlockFace face, Item item) {
        Block target = block.getSide(face);

        if (item.isFertilizer()) {
            if (target.isFertilizable()) {
                target.onActivate(item);

            } else {
                this.success = false;
            }

            return null;
        }

        return super.dispense(block, face, item);
    }
}
