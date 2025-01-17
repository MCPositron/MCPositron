package cn.nukkit.item;

import cn.nukkit.block.Block;
import cn.nukkit.block.impl.BlockWater;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityChestBoat;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.player.Player;

public abstract class ItemChestBoatBase extends Item {

    public ItemChestBoatBase(int id, Integer meta, int count, String name) {
        super(id, meta, count, name);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    public abstract int getBoatId();

    @Override
    public boolean onActivate(
            Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (face != BlockFace.UP || block instanceof BlockWater) return false;
        EntityChestBoat boat = (EntityChestBoat) Entity.createEntity(
                "ChestBoat",
                level.getChunk(block.getFloorX() >> 4, block.getFloorZ() >> 4),
                new CompoundTag("")
                        .putList(new ListTag<DoubleTag>("Pos")
                                .add(new DoubleTag("", block.x() + 0.5))
                                .add(new DoubleTag("", block.y() - (target instanceof BlockWater ? 0.375 : 0)))
                                .add(new DoubleTag("", block.z() + 0.5)))
                        .putList(new ListTag<DoubleTag>("Motion")
                                .add(new DoubleTag("", 0))
                                .add(new DoubleTag("", 0))
                                .add(new DoubleTag("", 0)))
                        .putList(new ListTag<FloatTag>("Rotation")
                                .add(new FloatTag("", (float) ((player.yaw() + 90f) % 360)))
                                .add(new FloatTag("", 0)))
                        .putInt("Variant", getBoatId()));

        if (boat == null) {
            return false;
        }

        if (player.isSurvival() || player.isAdventure()) {
            Item item = player.getInventory().getItemInHand();
            item.setCount(item.getCount() - 1);
            player.getInventory().setItemInHand(item);
        }

        boat.spawnToAll();
        return true;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
