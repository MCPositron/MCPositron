package cn.nukkit.block.impl;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockCrops;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.event.block.BlockSpreadEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;
import cn.nukkit.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Pub4Game
 * @since 03.01.2016
 */
public class BlockMycelium extends BlockSolid {

    public BlockMycelium() {}

    @Override
    public String getName() {
        return "Mycelium";
    }

    @Override
    public int getId() {
        return MYCELIUM;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_SHOVEL;
    }

    @Override
    public double getHardness() {
        return 0.6;
    }

    @Override
    public double getResistance() {
        return 2.5;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {new ItemBlock(Block.get(BlockID.DIRT))};
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_RANDOM) {
            if (getLevel().getFullLight(add(0, 1, 0)) >= BlockCrops.MINIMUM_LIGHT_LEVEL) {
                // TODO: light levels
                NukkitRandom random = new NukkitRandom();
                setX(random.nextRange((int) x() - 1, (int) x() + 1));
                setY(random.nextRange((int) y() - 1, (int) y() + 1));
                setZ(random.nextRange((int) z() - 1, (int) z() + 1));
                Block block = this.getLevel().getBlock(new Vector3(x(), y(), z()));
                if (block.getId() == Block.DIRT && block.getDamage() == 0) {
                    if (block.up().isTransparent()) {
                        BlockSpreadEvent event = new BlockSpreadEvent(block, this, Block.get(BlockID.MYCELIUM));
                        event.call();
                        if (!event.isCancelled()) {
                            this.getLevel().setBlock(block, event.getNewState());
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (!this.up().canBeReplaced()) {
            return false;
        }

        if (item.isShovel()) {
            item.useOn(this);
            this.getLevel().setBlock(this, Block.get(BlockID.GRASS_PATH));
            if (player != null) {
                player.getLevel().addSound(player, Sound.USE_GRASS);
            }
            return true;
        }
        return false;
    }
}
