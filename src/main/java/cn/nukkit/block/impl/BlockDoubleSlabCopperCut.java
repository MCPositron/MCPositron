package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockDoubleSlabCopperBase;
import cn.nukkit.block.property.value.OxidizationLevel;
import java.util.Locale;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author joserobjr
 * @since 2021-06-14
 */
public class BlockDoubleSlabCopperCut extends BlockDoubleSlabCopperBase {
    public BlockDoubleSlabCopperCut() {
        this(0);
    }

    public BlockDoubleSlabCopperCut(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DOUBLE_CUT_COPPER_SLAB;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        StringBuilder sb = new StringBuilder(30);
        if (isWaxed()) {
            sb.append("Waxed ");
        }
        OxidizationLevel oxidizationLevel = getOxidizationLevel();
        if (!OxidizationLevel.UNAFFECTED.equals(oxidizationLevel)) {
            String name = oxidizationLevel.name();
            sb.append(name.charAt(0))
                    .append(name.substring(1).toLowerCase(Locale.ENGLISH))
                    .append(' ');
        }
        return sb.append("Cut Copper").toString();
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getSingleSlabId() {
        return CUT_COPPER_SLAB;
    }

    @Since("FUTURE")
    @PowerNukkitOnly
    @Override
    protected int getCopperId(boolean waxed, @Nullable OxidizationLevel oxidizationLevel) {
        if (oxidizationLevel == null) {
            return getId();
        }
        switch (oxidizationLevel) {
            case UNAFFECTED:
                return waxed ? WAXED_DOUBLE_CUT_COPPER_SLAB : DOUBLE_CUT_COPPER_SLAB;
            case EXPOSED:
                return waxed ? WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB : EXPOSED_DOUBLE_CUT_COPPER_SLAB;
            case WEATHERED:
                return waxed ? WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB : WEATHERED_DOUBLE_CUT_COPPER_SLAB;
            case OXIDIZED:
                return waxed ? WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB : OXIDIZED_DOUBLE_CUT_COPPER_SLAB;
            default:
                return getId();
        }
    }

    @Since("FUTURE")
    @PowerNukkitOnly
    @NotNull @Override
    public OxidizationLevel getOxidizationLevel() {
        return OxidizationLevel.UNAFFECTED;
    }
}
