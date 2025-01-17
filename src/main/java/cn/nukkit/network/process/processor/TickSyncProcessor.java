package cn.nukkit.network.process.processor;

import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.protocol.TickSyncPacket;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class TickSyncProcessor extends DataPacketProcessor<TickSyncPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull TickSyncPacket pk) {
        Player player = playerHandle.getPlayer();
        TickSyncPacket tickSyncPacketToClient = new TickSyncPacket();
        tickSyncPacketToClient.setRequestTimestamp(pk.getRequestTimestamp());
        tickSyncPacketToClient.setResponseTimestamp(player.getServer().getTick());
        player.sendPacketImmediately(tickSyncPacketToClient);
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.TICK_SYNC_PACKET);
    }
}
