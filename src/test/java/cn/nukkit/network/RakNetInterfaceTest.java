package cn.nukkit.network;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import cn.nukkit.Server;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.ResourcePackChunkRequestPacket;
import cn.nukkit.player.Player;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powernukkit.tests.api.MockPlayer;
import org.powernukkit.tests.junit.jupiter.PowerNukkitExtension;

@ExtendWith(PowerNukkitExtension.class)
class RakNetInterfaceTest {

    RakNetInterface rakNetInterface;

    @MockPlayer
    Player player;

    @BeforeEach
    void setUp() throws IOException {
        Server server = Server.getInstance();
        ServerSocket socket = new ServerSocket(0);
        int port = socket.getLocalPort();
        socket.close();
        when(server.getIp()).thenReturn("127.0.0.1");
        when(server.getPort()).thenReturn(port);
        rakNetInterface = new RakNetInterface(server);
    }

    @Test
    void noSession() {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 3222);
        when(player.getPlayerConnection().getRawSocketAddress()).thenReturn(socketAddress);
        DataPacket packet = new ResourcePackChunkRequestPacket();
        rakNetInterface.putResourcePacket(player, packet);
        assertFalse(packet.isEncoded);
    }
}
