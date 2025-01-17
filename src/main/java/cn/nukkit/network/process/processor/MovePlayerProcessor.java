package cn.nukkit.network.process.processor;

import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.MovePlayerPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class MovePlayerProcessor extends DataPacketProcessor<MovePlayerPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull MovePlayerPacket pk) {
        Player player = playerHandle.getPlayer();
        if (!player.isLocallyInitialized() || Server.getInstance().getServerAuthoritativeMovement() > 0) {
            return;
        }
        Vector3 newPos = new Vector3(pk.x, pk.y - playerHandle.getBaseOffset(), pk.z);

        pk.yaw %= 360;
        pk.headYaw %= 360;
        pk.pitch %= 360;
        if (pk.yaw < 0) {
            pk.yaw += 360;
        }
        if (pk.headYaw < 0) {
            pk.headYaw += 360;
        }
        playerHandle.offerMovementTask(Location.fromObject(newPos, player.getLevel(), pk.yaw, pk.pitch, pk.headYaw));
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.MOVE_PLAYER_PACKET);
    }
}
