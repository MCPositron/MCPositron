package cn.nukkit.inventory;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockentity.BlockEntityChest;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.BlockEventPacket;
import cn.nukkit.network.protocol.InventorySlotPacket;
import cn.nukkit.player.Player;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class DoubleChestInventory extends ContainerInventory implements InventoryHolder {

    private final ChestInventory left;
    private final ChestInventory right;

    public DoubleChestInventory(BlockEntityChest left, BlockEntityChest right) {
        super(null, InventoryType.DOUBLE_CHEST);
        this.holder = this;

        this.left = left.getRealInventory();
        this.left.setDoubleInventory(this);

        this.right = right.getRealInventory();
        this.right.setDoubleInventory(this);

        Map<Integer, Item> items = new HashMap<>();
        // First we add the items from the left chest
        for (int idx = 0; idx < this.left.getSize(); idx++) {
            if (this.left.getContents().containsKey(idx)) { // Don't forget to skip empty slots!
                items.put(idx, this.left.getContents().get(idx));
            }
        }
        // And them the items from the right chest
        for (int idx = 0; idx < this.right.getSize(); idx++) {
            if (this.right.getContents().containsKey(idx)) { // Don't forget to skip empty slots!
                items.put(
                        idx + this.left.getSize(),
                        this.right
                                .getContents()
                                .get(idx)); // idx + this.left.getSize() so we don't overlap left chest items
            }
        }

        this.setContents(items);
    }

    @Override
    public Inventory getInventory() {
        return this;
    }

    @Override
    public BlockEntityChest getHolder() {
        return this.left.getHolder();
    }

    @NotNull @Override
    public Item getItem(int index) {
        return index < this.left.getSize()
                ? this.left.getItem(index)
                : this.right.getItem(index - this.right.getSize());
    }

    @PowerNukkitXOnly
    @Since("1.19.60-r1")
    @Override
    public Item getUnclonedItem(int index) {
        return index < this.left.getSize()
                ? this.left.getUnclonedItem(index)
                : this.right.getUnclonedItem(index - this.right.getSize());
    }

    @Override
    public boolean setItem(int index, Item item, boolean send) {
        return index < this.left.getSize()
                ? this.left.setItem(index, item, send)
                : this.right.setItem(index - this.right.getSize(), item, send);
    }

    @Override
    public boolean clear(int index, boolean send) {
        return index < this.left.getSize()
                ? this.left.clear(index, send)
                : this.right.clear(index - this.right.getSize(), send);
    }

    @Override
    public Map<Integer, Item> getContents() {
        Map<Integer, Item> contents = new HashMap<>();

        for (int i = 0; i < this.getSize(); ++i) {
            contents.put(i, this.getItem(i));
        }

        return contents;
    }

    @Override
    public void setContents(Map<Integer, Item> items) {
        if (items.size() > this.size) {
            Map<Integer, Item> newItems = new HashMap<>();
            for (int i = 0; i < this.size; i++) {
                newItems.put(i, items.get(i));
            }
            items = newItems;
        }

        for (int i = 0; i < this.size; i++) {
            if (!items.containsKey(i)) {
                if (i < this.left.size) {
                    if (this.left.slots.containsKey(i)) {
                        this.clear(i);
                    }
                } else if (this.right.slots.containsKey(i - this.left.size)) {
                    this.clear(i);
                }
            }
        }
    }

    @PowerNukkitDifference(info = "Using new method to play sounds", since = "1.4.0.0-PN")
    @Override
    public void onOpen(Player who) {
        super.onOpen(who);
        this.left.viewers.add(who);
        this.right.viewers.add(who);

        if (this.getViewers().size() == 1) {
            BlockEventPacket pk1 = new BlockEventPacket();
            pk1.x = (int) this.left.getHolder().x();
            pk1.y = (int) this.left.getHolder().y();
            pk1.z = (int) this.left.getHolder().z();
            pk1.case1 = 1;
            pk1.case2 = 2;
            Level level = this.left.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.left.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_CHESTOPEN);
                level.addChunkPacket(
                        (int) this.left.getHolder().x() >> 4,
                        (int) this.left.getHolder().z() >> 4,
                        pk1);
            }

            BlockEventPacket pk2 = new BlockEventPacket();
            pk2.x = (int) this.right.getHolder().x();
            pk2.y = (int) this.right.getHolder().y();
            pk2.z = (int) this.right.getHolder().z();
            pk2.case1 = 1;
            pk2.case2 = 2;

            level = this.right.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.right.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_CHESTOPEN);
                level.addChunkPacket(
                        (int) this.right.getHolder().x() >> 4,
                        (int) this.right.getHolder().z() >> 4,
                        pk2);
            }
        }
    }

    @PowerNukkitDifference(info = "Using new method to play sounds", since = "1.4.0.0-PN")
    @Override
    public void onClose(Player who) {
        if (this.getViewers().size() == 1) {
            BlockEventPacket pk1 = new BlockEventPacket();
            pk1.x = (int) this.right.getHolder().x();
            pk1.y = (int) this.right.getHolder().y();
            pk1.z = (int) this.right.getHolder().z();
            pk1.case1 = 1;
            pk1.case2 = 0;

            Level level = this.right.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.right.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_CHESTCLOSED);
                level.addChunkPacket(
                        (int) this.right.getHolder().x() >> 4,
                        (int) this.right.getHolder().z() >> 4,
                        pk1);
            }

            BlockEventPacket pk2 = new BlockEventPacket();
            pk2.x = (int) this.left.getHolder().x();
            pk2.y = (int) this.left.getHolder().y();
            pk2.z = (int) this.left.getHolder().z();
            pk2.case1 = 1;
            pk2.case2 = 0;

            level = this.left.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.left.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_CHESTCLOSED);
                level.addChunkPacket(
                        (int) this.left.getHolder().x() >> 4,
                        (int) this.left.getHolder().z() >> 4,
                        pk2);
            }
        }

        this.left.viewers.remove(who);
        this.right.viewers.remove(who);
        super.onClose(who);
    }

    public ChestInventory getLeftSide() {
        return this.left;
    }

    public ChestInventory getRightSide() {
        return this.right;
    }

    public void sendSlot(Inventory inv, int index, Player... players) {
        InventorySlotPacket pk = new InventorySlotPacket();
        pk.slot = inv == this.right ? this.left.getSize() + index : index;
        pk.item = inv.getUnclonedItem(index);

        for (Player player : players) {
            int id = player.getWindowId(this);
            if (id == -1) {
                this.close(player);
                continue;
            }
            pk.inventoryId = id;
            player.sendPacket(pk);
        }
    }
}
