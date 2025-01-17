package cn.nukkit.block.impl;

import static cn.nukkit.block.impl.BlockSapling.AGED;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.*;
import cn.nukkit.block.BlockCrops;
import cn.nukkit.block.property.ArrayBlockProperty;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.value.BambooLeafSize;
import cn.nukkit.block.property.value.BambooStalkThickness;
import cn.nukkit.event.block.BlockGrowEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.MathHelper;
import cn.nukkit.network.protocol.AnimatePacket;
import cn.nukkit.player.Player;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
public class BlockBamboo extends BlockTransparentMeta implements BlockFlowerPot.FlowerPotBlock {

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final ArrayBlockProperty<BambooStalkThickness> STALK_THICKNESS =
            new ArrayBlockProperty<>("bamboo_stalk_thickness", false, BambooStalkThickness.class);

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final ArrayBlockProperty<BambooLeafSize> LEAF_SIZE =
            new ArrayBlockProperty<>("bamboo_leaf_size", false, BambooLeafSize.class);

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(STALK_THICKNESS, LEAF_SIZE, AGED);

    public @PowerNukkitOnly static final int LEAF_SIZE_NONE = 0;
    public @PowerNukkitOnly static final int LEAF_SIZE_SMALL = 1;
    public @PowerNukkitOnly static final int LEAF_SIZE_LARGE = 2;

    @PowerNukkitOnly
    public BlockBamboo() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockBamboo(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BAMBOO;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public String getName() {
        return "Bamboo";
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (isSupportInvalid()) {
                getLevel().scheduleUpdate(this, 0);
            }
            return type;
        } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            getLevel().useBreakOn(this, null, null, true);
        } else if (type == Level.BLOCK_UPDATE_RANDOM) {
            Block up = up();
            if (getAge() == 0
                    && up.getId() == AIR
                    && getLevel().getFullLight(up) >= BlockCrops.MINIMUM_LIGHT_LEVEL
                    && ThreadLocalRandom.current().nextInt(3) == 0) {
                grow(up);
            }
            return type;
        }
        return 0;
    }

    @PowerNukkitOnly
    public boolean grow(Block up) {
        BlockBamboo newState = new BlockBamboo();
        if (isThick()) {
            newState.setThick(true);
            newState.setLeafSize(LEAF_SIZE_LARGE);
        } else {
            newState.setLeafSize(LEAF_SIZE_SMALL);
        }
        BlockGrowEvent blockGrowEvent = new BlockGrowEvent(up, newState);
        blockGrowEvent.call();
        if (!blockGrowEvent.isCancelled()) {
            Block newState1 = blockGrowEvent.getNewState();
            newState1.setX(x());
            newState1.setY(up.y());
            newState1.setZ(z());
            newState1.setLevel(getLevel());
            newState1.place(toItem(), up, this, BlockFace.DOWN, 0.5, 0.5, 0.5, null);
            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    public int countHeight() {
        int count = 0;
        Optional<Block> opt;
        Block down = this;
        while ((opt = down.down().firstInLayers(b -> b.getId() == BAMBOO)).isPresent()) {
            down = opt.get();
            if (++count >= 16) {
                break;
            }
        }
        return count;
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
        Block down = down();
        int downId = down.getId();
        if (downId != BAMBOO && downId != BAMBOO_SAPLING) {
            BlockBambooSapling sampling = new BlockBambooSapling();
            sampling.setX(x());
            sampling.setY(y());
            sampling.setZ(z());
            sampling.setLevel(getLevel());
            return sampling.place(item, block, target, face, fx, fy, fz, player);
        }

        boolean canGrow = true;

        if (downId == BAMBOO_SAPLING) {
            if (player != null) {
                AnimatePacket animatePacket = new AnimatePacket();
                animatePacket.action = AnimatePacket.Action.SWING_ARM;
                animatePacket.eid = player.getId();
                this.getLevel().addChunkPacket(player.getChunkX(), player.getChunkZ(), animatePacket);
            }
            setLeafSize(LEAF_SIZE_SMALL);
        }
        if (down instanceof BlockBamboo) {
            BlockBamboo bambooDown = (BlockBamboo) down;
            canGrow = bambooDown.getAge() == 0;
            boolean thick = bambooDown.isThick();
            if (!thick) {
                boolean setThick = true;
                for (int i = 2; i <= 3; i++) {
                    if (getSide(BlockFace.DOWN, i).getId() != BAMBOO) {
                        setThick = false;
                    }
                }
                if (setThick) {
                    setThick(true);
                    setLeafSize(LEAF_SIZE_LARGE);
                    bambooDown.setLeafSize(LEAF_SIZE_SMALL);
                    bambooDown.setThick(true);
                    bambooDown.setAge(1);
                    this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                    while ((down = down.down()) instanceof BlockBamboo) {
                        bambooDown = (BlockBamboo) down;
                        bambooDown.setThick(true);
                        bambooDown.setLeafSize(LEAF_SIZE_NONE);
                        bambooDown.setAge(1);
                        this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                    }
                } else {
                    setLeafSize(LEAF_SIZE_SMALL);
                    bambooDown.setAge(1);
                    this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                }
            } else {
                setThick(true);
                setLeafSize(LEAF_SIZE_LARGE);
                setAge(0);
                bambooDown.setLeafSize(LEAF_SIZE_LARGE);
                bambooDown.setAge(1);
                this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                down = bambooDown.down();
                if (down instanceof BlockBamboo) {
                    bambooDown = (BlockBamboo) down;
                    bambooDown.setLeafSize(LEAF_SIZE_SMALL);
                    bambooDown.setAge(1);
                    this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                    down = bambooDown.down();
                    if (down instanceof BlockBamboo) {
                        bambooDown = (BlockBamboo) down;
                        bambooDown.setLeafSize(LEAF_SIZE_NONE);
                        bambooDown.setAge(1);
                        this.getLevel().setBlock(bambooDown, bambooDown, false, true);
                    }
                }
            }
        } else if (isSupportInvalid()) {
            return false;
        }

        int height = canGrow ? countHeight() : 0;
        if (!canGrow
                || height >= 15
                || height >= 11 && ThreadLocalRandom.current().nextFloat() < 0.25F) {
            setAge(1);
        }

        this.getLevel().setBlock(this, this, false, true);
        return true;
    }

    @Override
    public boolean onBreak(Item item) {
        Optional<Block> down = down().firstInLayers(b -> b instanceof BlockBamboo);
        if (down.isPresent()) {
            BlockBamboo bambooDown = (BlockBamboo) down.get();
            int height = bambooDown.countHeight();
            if (height < 15 && (height < 11 || !(ThreadLocalRandom.current().nextFloat() < 0.25F))) {
                bambooDown.setAge(0);
                this.getLevel().setBlock(bambooDown, bambooDown.layer, bambooDown, false, true);
            }
        }
        return super.onBreak(item);
    }

    @Override
    public boolean canPassThrough() {
        return true;
    }

    private boolean isSupportInvalid() {
        int downId = down().getId();
        return downId != BAMBOO
                && downId != DIRT
                && downId != GRASS
                && downId != SAND
                && downId != GRAVEL
                && downId != PODZOL
                && downId != BAMBOO_SAPLING;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(new BlockBamboo());
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 5;
    }

    @PowerNukkitOnly
    public boolean isThick() {
        return getBambooStalkThickness().equals(BambooStalkThickness.THICK);
    }

    @PowerNukkitOnly
    public void setThick(boolean thick) {
        setBambooStalkThickness(thick ? BambooStalkThickness.THICK : BambooStalkThickness.THIN);
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public BambooStalkThickness getBambooStalkThickness() {
        return getPropertyValue(STALK_THICKNESS);
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public void setBambooStalkThickness(@NotNull BambooStalkThickness value) {
        setPropertyValue(STALK_THICKNESS, value);
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @PowerNukkitOnly
    @Deprecated
    @DeprecationDetails(
            by = "PowerNukkit",
            since = "1.5.0.0-PN",
            replaceWith = "getBambooLeafSize",
            reason = "magic values")
    public int getLeafSize() {
        return getBambooLeafSize().ordinal();
    }

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public BambooLeafSize getBambooLeafSize() {
        return getPropertyValue(LEAF_SIZE);
    }

    @Deprecated
    @DeprecationDetails(
            by = "PowerNukkit",
            since = "1.5.0.0-PN",
            replaceWith = "getBambooLeafSize",
            reason = "magic values")
    @PowerNukkitOnly
    public void setLeafSize(int leafSize) {
        leafSize = MathHelper.clamp(leafSize, LEAF_SIZE_NONE, LEAF_SIZE_LARGE) & 0b11;
        setDamage(getDamage() & (DATA_MASK ^ 0b110) | (leafSize << 1));
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (item.isFertilizer()) {
            int top = (int) y();
            int count = 1;

            for (int i = 1; i <= 16; i++) {
                int id = this.getLevel().getBlockIdAt(this.getFloorX(), this.getFloorY() - i, this.getFloorZ());
                if (id == BAMBOO) {
                    count++;
                } else {
                    break;
                }
            }

            for (int i = 1; i <= 16; i++) {
                int id = this.getLevel().getBlockIdAt(this.getFloorX(), this.getFloorY() + i, this.getFloorZ());
                if (id == BAMBOO) {
                    top++;
                    count++;
                } else {
                    break;
                }
            }

            // 15格以上需要嫁接（放置竹子）
            if (count >= 15) {
                return false;
            }

            boolean success = false;

            Block block = this.up(top - (int) y() + 1);
            if (block.getId() == BlockID.AIR) {
                success = grow(block);
            }

            if (success) {
                if (player != null && player.isSurvival()) {
                    item.count--;
                }
                getLevel().addParticle(new BoneMealParticle(this));
            }

            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    public int getAge() {
        return getBooleanValue(AGED) ? 1 : 0;
    }

    @PowerNukkitOnly
    public void setAge(int age) {
        age = MathHelper.clamp(age, 0, 1);
        setBooleanValue(AGED, age == 1);
    }

    @PowerNukkitOnly
    @Override
    public boolean breaksWhenMoved() {
        return true;
    }
}
