package cn.nukkit.block.impl;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityCauldron;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityCombustByBlockEvent;
import cn.nukkit.event.entity.EntityDamageByBlockEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerBucketEmptyEvent;
import cn.nukkit.event.player.PlayerBucketFillEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBucket;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.level.Sound;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.player.Player;
import cn.nukkit.potion.Effect;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
@Deprecated(since = "1.20.10-r1")
@DeprecationDetails(since = "1.20.10-r1", reason = "Use BlockCauldron instead")
public class BlockCauldronLava extends BlockCauldron {
    @PowerNukkitOnly
    public BlockCauldronLava() {
        this(0x8);
    }

    @PowerNukkitOnly
    public BlockCauldronLava(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Lava Cauldron";
    }

    @Override
    public int getId() {
        return LAVA_CAULDRON;
    }

    @Override
    public int getLightLevel() {
        return 15;
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    @Override
    protected AxisAlignedBB recalculateCollisionBoundingBox() {
        return shrink(0.3, 0.3, 0.3);
    }

    @PowerNukkitOnly
    @Override
    public void setFillLevel(int fillLevel) {
        super.setFillLevel(fillLevel);
        setDamage(getDamage() | 0x8);
    }

    @Override
    public void onEntityCollide(Entity entity) {
        // Always setting the duration to 15 seconds? TODO
        EntityCombustByBlockEvent event = new EntityCombustByBlockEvent(this, entity, 15);
        event.call();
        if (!event.isCancelled()
                // Making sure the entity is actually alive and not invulnerable.
                && entity.isAlive()
                && entity.noDamageTicks == 0) {
            entity.setOnFire(event.getDuration());
        }

        if (!entity.hasEffect(Effect.FIRE_RESISTANCE)) {
            entity.attack(new EntityDamageByBlockEvent(this, entity, EntityDamageEvent.DamageCause.LAVA, 4));
        }
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        BlockEntity be = this.getLevel().getBlockEntity(this);

        if (!(be instanceof BlockEntityCauldron)) {
            return false;
        }

        BlockEntityCauldron cauldron = (BlockEntityCauldron) be;

        switch (item.getId()) {
            case Item.BUCKET:
                ItemBucket bucket = (ItemBucket) item;
                if (bucket.getFishEntityId() != null) {
                    break;
                }
                if (item.getDamage() == 0) { // empty
                    if (!isFull() || cauldron.isCustomColor() || cauldron.hasPotion()) {
                        break;
                    }

                    PlayerBucketFillEvent ev = new PlayerBucketFillEvent(
                            player,
                            this,
                            null,
                            this,
                            item,
                            MinecraftItemID.LAVA_BUCKET.get(1, bucket.getCompoundTag()));
                    ev.call();
                    if (!ev.isCancelled()) {
                        replaceBucket(bucket, player, ev.getItem());
                        this.setFillLevel(FILL_LEVEL.getMinValue(), player); // empty
                        this.getLevel().setBlock(this, new BlockCauldron(0), true);
                        cauldron.clearCustomColor();
                        this.getLevel().addSound(this.add(0.5, 1, 0.5), Sound.BUCKET_FILL_LAVA);
                    }
                } else if (bucket.isWater() || bucket.isLava()) { // water or lava bucket
                    if (isFull() && !cauldron.isCustomColor() && !cauldron.hasPotion() && item.getDamage() == 10) {
                        break;
                    }

                    PlayerBucketEmptyEvent event = new PlayerBucketEmptyEvent(
                            player, this, null, this, item, MinecraftItemID.BUCKET.get(1, bucket.getCompoundTag()));
                    event.call();
                    if (!event.isCancelled()) {
                        replaceBucket(bucket, player, event.getItem());

                        if (cauldron.hasPotion()) { // if has potion
                            clearWithFizz(cauldron);
                        } else if (bucket.isLava()) { // lava bucket
                            this.setFillLevel(FILL_LEVEL.getMaxValue(), player); // fill
                            cauldron.clearCustomColor();
                            this.getLevel().setBlock(this, this, true);
                            this.getLevel().addSound(this.add(0.5, 1, 0.5), Sound.BUCKET_EMPTY_LAVA);
                        } else {
                            if (isEmpty()) {
                                this.getLevel().setBlock(this, new BlockCauldron(6), true, true);
                                cauldron.clearCustomColor();
                                this.getLevel().addSound(this.add(0.5, 1, 0.5), Sound.CAULDRON_FILLWATER);
                            } else {
                                clearWithFizz(cauldron);
                            }
                        }
                    }
                }
                break;
            case Item.POTION:
            case Item.SPLASH_POTION:
            case Item.LINGERING_POTION:
                if (!isEmpty()
                        && (cauldron.hasPotion()
                                ? cauldron.getPotionId() != item.getDamage()
                                : item.getDamage() != 0)) {
                    clearWithFizz(cauldron);
                    break;
                }
                return super.onActivate(item, player);
            case Item.GLASS_BOTTLE:
                if (!isEmpty() && cauldron.hasPotion()) {
                    return super.onActivate(item, player);
                }
            default:
                return true;
        }

        this.getLevel().updateComparatorOutputLevel(this);
        return true;
    }
}
