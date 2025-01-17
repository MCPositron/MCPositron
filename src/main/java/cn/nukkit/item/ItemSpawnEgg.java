package cn.nukkit.item;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.CreatureSpawnEvent;
import cn.nukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.vibration.VibrationEvent;
import cn.nukkit.level.vibration.VibrationType;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.player.Player;
import java.util.Random;
import javax.annotation.Nullable;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class ItemSpawnEgg extends Item {

    public ItemSpawnEgg() {
        this(0, 1);
    }

    public ItemSpawnEgg(Integer meta) {
        this(meta, 1);
    }

    public ItemSpawnEgg(Integer meta, int count) {
        this(SPAWN_EGG, meta, count, "Spawn Egg");
        updateName();
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    protected ItemSpawnEgg(int id, Integer meta, int count, String name) {
        super(id, meta, count, name);
    }

    @Override
    public void setDamage(Integer meta) {
        super.setDamage(meta);
        updateName();
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    protected void updateName() {
        String entityName = getEntityName();
        if (entityName == null) {
            name = "Spawn Egg";
        } else {
            name = entityName + " Spawn Egg";
        }
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(
            Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (player.isAdventure()) {
            return false;
        }

        FullChunk chunk = level.getChunk((int) block.x() >> 4, (int) block.z() >> 4);

        if (chunk == null) {
            return false;
        }

        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", block.x() + 0.5))
                        .add(new DoubleTag(
                                "",
                                target.getBoundingBox() == null
                                        ? block.y()
                                        : target.getBoundingBox().getMaxY() + 0.0001f))
                        .add(new DoubleTag("", block.z() + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", new Random().nextFloat() * 360))
                        .add(new FloatTag("", 0)));

        if (this.hasCustomName()) {
            nbt.putString("CustomName", this.getCustomName());
        }

        int networkId = getEntityNetworkId();
        CreatureSpawnEvent event = new CreatureSpawnEvent(networkId, block, nbt, SpawnReason.SPAWN_EGG);
        event.call();

        if (event.isCancelled()) {
            return false;
        }

        Entity entity = Entity.createEntity(networkId, chunk, nbt);

        if (entity != null) {
            if (player.isSurvival()) {
                player.getInventory().decreaseCount(player.getInventory().getHeldItemIndex());
            }
            entity.spawnToAll();

            level.getVibrationManager()
                    .callVibrationEvent(new VibrationEvent(player, entity.clone(), VibrationType.ENTITY_PLACE));

            return true;
        }

        return false;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public int getEntityNetworkId() {
        return this.meta;
    }

    @PowerNukkitOnly
    @Since("1.19.21-r1")
    @Nullable public String getEntityName() {
        String saveId = Entity.getSaveId(getEntityNetworkId());
        if (saveId == null) {
            return null;
        }
        return switch (saveId) {
            case "VillagerV1" -> "Villager";
            case "ZombieVillagerV1" -> "Zombie Villager";
            case "NPC" -> "NPC";
            default -> String.join(" ", saveId.split("(?=\\p{Lu})"));
        };
    }
}
