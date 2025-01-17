package cn.nukkit.event.block;

import cn.nukkit.block.Block;

/**
 * @author CreeperFace
 * @since 2.8.2017
 *
 * @deprecated Use BlockPistonEvent
 */
@Deprecated
public class BlockPistonChangeEvent extends BlockEvent {

    private int oldPower;
    private int newPower;

    public BlockPistonChangeEvent(Block block, int oldPower, int newPower) {
        super(block);
        this.oldPower = oldPower;
        this.newPower = newPower;
    }

    public int getOldPower() {
        return oldPower;
    }

    public int getNewPower() {
        return newPower;
    }
}
