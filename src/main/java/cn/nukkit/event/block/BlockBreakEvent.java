package cn.nukkit.event.block;

import cn.nukkit.block.Block;
import cn.nukkit.event.Cancellable;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class BlockBreakEvent extends BlockEvent implements Cancellable {

    protected final Player player;

    protected final Item item;
    protected final BlockFace face;

    protected boolean instaBreak = false;
    protected Item[] blockDrops = Item.EMPTY_ARRAY;
    protected int blockXP = 0;

    protected boolean fastBreak = false;

    public BlockBreakEvent(Player player, Block block, Item item, Item[] drops) {
        this(player, block, item, drops, false, false);
    }

    public BlockBreakEvent(Player player, Block block, Item item, Item[] drops, boolean instaBreak) {
        this(player, block, item, drops, instaBreak, false);
    }

    public BlockBreakEvent(Player player, Block block, Item item, Item[] drops, boolean instaBreak, boolean fastBreak) {
        this(player, block, null, item, drops, instaBreak, fastBreak);
    }

    public BlockBreakEvent(
            Player player,
            Block block,
            BlockFace face,
            Item item,
            Item[] drops,
            boolean instaBreak,
            boolean fastBreak) {
        super(block);
        this.face = face;
        this.item = item;
        this.player = player;
        this.instaBreak = instaBreak;
        this.blockDrops = drops;
        this.fastBreak = fastBreak;
        this.blockXP = block.getDropExp();
    }

    public Player getPlayer() {
        return player;
    }

    public BlockFace getFace() {
        return face;
    }

    public Item getItem() {
        return item;
    }

    /**
     * 返回块是否可能在小于计算的时间内被破坏。通常创造玩家是true。
     * <p>
     * Returns whether the block may be broken in less than the amount of time calculated. This is usually true for creative players.
     *
     * @return the insta break
     */
    public boolean getInstaBreak() {
        return this.instaBreak;
    }

    public Item[] getDrops() {
        return blockDrops;
    }

    public void setDrops(Item[] drops) {
        this.blockDrops = drops;
    }

    public int getDropExp() {
        return this.blockXP;
    }

    public void setDropExp(int xp) {
        this.blockXP = xp;
    }

    public void setInstaBreak(boolean instaBreak) {
        this.instaBreak = instaBreak;
    }

    public boolean isFastBreak() {
        return this.fastBreak;
    }
}
