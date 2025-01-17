package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockSolidMeta;
import cn.nukkit.block.property.ArrayBlockProperty;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BlockProperty;
import cn.nukkit.block.property.value.DirtType;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Sound;
import cn.nukkit.player.Player;
import java.util.Optional;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author MagicDroidX (Nukkit Project), kvetinac97
 */
public class BlockDirt extends BlockSolidMeta {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperty<DirtType> DIRT_TYPE = new ArrayBlockProperty<>("dirt_type", true, DirtType.class);

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(DIRT_TYPE);

    public BlockDirt() {
        this(0);
    }

    public BlockDirt(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DIRT;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    @NotNull public Optional<DirtType> getDirtType() {
        return Optional.of(getPropertyValue(DIRT_TYPE));
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setDirtType(@Nullable DirtType dirtType) {
        setPropertyValue(DIRT_TYPE, dirtType);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public double getResistance() {
        return 2.5;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_SHOVEL;
    }

    @Override
    public String getName() {
        return this.getDamage() == 0 ? "Dirt" : "Coarse Dirt";
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (!this.up().canBeReplaced()) {
            return false;
        }

        if (item.isHoe()) {
            item.useOn(this);
            this.getLevel().setBlock(this, this.getDamage() == 0 ? get(FARMLAND) : get(DIRT), true);
            if (player != null) {
                player.getLevel().addSound(player, Sound.USE_GRASS);
            }
            return true;
        } else if (item.isShovel()) {
            item.useOn(this);
            this.getLevel().setBlock(this, Block.get(BlockID.GRASS_PATH));
            if (player != null) {
                player.getLevel().addSound(player, Sound.USE_GRASS);
            }
            return true;
        }

        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {new ItemBlock(Block.get(BlockID.DIRT))};
    }
}
