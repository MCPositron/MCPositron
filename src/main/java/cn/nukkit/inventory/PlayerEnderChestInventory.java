package cn.nukkit.inventory;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.block.impl.BlockEnderChest;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.EntityHumanType;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.BlockEventPacket;
import cn.nukkit.network.protocol.ContainerClosePacket;
import cn.nukkit.network.protocol.ContainerOpenPacket;
import cn.nukkit.player.Player;

public class PlayerEnderChestInventory extends BaseInventory {

    public PlayerEnderChestInventory(EntityHumanType player) {
        super(player, InventoryType.ENDER_CHEST);
    }

    @Override
    public EntityHuman getHolder() {
        return (EntityHuman) this.holder;
    }

    @PowerNukkitDifference(info = "Using new method to play sounds", since = "1.4.0.0-PN")
    @Override
    public void onOpen(Player who) {
        if (who != this.getHolder()) {
            return;
        }
        super.onOpen(who);
        ContainerOpenPacket containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.windowId = who.getWindowId(this);
        containerOpenPacket.type = this.getType().getNetworkType();
        BlockEnderChest chest = who.getViewingEnderChest();
        if (chest != null) {
            containerOpenPacket.x = (int) chest.x();
            containerOpenPacket.y = (int) chest.y();
            containerOpenPacket.z = (int) chest.z();
        } else {
            containerOpenPacket.x = containerOpenPacket.y = containerOpenPacket.z = 0;
        }

        who.sendPacket(containerOpenPacket);

        this.sendContents(who);

        if (chest != null && chest.getViewers().size() == 1) {
            BlockEventPacket blockEventPacket = new BlockEventPacket();
            blockEventPacket.x = (int) chest.x();
            blockEventPacket.y = (int) chest.y();
            blockEventPacket.z = (int) chest.z();
            blockEventPacket.case1 = 1;
            blockEventPacket.case2 = 2;

            Level level = this.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_ENDERCHESTOPEN);
                level.addChunkPacket(
                        (int) this.getHolder().x() >> 4, (int) this.getHolder().z() >> 4, blockEventPacket);
            }
        }
    }

    @PowerNukkitDifference(info = "Using new method to play sounds", since = "1.4.0.0-PN")
    @Override
    public void onClose(Player who) {
        ContainerClosePacket containerClosePacket = new ContainerClosePacket();
        containerClosePacket.windowId = who.getWindowId(this);
        containerClosePacket.wasServerInitiated = who.getClosingWindowId() != containerClosePacket.windowId;
        who.sendPacket(containerClosePacket);
        super.onClose(who);

        BlockEnderChest chest = who.getViewingEnderChest();
        if (chest != null && chest.getViewers().size() == 1) {
            BlockEventPacket blockEventPacket = new BlockEventPacket();
            blockEventPacket.x = (int) chest.x();
            blockEventPacket.y = (int) chest.y();
            blockEventPacket.z = (int) chest.z();
            blockEventPacket.case1 = 1;
            blockEventPacket.case2 = 0;

            Level level = this.getHolder().getLevel();
            if (level != null) {
                level.addSound(this.getHolder().add(0.5, 0.5, 0.5), Sound.RANDOM_ENDERCHESTCLOSED);
                level.addChunkPacket(
                        (int) this.getHolder().x() >> 4, (int) this.getHolder().z() >> 4, blockEventPacket);
            }

            who.setViewingEnderChest(null);
        }

        super.onClose(who);
    }
}
