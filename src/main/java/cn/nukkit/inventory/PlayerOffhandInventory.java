package cn.nukkit.inventory;

import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.EntityHumanType;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.InventoryContentPacket;
import cn.nukkit.network.protocol.InventorySlotPacket;
import cn.nukkit.network.protocol.MobEquipmentPacket;
import cn.nukkit.network.protocol.types.ContainerIds;
import cn.nukkit.player.Player;

public class PlayerOffhandInventory extends BaseInventory {

    public PlayerOffhandInventory(EntityHumanType holder) {
        super(holder, InventoryType.OFFHAND);
    }

    @Override
    public void setSize(int size) {
        throw new UnsupportedOperationException("Offhand can only carry one item at a time");
    }

    @Override
    public void onSlotChange(int index, Item before, boolean send) {
        EntityHuman holder = this.getHolder();
        if (holder instanceof Player player) {
            if (!player.isSpawned()) return;
            if (send) {
                this.sendContents(this.getViewers());
                this.sendContents(holder.getViewers().values());
                this.sendContents(player);
            }
        }
    }

    @Override
    public void sendContents(Player... players) {
        Item item = this.getItem(0);
        MobEquipmentPacket pk = this.createMobEquipmentPacket(item);

        for (Player player : players) {
            if (player == this.getHolder()) {
                InventoryContentPacket pk2 = new InventoryContentPacket();
                pk2.inventoryId = ContainerIds.OFFHAND;
                pk2.slots = new Item[] {item};
                player.sendPacket(pk2);
            } else {
                player.sendPacket(pk);
            }
        }
    }

    @Override
    public void sendSlot(int index, Player... players) {
        Item item = this.getItem(0);
        MobEquipmentPacket pk = this.createMobEquipmentPacket(item);

        for (Player player : players) {
            if (player == this.getHolder()) {
                InventorySlotPacket pk2 = new InventorySlotPacket();
                pk2.inventoryId = ContainerIds.OFFHAND;
                pk2.item = item;
                player.sendPacket(pk2);
            } else {
                player.sendPacket(pk);
            }
        }
    }

    private MobEquipmentPacket createMobEquipmentPacket(Item item) {
        MobEquipmentPacket pk = new MobEquipmentPacket();
        pk.eid = this.getHolder().getId();
        pk.item = item;
        pk.inventorySlot = 1;
        pk.windowId = ContainerIds.OFFHAND;
        pk.tryEncode();
        return pk;
    }

    @Override
    public EntityHuman getHolder() {
        return (EntityHuman) super.getHolder();
    }
}
