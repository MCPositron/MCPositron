package cn.nukkit.block;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.impl.BlockFence;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.exception.InvalidBlockPropertyValueException;
import cn.nukkit.block.property.value.WoodType;
import java.util.Optional;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public abstract class BlockFenceBase extends BlockFence {
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockFenceBase() {
        this(0);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockFenceBase(int meta) {
        super(meta);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return CommonBlockProperties.EMPTY_PROPERTIES;
    }

    @Deprecated
    @DeprecationDetails(
            reason = "Will always returns empty on this type. It is here for backward compatibility",
            since = "1.4.0.0-PN")
    @Override
    @PowerNukkitOnly
    public Optional<WoodType> getWoodType() {
        return Optional.empty();
    }

    @Deprecated
    @DeprecationDetails(reason = "Only accepts null. It is here for backward compatibility", since = "1.4.0.0-PN")
    @Override
    @PowerNukkitOnly
    public void setWoodType(@Nullable WoodType woodType) {
        if (woodType != null) {
            throw new InvalidBlockPropertyValueException(
                    WoodType.PROPERTY, null, woodType, "This block don't have a regular wood type");
        }
    }
}
