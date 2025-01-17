package cn.nukkit.network.process.processor;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityItemFrame;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ItemFrameDropItemPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class ItemFrameDropItemProcessor extends DataPacketProcessor<ItemFrameDropItemPacket> {

    // PowerNukkit Note: This packed is not being sent anymore since 1.16.210
    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull ItemFrameDropItemPacket pk) {
        Player player = playerHandle.getPlayer();
        Vector3 vector3 = player.temporalVector.setComponents(pk.x, pk.y, pk.z);
        if (vector3.distanceSquared(player) < 1000) {
            BlockEntity itemFrame = player.getLevel().getBlockEntity(vector3);
            if (itemFrame instanceof BlockEntityItemFrame) {
                ((BlockEntityItemFrame) itemFrame).dropItem(player);
            }
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.ITEM_FRAME_DROP_ITEM_PACKET);
    }
}
