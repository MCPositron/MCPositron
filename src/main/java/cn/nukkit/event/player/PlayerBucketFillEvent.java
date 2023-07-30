package cn.nukkit.event.player;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;

public class PlayerBucketFillEvent extends PlayerBucketEvent {

    @PowerNukkitOnly
    public PlayerBucketFillEvent(
            Player who, Block blockClicked, BlockFace blockFace, Block liquid, Item bucket, Item itemInHand) {
        super(who, blockClicked, blockFace, liquid, bucket, itemInHand);
    }
}
