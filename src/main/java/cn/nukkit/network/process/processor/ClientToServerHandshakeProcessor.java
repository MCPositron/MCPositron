package cn.nukkit.network.process.processor;

import cn.nukkit.Server;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ClientToServerHandshakePacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

/**
 * @author LT_Name
 */
public class ClientToServerHandshakeProcessor extends DataPacketProcessor<ClientToServerHandshakePacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull ClientToServerHandshakePacket pk) {
        if (Server.getInstance().enabledNetworkEncryption
                && playerHandle.getPlayerInfo().isXboxAuthed()) {
            playerHandle.handleLogin();
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.CLIENT_TO_SERVER_HANDSHAKE_PACKET);
    }
}
