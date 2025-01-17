package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockVinesNether;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.IntBlockProperty;
import cn.nukkit.block.property.exception.InvalidBlockPropertyMetaException;
import cn.nukkit.math.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * Properties and behaviour definitions of the {@link BlockID#WEEPING_VINES} block.
 * @author joserobjr
 */
@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockVinesWeeping extends BlockVinesNether {
    /**
     * Increments for every block the weeping vine grows.
     */
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final IntBlockProperty WEEPING_VINES_AGE = new IntBlockProperty("weeping_vines_age", false, 25);

    /**
     * Holds the {@code weeping_vines} block property definitions.
     */
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(WEEPING_VINES_AGE);

    /**
     * Creates a {@code weeping_vine} with age {@code 0}.
     */
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockVinesWeeping() {}

    /**
     * Creates a {@code weeping_vine} from a meta compatible with {@link #getProperties()}.
     * @throws InvalidBlockPropertyMetaException If the meta is incompatible
     */
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockVinesWeeping(int meta) throws InvalidBlockPropertyMetaException {
        super(meta);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public int getId() {
        return WEEPING_VINES;
    }

    @Override
    public String getName() {
        return "Weeping Vines";
    }

    @NotNull @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public BlockFace getGrowthDirection() {
        return BlockFace.DOWN;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getVineAge() {
        return getIntValue(WEEPING_VINES_AGE);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public void setVineAge(int vineAge) {
        setIntValue(WEEPING_VINES_AGE, vineAge);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getMaxVineAge() {
        return WEEPING_VINES_AGE.getMaxValue();
    }
}
