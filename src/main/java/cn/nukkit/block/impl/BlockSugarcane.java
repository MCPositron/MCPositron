package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockFlowable;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.IntBlockProperty;
import cn.nukkit.event.block.BlockGrowEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemSugarcane;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Pub4Game
 * @since 09.01.2016
 */
public class BlockSugarcane extends BlockFlowable {

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final IntBlockProperty AGE = CommonBlockProperties.AGE_15;

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(AGE);

    public BlockSugarcane() {
        this(0);
    }

    public BlockSugarcane(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Sugarcane";
    }

    @Override
    public int getId() {
        return REEDS;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public Item toItem() {
        return new ItemSugarcane();
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (item.isFertilizer()) { // Bonemeal
            int count = 1;

            for (int i = 1; i <= 2; i++) {
                int id = this.getLevel().getBlockIdAt(this.getFloorX(), this.getFloorY() - i, this.getFloorZ());

                if (id == REEDS) {
                    count++;
                }
            }

            if (count < 3) {
                boolean success = false;
                int toGrow = 3 - count;

                for (int i = 1; i <= toGrow; i++) {
                    Block block = this.up(i);
                    if (block.getId() == 0) {
                        BlockGrowEvent event = new BlockGrowEvent(block, Block.get(BlockID.REEDS));
                        event.call();

                        if (!event.isCancelled()) {
                            this.getLevel().setBlock(block, event.getNewState(), true);
                            success = true;
                        }
                    } else if (block.getId() != REEDS) {
                        break;
                    }
                }

                if (success) {
                    if (player != null && player.getGamemode().isSurvival()) {
                        item.count--;
                    }

                    this.getLevel().addParticle(new BoneMealParticle(this));
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public int onUpdate(int type) {
        Level level = getLevel();
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            level.scheduleUpdate(this, 0);
            return type;
        }

        if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (!isSupportValid()) {
                level.useBreakOn(this);
            }
            return type;
        }

        if (type == Level.BLOCK_UPDATE_RANDOM) {
            if (!isSupportValid()) {
                level.scheduleUpdate(this, 0);
                return type;
            }
            if (getDamage() < 15) {
                setDamage(this.getDamage() + 1);
                level.setBlock(this, this, false);
                return type;
            }
            Block up = up();
            if (up.getId() != AIR) {
                return type;
            }

            int height = 0;
            for (Block current = this; height < 3 && current.getId() == REEDS; height++) {
                current = current.down();
            }
            if (height >= 3) {
                return type;
            }

            BlockGrowEvent event = new BlockGrowEvent(up, Block.get(BlockID.REEDS));
            event.call();

            if (event.isCancelled()) {
                return type;
            }

            if (!level.setBlock(up, Block.get(BlockID.REEDS), false)) {
                return type;
            }

            setDamage(0);
            level.setBlock(this, this, false);
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
        if (block.getId() != AIR) {
            return false;
        }
        if (isSupportValid()) {
            this.getLevel().setBlock(block, this, true);
            return true;
        }
        return false;
    }

    /**
     * @since 1.2.0.2-PN
     */
    private boolean isSupportValid() {
        Block down = this.down();
        int downId = down.getId();
        if (downId == REEDS) {
            return true;
        }
        if (downId != GRASS && downId != DIRT && downId != SAND || down.getId() == PODZOL) {
            return false;
        }
        for (BlockFace face : BlockFace.Plane.HORIZONTAL) {
            Block possibleWater = down.getSide(face);
            if (possibleWater instanceof BlockWater
                    || possibleWater instanceof BlockIceFrosted
                    || possibleWater.getLevelBlockAtLayer(1) instanceof BlockWater) {
                return true;
            }
        }
        return false;
    }
}
