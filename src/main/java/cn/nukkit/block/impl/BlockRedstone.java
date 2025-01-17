package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSolidMeta;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import cn.nukkit.utils.RedstoneComponent;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/*
 * @author Pub4Game
 * @since 2015/12/11
 */
@PowerNukkitDifference(info = "Implements RedstoneComponent and uses methods from it.", since = "1.4.0.0-PN")
public class BlockRedstone extends BlockSolidMeta implements RedstoneComponent {

    public BlockRedstone() {
        this(0);
    }

    public BlockRedstone(int meta) {
        super(0);
    }

    @Override
    public int getId() {
        return REDSTONE_BLOCK;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return CommonBlockProperties.EMPTY_PROPERTIES;
    }

    @Override
    public double getResistance() {
        return 10;
    }

    @Override
    public double getHardness() {
        return 5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        return "Redstone Block";
    }

    @Override
    @PowerNukkitOnly
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    @PowerNukkitDifference(info = "Update around redstone", since = "1.4.0.0-PN")
    public boolean place(
            @NotNull Item item,
            @NotNull Block block,
            @NotNull Block target,
            @NotNull BlockFace face,
            double fx,
            double fy,
            double fz,
            @Nullable Player player) {
        if (super.place(item, block, target, face, fx, fy, fz, player)) {
            updateAroundRedstone();

            return true;
        }
        return false;
    }

    @Override
    @PowerNukkitDifference(info = "Update around redstone", since = "1.4.0.0-PN")
    public boolean onBreak(Item item) {
        if (!super.onBreak(item)) {
            return false;
        }

        updateAroundRedstone();
        return true;
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    @Override
    public int getWeakPower(BlockFace face) {
        return 15;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
