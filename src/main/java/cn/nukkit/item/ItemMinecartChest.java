package cn.nukkit.item;

import cn.nukkit.block.Block;
import cn.nukkit.block.impl.BlockRail;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityMinecartChest;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.player.Player;
import cn.nukkit.utils.Rail;

public class ItemMinecartChest extends Item {

    public ItemMinecartChest() {
        this(0, 1);
    }

    public ItemMinecartChest(Integer meta) {
        this(meta, 1);
    }

    public ItemMinecartChest(Integer meta, int count) {
        super(CHEST_MINECART, meta, count, "Minecart with Chest");
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(
            Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (Rail.isRailBlock(target)) {
            Rail.Orientation type = ((BlockRail) target).getOrientation();
            double adjacent = 0.0D;
            if (type.isAscending()) {
                adjacent = 0.5D;
            }
            EntityMinecartChest minecart = (EntityMinecartChest) Entity.createEntity(
                    "MinecartChest",
                    level.getChunk(target.getFloorX() >> 4, target.getFloorZ() >> 4),
                    new CompoundTag("")
                            .putList(new ListTag<>("Pos")
                                    .add(new DoubleTag("", target.x() + 0.5))
                                    .add(new DoubleTag("", target.y() + 0.0625D + adjacent))
                                    .add(new DoubleTag("", target.z() + 0.5)))
                            .putList(new ListTag<>("Motion")
                                    .add(new DoubleTag("", 0))
                                    .add(new DoubleTag("", 0))
                                    .add(new DoubleTag("", 0)))
                            .putList(new ListTag<>("Rotation")
                                    .add(new FloatTag("", 0))
                                    .add(new FloatTag("", 0))));

            if (minecart == null) {
                return false;
            }

            if (player.isAdventure() || player.isSurvival()) {
                Item item = player.getInventory().getItemInHand();
                item.setCount(item.getCount() - 1);
                player.getInventory().setItemInHand(item);
            }

            minecart.spawnToAll();
            return true;
        }
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
