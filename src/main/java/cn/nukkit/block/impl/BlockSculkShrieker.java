package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockEntityHolder;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BooleanBlockProperty;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySculkShrieker;
import cn.nukkit.item.ItemTool;
import org.jetbrains.annotations.NotNull;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class BlockSculkShrieker extends BlockSolid implements BlockEntityHolder<BlockEntitySculkShrieker> {

    public static final BooleanBlockProperty ACTIVE = new BooleanBlockProperty("active", false);
    public static final BooleanBlockProperty CAN_SUMMON = new BooleanBlockProperty("can_summon", false);
    public static final BlockProperties PROPERTIES = new BlockProperties(ACTIVE, CAN_SUMMON);

    @Override
    public String getName() {
        return "Sculk Shrieker";
    }

    @Override
    public int getId() {
        return SCULK_SHRIEKER;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Override
    public boolean canBePulled() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public double getResistance() {
        return 3;
    }

    @Override
    public double getHardness() {
        return 3.0;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_HOE;
    }

    @NotNull @Override
    public Class<? extends BlockEntitySculkShrieker> getBlockEntityClass() {
        return BlockEntitySculkShrieker.class;
    }

    @NotNull @Override
    public String getBlockEntityType() {
        return BlockEntity.SCULK_SHRIEKER;
    }
}
