package cn.nukkit.network.process.processor;

import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.event.player.PlayerBlockPickEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.BlockPickRequestPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class BlockPickRequestProcessor extends DataPacketProcessor<BlockPickRequestPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull BlockPickRequestPacket pk) {
        Player player = playerHandle.getPlayer();
        Block block = player.getLevel().getBlock(pk.x, pk.y, pk.z, false);
        if (block.distanceSquared(player) > 1000) {
            log.debug(playerHandle.getUsername() + ": Block pick request for a block too far away");
            return;
        }
        Item item = block.toItem();

        if (pk.addUserData) {
            BlockEntity blockEntity = player.getLevel().getBlockEntity(new Vector3(pk.x, pk.y, pk.z));
            if (blockEntity != null) {
                CompoundTag nbt = blockEntity.getCleanedNBT();
                if (nbt != null) {
                    item.setCustomBlockData(nbt);
                    item.setLore("+(DATA)");
                }
            }
        }

        PlayerBlockPickEvent pickEvent = new PlayerBlockPickEvent(player, block, item);
        if (player.isSpectator()) {
            log.debug("Got block-pick request from {} when in spectator mode", player.getName());
            pickEvent.cancel();
        }

        pickEvent.call();

        if (!pickEvent.isCancelled()) {
            boolean itemExists = false;
            int itemSlot = -1;
            for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
                if (player.getInventory().getItem(slot).equals(pickEvent.getItem())) {
                    if (slot < player.getInventory().getHotbarSize()) {
                        player.getInventory().setHeldItemSlot(slot);
                    } else {
                        itemSlot = slot;
                    }
                    itemExists = true;
                    break;
                }
            }

            for (int slot = 0; slot < player.getInventory().getHotbarSize(); slot++) {
                if (player.getInventory().getItem(slot).isNull()) {
                    if (!itemExists && player.isCreative()) {
                        player.getInventory().setHeldItemSlot(slot);
                        player.getInventory().setItemInHand(pickEvent.getItem());
                        return;
                    } else if (itemSlot > -1) {
                        player.getInventory().setHeldItemSlot(slot);
                        player.getInventory()
                                .setItemInHand(player.getInventory().getItem(itemSlot));
                        player.getInventory().clear(itemSlot, true);
                        return;
                    }
                }
            }

            if (!itemExists && player.isCreative()) {
                Item itemInHand = player.getInventory().getItemInHand();
                player.getInventory().setItemInHand(pickEvent.getItem());
                if (!player.getInventory().isFull()) {
                    for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
                        if (player.getInventory().getItem(slot).isNull()) {
                            player.getInventory().setItem(slot, itemInHand);
                            break;
                        }
                    }
                }
            } else if (itemSlot > -1) {
                Item itemInHand = player.getInventory().getItemInHand();
                player.getInventory().setItemInHand(player.getInventory().getItem(itemSlot));
                player.getInventory().setItem(itemSlot, itemInHand);
            }
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.BLOCK_PICK_REQUEST_PACKET);
    }
}
