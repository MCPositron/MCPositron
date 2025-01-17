package cn.nukkit.block.impl;

import static cn.nukkit.block.property.CommonBlockProperties.PILLAR_AXIS;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXDifference;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockLog;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.value.WoodType;
import cn.nukkit.block.state.BlockState;
import cn.nukkit.block.state.IBlockState;
import cn.nukkit.block.state.exception.InvalidBlockStateException;
import org.jetbrains.annotations.NotNull;

/**
 * @author MagicDroidX (Nukkit Project)
 */
@PowerNukkitDifference(info = "Extends BlockLog instead of BlockSolidMeta only in PowerNukkit", since = "1.4.0.0-PN")
public class BlockWood extends BlockLog {

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(WoodType.PROPERTY, PILLAR_AXIS);

    public static final int OAK = 0;
    public static final int SPRUCE = 1;
    public static final int BIRCH = 2;
    public static final int JUNGLE = 3;

    public BlockWood() {
        this(0);
    }

    public BlockWood(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return LOG;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 2;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public WoodType getWoodType() {
        return getPropertyValue(WoodType.PROPERTY);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setWoodType(WoodType woodType) {
        setPropertyValue(WoodType.PROPERTY, woodType);
    }

    @Override
    public String getName() {
        return getWoodType().getEnglishName() + " Log";
    }

    @Since("1.5.1.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public Block forState(@NotNull IBlockState state) throws InvalidBlockStateException {
        int id = getId();
        if (id != LOG && id != LOG2) {
            return super.forState(state);
        }

        id = state.getBlockId();
        if (id != LOG && id != LOG2 || state.getBitSize() != 4) {
            return super.forState(state);
        }

        int exactInt = state.getExactIntStorage();
        if ((exactInt & 0b1100) == 0b1100) {
            int increment = state.getBlockId() == BlockID.LOG ? 0b000 : 0b100;
            return BlockState.of(BlockID.WOOD_BARK, (exactInt & 0b11) + increment)
                    .getBlock(this, layer);
        }

        return super.forState(state);
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 10;
    }

    @PowerNukkitOnly
    @Override
    @PowerNukkitXDifference(since = "1.20.0-r2", info = "make public")
    public BlockState getStrippedState() {
        int strippedId =
                switch (getWoodType()) {
                    case OAK -> STRIPPED_OAK_LOG;
                    case SPRUCE -> STRIPPED_SPRUCE_LOG;
                    case BIRCH -> STRIPPED_BIRCH_LOG;
                    case JUNGLE -> STRIPPED_JUNGLE_LOG;
                    case ACACIA -> STRIPPED_ACACIA_LOG;
                    case DARK_OAK -> STRIPPED_DARK_OAK_LOG;
                };
        return BlockState.of(strippedId).withProperty(PILLAR_AXIS, getPillarAxis());
    }
}
