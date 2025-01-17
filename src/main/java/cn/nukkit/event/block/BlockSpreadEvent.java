package cn.nukkit.event.block;

import cn.nukkit.block.Block;
import cn.nukkit.event.Cancellable;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class BlockSpreadEvent extends BlockFormEvent implements Cancellable {

    private final Block source;

    public BlockSpreadEvent(Block block, Block source, Block newState) {
        super(block, newState);
        this.source = source;
    }

    public Block getSource() {
        return source;
    }
}
