package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.Level;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockFireSoul extends BlockFire {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockFireSoul() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockFireSoul(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SOUL_FIRE;
    }

    @Override
    public String getName() {
        return "Soul Fire Block";
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            int downId = down().getId();
            if (downId != Block.SOUL_SAND && downId != Block.SOUL_SOIL) {
                this.getLevel()
                        .setBlock(
                                this,
                                getCurrentState().withBlockId(BlockID.FIRE).getBlock(this));
            }
            return type;
        }
        return 0;
    }
}
