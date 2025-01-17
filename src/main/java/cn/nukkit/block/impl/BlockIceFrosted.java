package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockTransparentMeta;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.IntBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
public class BlockIceFrosted extends BlockTransparentMeta {

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final IntBlockProperty AGE = new IntBlockProperty("age", false, 3);

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(AGE);

    @PowerNukkitOnly
    public BlockIceFrosted() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockIceFrosted(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ICE_FROSTED;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public String getName() {
        return "Frosted Ice";
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
    public double getFrictionFactor() {
        return 0.98;
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
            Player player) {
        boolean success = super.place(item, block, target, face, fx, fy, fz, player);
        if (success) {
            getLevel().scheduleUpdate(this, ThreadLocalRandom.current().nextInt(20, 40));
        }
        return success;
    }

    @Override
    public boolean onBreak(Item item) {
        getLevel().setBlock(this, get(FLOWING_WATER), true);
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (getLevel().getBlockLightAt(getFloorX(), getFloorY(), getFloorZ()) > 11
                    && (ThreadLocalRandom.current().nextInt(3) == 0 || countNeighbors() < 4)) {
                slightlyMelt(true);
            } else {
                getLevel().scheduleUpdate(this, ThreadLocalRandom.current().nextInt(20, 40));
            }
        } else if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (countNeighbors() < 2) {
                getLevel().setBlock(this, layer, get(FLOWING_WATER), true);
            }
        }
        return super.onUpdate(type);
    }

    @Override
    public Item toItem() {
        return Item.AIR_ITEM;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @PowerNukkitOnly
    protected void slightlyMelt(boolean isSource) {
        int age = getDamage();
        if (age < 3) {
            setDamage(age + 1);
            getLevel().setBlock(this, layer, this, true);
            getLevel()
                    .scheduleUpdate(
                            getLevel().getBlock(this),
                            ThreadLocalRandom.current().nextInt(20, 40));
        } else {
            getLevel().setBlock(this, layer, get(FLOWING_WATER), true);
            if (isSource) {
                for (BlockFace face : BlockFace.values()) {
                    Block block = getSide(face);
                    if (block instanceof BlockIceFrosted) {
                        ((BlockIceFrosted) block).slightlyMelt(false);
                    }
                }
            }
        }
    }

    private int countNeighbors() {
        int neighbors = 0;
        for (BlockFace face : BlockFace.values()) {
            if (getSide(face).getId() == ICE_FROSTED && ++neighbors >= 4) {
                return neighbors;
            }
        }
        return neighbors;
    }
}
