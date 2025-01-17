package cn.nukkit.block.impl;

import static cn.nukkit.block.property.CommonBlockProperties.FACING_DIRECTION;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockTransparentMeta;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import cn.nukkit.utils.Faceable;
import org.jetbrains.annotations.NotNull;

@Since("1.6.0.0-PNX")
@PowerNukkitOnly
public class BlockLightningRod extends BlockTransparentMeta implements Faceable {
    @PowerNukkitOnly
    @Since("1.6.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(FACING_DIRECTION);

    @Override
    public String getName() {
        return "LightningRod";
    }

    @Override
    public int getId() {
        return LIGHTNING_ROD;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Since("1.6.0.0-PNX")
    @PowerNukkitOnly
    @Override
    public boolean isSolid(BlockFace side) {
        return false;
    }

    @PowerNukkitOnly
    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Override
    public boolean canBeFlowedInto() {
        return true;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 6;
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
            Player player) {
        this.setBlockFace(face);
        this.getLevel().setBlock(block, this, true, true);
        return true;
    }

    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public BlockFace getBlockFace() {
        return this.getPropertyValue(FACING_DIRECTION);
    }

    @Override
    public void setBlockFace(BlockFace face) {
        this.setPropertyValue(FACING_DIRECTION, face);
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }
}
