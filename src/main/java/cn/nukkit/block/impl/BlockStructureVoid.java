package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.block.property.ArrayBlockProperty;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.value.StructureVoidType;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author good777LUCKY
 */
@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockStructureVoid extends BlockSolid {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final ArrayBlockProperty<StructureVoidType> STRUCTURE_VOID_TYPE =
            new ArrayBlockProperty<>("structure_void_type", false, StructureVoidType.class);

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(STRUCTURE_VOID_TYPE);

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStructureVoid() {
        // Does Nothing
    }

    @Override
    public int getId() {
        return STRUCTURE_VOID;
    }

    @Override
    public String getName() {
        return "Structure Void";
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull public StructureVoidType getType() {
        return getPropertyValue(STRUCTURE_VOID_TYPE);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public void setType(@Nullable StructureVoidType type) {
        setPropertyValue(STRUCTURE_VOID_TYPE, type);
    }

    @Override
    public double getHardness() {
        return 0;
    }

    @Override
    public double getResistance() {
        return 0;
    }

    @Override
    public boolean canPassThrough() {
        return true;
    }

    @Since("1.3.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean isSolid(BlockFace side) {
        return false;
    }

    @Override
    public boolean isBreakable(Item item) {
        return false;
    }

    @Override
    public boolean canHarvestWithHand() {
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
}
