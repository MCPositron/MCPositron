package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.redstone.RedstoneUpdateEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.level.Level;
import cn.nukkit.utils.RedstoneComponent;

/**
 * @author Pub4Game
 */
@PowerNukkitDifference(info = "Implements RedstoneComponent.", since = "1.4.0.0-PN")
public class BlockRedstoneLampLit extends BlockRedstoneLamp implements RedstoneComponent {

    public BlockRedstoneLampLit() {}

    @Override
    public String getName() {
        return "Lit Redstone Lamp";
    }

    @Override
    public int getId() {
        return LIT_REDSTONE_LAMP;
    }

    @Override
    public int getLightLevel() {
        return 15;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(BlockID.REDSTONE_LAMP));
    }

    @PowerNukkitDifference(
            info = "Redstone Event on scheduled update part + use #isGettingPower() method" + " + trigger observer.",
            since = "1.4.0.0-PN")
    @Override
    public int onUpdate(int type) {
        if (!this.getLevel().getServer().isRedstoneEnabled()) {
            return 0;
        }

        if ((type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_REDSTONE) && !this.isGettingPower()) {
            this.getLevel().scheduleUpdate(this, 4);
            return 1;
        }

        if (type == Level.BLOCK_UPDATE_SCHEDULED && !this.isGettingPower()) {
            // Redstone event
            RedstoneUpdateEvent event = new RedstoneUpdateEvent(this);
            event.call();
            if (event.isCancelled()) {
                return 0;
            }

            this.getLevel().updateComparatorOutputLevelSelective(this, true);

            this.getLevel().setBlock(this, Block.get(BlockID.REDSTONE_LAMP), false, false);
        }
        return 0;
    }
}
