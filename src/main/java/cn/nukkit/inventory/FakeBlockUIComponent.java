package cn.nukkit.inventory;

import cn.nukkit.event.inventory.InventoryCloseEvent;
import cn.nukkit.event.inventory.InventoryOpenEvent;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.ContainerClosePacket;
import cn.nukkit.network.protocol.ContainerOpenPacket;
import cn.nukkit.player.Player;

/**
 * 一个抽象的方块实体UI，提供了一些inventory方法的默认实现
 */
public class FakeBlockUIComponent extends PlayerUIComponent {
    private final InventoryType type;

    FakeBlockUIComponent(PlayerUIInventory playerUI, InventoryType type, int offset, Position position) {
        super(playerUI, offset, type.getDefaultSize());
        this.type = type;
        this.holder = new FakeBlockMenu(this, position);
    }

    @Override
    public FakeBlockMenu getHolder() {
        return (FakeBlockMenu) this.holder;
    }

    @Override
    public InventoryType getType() {
        return type;
    }

    @Override
    public boolean open(Player who) {
        InventoryOpenEvent event = new InventoryOpenEvent(this, who);
        event.call();
        if (event.isCancelled()) {
            return false;
        }
        this.onOpen(who);

        return true;
    }

    @Override
    public void close(Player who) {
        InventoryCloseEvent event = new InventoryCloseEvent(this, who);
        event.call();

        this.onClose(who);
    }

    @Override
    public void onOpen(Player who) {
        super.onOpen(who);
        ContainerOpenPacket pk = new ContainerOpenPacket();
        pk.windowId = who.getWindowId(this);
        pk.type = type.getNetworkType();
        InventoryHolder holder = this.getHolder();
        if (holder != null) {
            pk.x = (int) ((Vector3) holder).x();
            pk.y = (int) ((Vector3) holder).y();
            pk.z = (int) ((Vector3) holder).z();
        } else {
            pk.x = pk.y = pk.z = 0;
        }

        who.sendPacket(pk);

        this.sendContents(who);
    }

    @Override
    public void onClose(Player who) {
        ContainerClosePacket pk = new ContainerClosePacket();
        pk.windowId = who.getWindowId(this);
        pk.wasServerInitiated = who.getClosingWindowId() != pk.windowId;
        who.sendPacket(pk);
        super.onClose(who);
    }

    @Override
    public void sendContents(Player... players) {
        for (int slot = 0; slot < getSize(); slot++) {
            sendSlot(slot, players);
        }
    }
}
