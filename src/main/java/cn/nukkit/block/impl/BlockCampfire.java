package cn.nukkit.block.impl;

import static cn.nukkit.block.property.CommonBlockProperties.DIRECTION;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.*;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.BooleanBlockProperty;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityCampfire;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPotion;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.entity.projectile.EntityProjectile;
import cn.nukkit.entity.projectile.EntitySmallFireBall;
import cn.nukkit.event.entity.EntityCombustByBlockEvent;
import cn.nukkit.event.entity.EntityDamageByBlockEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.inventory.CampfireInventory;
import cn.nukkit.inventory.CampfireRecipe;
import cn.nukkit.inventory.ContainerInventory;
import cn.nukkit.item.*;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.SimpleAxisAlignedBB;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.player.Player;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Faceable;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
@Log4j2
public class BlockCampfire extends BlockTransparentMeta implements Faceable, BlockEntityHolder<BlockEntityCampfire> {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BooleanBlockProperty EXTINGUISHED = new BooleanBlockProperty("extinguished", false);

    public static final BlockProperties PROPERTIES =
            new BlockProperties(EXTINGUISHED, CommonBlockProperties.CARDINAL_DIRECTION);

    @PowerNukkitOnly
    public BlockCampfire() {
        this(0);
    }

    @PowerNukkitOnly
    public BlockCampfire(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CAMPFIRE_BLOCK;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    @NotNull @Override
    public String getBlockEntityType() {
        return BlockEntity.CAMPFIRE;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull @Override
    public Class<? extends BlockEntityCampfire> getBlockEntityClass() {
        return BlockEntityCampfire.class;
    }

    @Override
    public int getLightLevel() {
        return isExtinguished() ? 0 : 15;
    }

    @Override
    public double getResistance() {
        return 2;
    }

    @Override
    public double getHardness() {
        return 5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {MinecraftItemID.CHARCOAL.get(2)};
    }

    @Override
    public boolean canSilkTouch() {
        return true;
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
        if (down().getId() == CAMPFIRE_BLOCK) {
            return false;
        }

        final Block layer0 = getLevel().getBlock(this, 0);
        final Block layer1 = getLevel().getBlock(this, 1);

        setBlockFace(player != null ? player.getDirection().getOpposite() : null);
        boolean defaultLayerCheck = (block instanceof BlockWater && ((BlockWater) block).isSourceOrFlowingDown())
                || block instanceof BlockIceFrosted;
        boolean layer1Check = (layer1 instanceof BlockWater && ((BlockWater) layer1).isSourceOrFlowingDown())
                || layer1 instanceof BlockIceFrosted;
        if (defaultLayerCheck || layer1Check) {
            setExtinguished(true);
            this.getLevel().addSound(this, Sound.RANDOM_FIZZ, 0.5f, 2.2f);
            this.getLevel().setBlock(this, 1, defaultLayerCheck ? block : layer1, false, false);
        } else {
            this.getLevel().setBlock(this, 1, Block.get(BlockID.AIR), false, false);
        }

        this.getLevel().setBlock(block, this, true, true);
        try {
            CompoundTag nbt = new CompoundTag();

            if (item.hasCustomBlockData()) {
                Map<String, Tag> customData = item.getCustomBlockData().getTags();
                for (Map.Entry<String, Tag> tag : customData.entrySet()) {
                    nbt.put(tag.getKey(), tag.getValue());
                }
            }

            createBlockEntity(nbt);
        } catch (Exception e) {
            log.warn("Failed to create the block entity {} at {}", getBlockEntityType(), getLocation(), e);
            getLevel().setBlock(layer0, 0, layer0, true);
            getLevel().setBlock(layer1, 0, layer1, true);
            return false;
        }

        this.getLevel().updateAround(this);
        return true;
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    @Override
    public void onEntityCollide(Entity entity) {
        if (isExtinguished()) {
            if (entity.isOnFire()) {
                setExtinguished(false);
                getLevel().setBlock(this, this, true);
            }
            return;
        }

        if (entity.hasEffect(Effect.FIRE_RESISTANCE)
                || entity instanceof EntityProjectile
                || !entity.attack(getDamageEvent(entity))
                || !entity.isAlive()) {
            return;
        }

        EntityCombustByBlockEvent event = new EntityCombustByBlockEvent(this, entity, 8);
        event.call();
        if (!event.isCancelled() && entity.isAlive()) {
            entity.setOnFire(event.getDuration());
        }
    }

    @PowerNukkitOnly
    @Since("1.5.1.0-PN")
    protected EntityDamageEvent getDamageEvent(Entity entity) {
        return new EntityDamageByBlockEvent(this, entity, EntityDamageEvent.DamageCause.FIRE, 1);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (!isExtinguished()) {
                Block layer1 = getLevelBlockAtLayer(1);
                if (layer1 instanceof BlockWater || layer1 instanceof BlockIceFrosted) {
                    setExtinguished(true);
                    this.getLevel().setBlock(this, this, true, true);
                    this.getLevel().addSound(this, Sound.RANDOM_FIZZ, 0.5f, 2.2f);
                }
            }
            return type;
        }
        return 0;
    }

    @Override
    public boolean onActivate(@NotNull Item item, @Nullable Player player) {
        if (item.getId() == BlockID.AIR || item.getCount() <= 0) {
            return false;
        }

        BlockEntityCampfire campfire = getOrCreateBlockEntity();

        boolean itemUsed = false;
        if (!isExtinguished()) {
            if (item.isShovel()) {
                setExtinguished(true);
                this.getLevel().setBlock(this, this, true, true);
                this.getLevel().addSound(this, Sound.RANDOM_FIZZ, 0.5f, 2.2f);
                itemUsed = true;
            }
        } else if (item.getId() == ItemID.FLINT_AND_STEEL || item.getEnchantment(Enchantment.ID_FIRE_ASPECT) != null) {
            item.useOn(this);
            setExtinguished(false);
            this.getLevel().setBlock(this, this, true, true);
            campfire.scheduleUpdate();
            this.getLevel().addSound(this, Sound.FIRE_IGNITE);
            itemUsed = true;
        }

        Item cloned = item.clone();
        cloned.setCount(1);
        CampfireInventory inventory = campfire.getInventory();
        if (inventory.canAddItem(cloned)) {
            CampfireRecipe recipe =
                    this.getLevel().getServer().getCraftingManager().matchCampfireRecipe(cloned);
            if (recipe != null) {
                inventory.addItem(cloned);
                item.setCount(item.getCount() - 1);
                return true;
            }
        }

        return itemUsed;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean onProjectileHit(@NotNull Entity projectile, @NotNull Position position, @NotNull Vector3 motion) {
        if ((projectile instanceof EntitySmallFireBall || (projectile.isOnFire() && projectile instanceof EntityArrow))
                && isExtinguished()) {
            setExtinguished(false);
            getLevel().setBlock(this, this, true);
            return true;
        } else if (projectile instanceof EntityPotion
                && !isExtinguished()
                && ((EntityPotion) projectile).potionId == 0) {
            setExtinguished(true);
            getLevel().setBlock(this, this, true);
            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Override
    public double getMaxY() {
        return y() + 0.4371948;
    }

    @Override
    protected AxisAlignedBB recalculateCollisionBoundingBox() {
        return new SimpleAxisAlignedBB(x(), y(), z(), x() + 1, y() + 1, z() + 1);
    }

    @PowerNukkitOnly
    public boolean isExtinguished() {
        return getBooleanValue(EXTINGUISHED);
    }

    @PowerNukkitOnly
    public void setExtinguished(boolean extinguished) {
        setBooleanValue(EXTINGUISHED, extinguished);
    }

    @Override
    public BlockFace getBlockFace() {
        return getPropertyValue(DIRECTION);
    }

    @Override
    @PowerNukkitOnly
    @Since("1.3.0.0-PN")
    public void setBlockFace(BlockFace face) {
        setPropertyValue(DIRECTION, face);
    }

    @Override
    public String getName() {
        return "Campfire";
    }

    @Override
    public Item toItem() {
        return new ItemCampfire();
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride() {
        BlockEntityCampfire blockEntity = getBlockEntity();

        if (blockEntity != null) {
            return ContainerInventory.calculateRedstone(blockEntity.getInventory());
        }

        return super.getComparatorInputOverride();
    }

    @Override
    @PowerNukkitOnly
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    @PowerNukkitOnly
    public boolean canBePulled() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }
}
