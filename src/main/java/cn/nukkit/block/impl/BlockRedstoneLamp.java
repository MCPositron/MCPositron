package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.event.redstone.RedstoneUpdateEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import cn.nukkit.utils.RedstoneComponent;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Nukkit Project Team
 */
@PowerNukkitDifference(info = "Implements RedstoneComponent.", since = "1.4.0.0-PN")
public class BlockRedstoneLamp extends BlockSolid implements RedstoneComponent {

    public BlockRedstoneLamp() {}

    @Override
    public String getName() {
        return "Redstone Lamp";
    }

    @Override
    public int getId() {
        return REDSTONE_LAMP;
    }

    @Override
    public double getHardness() {
        return 0.3D;
    }

    @Override
    public double getResistance() {
        return 1.5D;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @PowerNukkitDifference(info = "Using new method for checking if powered", since = "1.4.0.0-PN")
    @Override
    public boolean place(
            @NotNull Item item,
            @NotNull Block block,
            @NotNull Block target,
            @NotNull BlockFace face,
            double fx,
            double fy,
            double fz,
            @Nullable Player player) {
        if (this.isGettingPower()) {
            this.getLevel().setBlock(this, Block.get(BlockID.LIT_REDSTONE_LAMP), false, true);
        } else {
            this.getLevel().setBlock(this, this, false, true);
        }
        return true;
    }

    @PowerNukkitDifference(
            info = "Redstone Event after Block powered check + use #isGettingPower() method" + " + trigger observer.",
            since = "1.4.0.0-PN")
    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_REDSTONE) {
            if (!this.getLevel().getServer().isRedstoneEnabled()) {
                return 0;
            }

            if (this.isGettingPower()) {
                // Redstone event
                RedstoneUpdateEvent event = new RedstoneUpdateEvent(this);
                event.call();
                if (event.isCancelled()) {
                    return 0;
                }

                this.getLevel().updateComparatorOutputLevelSelective(this, true);

                this.getLevel().setBlock(this, Block.get(BlockID.LIT_REDSTONE_LAMP), false, false);
                return 1;
            }
        }

        return 0;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {new ItemBlock(Block.get(BlockID.REDSTONE_LAMP))};
    }
}
