package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockCrops;
import cn.nukkit.block.BlockFlowable;
import cn.nukkit.block.BlockLiquid;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.event.block.BlockGrowEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.MathHelper;
import cn.nukkit.player.Player;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
public class BlockBambooSapling extends BlockFlowable {

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = BlockSapling.PROPERTIES;

    @PowerNukkitOnly
    public BlockBambooSapling() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockBambooSapling(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BAMBOO_SAPLING;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public String getName() {
        return "Bamboo Sapling";
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (isSupportInvalid()) {
                getLevel().useBreakOn(this, null, null, true);
            } else {
                Block up = up();
                if (up.getId() == BAMBOO) {
                    BlockBamboo upperBamboo = (BlockBamboo) up;
                    BlockBamboo newState = new BlockBamboo();
                    newState.setThick(upperBamboo.isThick());
                    getLevel().setBlock(this, newState, true, true);
                }
            }
            return type;
        } else if (type == Level.BLOCK_UPDATE_RANDOM) {
            Block up = up();
            if (getAge() == 0
                    && up.getId() == AIR
                    && getLevel().getFullLight(up) >= BlockCrops.MINIMUM_LIGHT_LEVEL
                    && ThreadLocalRandom.current().nextInt(3) == 0) {
                BlockBamboo newState = new BlockBamboo();
                newState.setLeafSize(BlockBamboo.LEAF_SIZE_SMALL);
                BlockGrowEvent blockGrowEvent = new BlockGrowEvent(up, newState);
                blockGrowEvent.call();
                if (!blockGrowEvent.isCancelled()) {
                    Block newState1 = blockGrowEvent.getNewState();
                    newState1.setY(up.y());
                    newState1.setX(x());
                    newState1.setZ(z());
                    newState1.setLevel(getLevel());
                    newState1.place(toItem(), up, this, BlockFace.DOWN, 0.5, 0.5, 0.5, null);
                }
            }
            return type;
        }
        return 0;
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
        if (isSupportInvalid()) {
            return false;
        }

        if (this.getLevelBlock() instanceof BlockLiquid || this.getLevelBlockAtLayer(1) instanceof BlockLiquid) {
            return false;
        }

        this.getLevel().setBlock(this, this, true, true);
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (item.isFertilizer()) {

            boolean success = false;
            Block block = this.up();
            if (block.getId() == AIR) {
                success = grow(block);
            }

            if (success) {
                if (player != null && player.getGamemode().isSurvival()) {
                    item.count--;
                }

                getLevel().addParticle(new BoneMealParticle(this));
            }

            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    public boolean grow(Block up) {
        BlockBamboo bamboo = new BlockBamboo();
        bamboo.setX(x());
        bamboo.setY(y());
        bamboo.setZ(z());
        bamboo.setLevel(getLevel());
        return bamboo.grow(up);
    }

    private boolean isSupportInvalid() {
        int downId = down().getId();
        return downId != DIRT && downId != GRASS && downId != SAND && downId != GRAVEL && downId != PODZOL;
    }

    @Override
    public double getResistance() {
        return 5;
    }

    @PowerNukkitOnly
    public int getAge() {
        return getDamage() & 0x1;
    }

    @PowerNukkitOnly
    public void setAge(int age) {
        age = MathHelper.clamp(age, 0, 1) & 0x1;
        setDamage(getDamage() & (DATA_MASK ^ 0x1) | age);
    }

    @Override
    public Item toItem() {
        return new ItemBlock(new BlockBamboo());
    }

    @Override
    public double getMinX() {
        return x() + 0.125;
    }

    @Override
    public double getMaxX() {
        return x() + 0.875;
    }

    @Override
    public double getMinZ() {
        return z() + 0.125;
    }

    @Override
    public double getMaxZ() {
        return z() + 0.875;
    }

    @Override
    public double getMaxY() {
        return y() + 0.875;
    }

    @Override
    public boolean isFertilizable() {
        return true;
    }
}
