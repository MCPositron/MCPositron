package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockFlowable;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BooleanBlockProperty;
import cn.nukkit.block.property.IntBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Sound;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gabriel8579
 * @since 2021-07-14
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockCandle extends BlockFlowable {

    @PowerNukkitOnly
    @Since("FUTURE")
    private static final BooleanBlockProperty LIT = new BooleanBlockProperty("lit", false);

    @PowerNukkitOnly
    @Since("FUTURE")
    private static final IntBlockProperty CANDLES = new IntBlockProperty("candles", false, 3, 0, 2);

    @PowerNukkitOnly
    @Since("FUTURE")
    private static final BlockProperties PROPERTIES = new BlockProperties(LIT, CANDLES);

    public BlockCandle() {
        super(0);
    }

    public BlockCandle(int meta) {
        super(meta);
    }

    protected Block toCakeForm() {
        return new BlockCandleCake();
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
        if (target.getId() == BlockID.CAKE_BLOCK && target.getDamage() == 0) { // 必须是完整的蛋糕才能插蜡烛
            target.getLevel().setBlock(target, toCakeForm(), true, true);
            return true;
        }
        if (target.up().getId() == getId()) {
            target = target.up();
        }
        if (target.getId() == getId()) {
            if (target.getPropertyValue(CANDLES) < 3) {
                target.setPropertyValue(CANDLES, target.getPropertyValue(CANDLES) + 1);
                getLevel().setBlock(target, target, true, true);
                return true;
            }
            return false;
        } else if (target instanceof BlockCandle) {
            return false;
        }

        setPropertyValue(CANDLES, 0);
        getLevel().setBlock(this, this, true, true);

        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {

        if (item.getId() != ItemID.FLINT_AND_STEEL && !item.isNull()) {
            return false;
        }

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
        }

        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {new ItemBlock(this, 0, getPropertyValue(CANDLES) + 1)};
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public String getName() {
        return "Candle";
    }

    @Override
    public int getId() {
        return BlockID.CANDLE;
    }

    @Override
    public int getLightLevel() {
        return getPropertyValue(LIT) ? getPropertyValue(CANDLES) * 3 : 0;
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public double getResistance() {
        return 0.1;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }
}
