package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.player.Player;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
@ParametersAreNonnullByDefault
public class BlockDeny extends BlockSolid {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockDeny() {
        // Does nothing
    }

    @Override
    public int getId() {
        return DENY;
    }

    @Override
    public double getHardness() {
        return -1;
    }

    @Override
    public double getResistance() {
        return 18000000;
    }

    @Override
    public String getName() {
        return "Deny";
    }

    @Override
    public boolean isBreakable(Item item) {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    @PowerNukkitOnly
    public boolean canBePulled() {
        return false;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

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
        if (player != null && (!player.isCreative() || !player.isOp())) {
            return false;
        }
        return super.place(item, block, target, face, fx, fy, fz, player);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean isBreakable(
            Vector3 vector, int layer, BlockFace face, Item item, @Nullable Player player, boolean setBlockDestroy) {
        if (player != null && (!player.isCreative() || !player.isOp())) {
            return false;
        }
        return super.isBreakable(vector, layer, face, item, player, setBlockDestroy);
    }

    @Override
    public Item[] getDrops(Item item) {
        return Item.EMPTY_ARRAY;
    }
}
