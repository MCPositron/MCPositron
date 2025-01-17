package cn.nukkit.item;

import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.player.Player;

public class ItemEmptyMap extends Item {

    public ItemEmptyMap() {
        this(0, 1);
    }

    public ItemEmptyMap(Integer meta) {
        this(meta, 1);
    }

    public ItemEmptyMap(Integer meta, int count) {
        super(EMPTY_MAP, meta, count, "Empty Map");
        updateName();
    }

    @Override
    public void setDamage(Integer meta) {
        super.setDamage(meta);
        updateName();
    }

    private void updateName() {
        if (getDamage() == 2) {
            name = "Empty Locator Map";
        } else {
            name = "Empty Map";
        }
    }

    @Override
    public boolean onActivate(
            Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (!player.isCreative()) {
            this.count--;
        }
        player.getInventory().addItem(Item.get(MAP));
        return true;
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.isCreative()) {
            this.count--;
        }
        player.getInventory().addItem(Item.get(MAP));
        return true;
    }
}
