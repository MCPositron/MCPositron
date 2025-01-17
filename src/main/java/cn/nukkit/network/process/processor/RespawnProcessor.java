package cn.nukkit.network.process.processor;

import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.protocol.RespawnPacket;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class RespawnProcessor extends DataPacketProcessor<RespawnPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull RespawnPacket pk) {
        Player player = playerHandle.getPlayer();
        if (player.isAlive()) {
            return;
        }
        if (pk.respawnState == RespawnPacket.STATE_CLIENT_READY_TO_SPAWN) {
            RespawnPacket respawn1 = new RespawnPacket();
            respawn1.x = (float) player.getX();
            respawn1.y = (float) player.y();
            respawn1.z = (float) player.getZ();
            respawn1.respawnState = RespawnPacket.STATE_READY_TO_SPAWN;
            player.sendPacket(respawn1);
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.RESPAWN_PACKET);
    }
}
