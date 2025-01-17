package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockTransparentMeta;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BooleanBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemCake;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class BlockCandleCake extends BlockTransparentMeta {
    @PowerNukkitOnly
    @Since("FUTURE")
    private static final BooleanBlockProperty LIT = new BooleanBlockProperty("lit", false);

    @PowerNukkitXOnly
    @Since("1.6.0.0-PNX")
    public static final BlockProperties PROPERTIES = new BlockProperties(LIT);

    public BlockCandleCake(int meta) {
        super(meta);
    }

    public BlockCandleCake() {
        this(0);
    }

    @Override
    public String getName() {
        return "Cake Block With " + getColorName() + " Candle";
    }

    protected String getColorName() {
        return "Simple";
    }

    @Override
    public int getId() {
        return CANDLE_CAKE;
    }

    @Since("1.6.0.0-PNX")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public double getResistance() {
        return 0.5;
    }

    @PowerNukkitOnly
    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Override
    public double getMinX() {
        return this.x() + (1 + getDamage() * 2) / 16d;
    }

    @Override
    public double getMinY() {
        return this.y();
    }

    @Override
    public double getMinZ() {
        return this.z() + 0.0625;
    }

    @Override
    public double getMaxX() {
        return this.x() - 0.0625 + 1;
    }

    @Override
    public double getMaxY() {
        return this.y() + 0.5;
    }

    @Override
    public double getMaxZ() {
        return this.z() - 0.0625 + 1;
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
        if (down().getId() != Block.AIR) {
            getLevel().setBlock(block, this, true, true);
            return true;
        }
        return false;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (down().getId() == Block.AIR) {
                getLevel().setBlock(this, Block.get(BlockID.AIR), true);
                return Level.BLOCK_UPDATE_NORMAL;
            }
        }

        return 0;
    }

    protected BlockCandle toCandleForm() {
        return new BlockCandle();
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {toCandleForm().toItem()};
    }

    @Override
    public Item toItem() {
        return new ItemCake();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (getPropertyValue(LIT) && item.getId() != ItemID.FLINT_AND_STEEL) {
            setPropertyValue(LIT, false);
            getLevel().addSound(this, Sound.RANDOM_FIZZ);
            getLevel().setBlock(this, this, true, true);
            return true;
        } else if (!getPropertyValue(LIT) && item.getId() == ItemID.FLINT_AND_STEEL) {
            setPropertyValue(LIT, true);
            getLevel().addSound(this, Sound.FIRE_IGNITE);
            getLevel().setBlock(this, this, true, true);
            return true;
        } else if (player != null
                && (player.getFoodData().getLevel() < player.getFoodData().getMaxLevel() || player.isCreative())) {
            final Block cake = new BlockCake();
            this.getLevel().setBlock(this, cake, true, true);
            this.getLevel().dropItem(this.add(0.5, 0.5, 0.5), getDrops(null)[0]);
            return this.getLevel().getBlock(this).onActivate(Item.get(0), player);
        }
        return false;
    }

    @Override
    public int getComparatorInputOverride() {
        return 14;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    @PowerNukkitOnly
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    @PowerNukkitOnly
    public boolean sticksToPiston() {
        return false;
    }
}
