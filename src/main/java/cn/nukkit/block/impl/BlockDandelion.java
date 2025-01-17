package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.value.SmallFlowerType;
import org.jetbrains.annotations.NotNull;

/**
 * @author xtypr
 * @since 2015/12/2
 */
public class BlockDandelion extends BlockFlower {
    public BlockDandelion() {
        this(0);
    }

    public BlockDandelion(int meta) {
        super(0);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return CommonBlockProperties.EMPTY_PROPERTIES;
    }

    @Override
    public int getId() {
        return DANDELION;
    }

    @Override
    protected Block getUncommonFlower() {
        return get(RED_FLOWER);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public void setFlowerType(SmallFlowerType flowerType) {
        setOnSingleFlowerType(SmallFlowerType.DANDELION, flowerType);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public SmallFlowerType getFlowerType() {
        return SmallFlowerType.DANDELION;
    }
}
