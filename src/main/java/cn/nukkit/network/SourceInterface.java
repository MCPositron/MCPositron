package cn.nukkit.network;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.session.NetworkPlayerSession;
import cn.nukkit.player.Player;
import java.net.InetSocketAddress;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public interface SourceInterface {
    @Deprecated
    default Integer putPacket(Player player, DataPacket packet) {
        throw new UnsupportedOperationException("This method is deprecated");
    }

    @Deprecated
    default Integer putPacket(Player player, DataPacket packet, boolean needACK) {
        throw new UnsupportedOperationException("This method is deprecated");
    }

    @Deprecated
    default Integer putPacket(Player player, DataPacket packet, boolean needACK, boolean immediate) {
        throw new UnsupportedOperationException("This method is deprecated");
    }

    NetworkPlayerSession getSession(InetSocketAddress address);

    int getNetworkLatency(Player player);

    void close(Player player);

    void close(Player player, String reason);

    void setName(String name);

    boolean process();

    void shutdown();

    void emergencyShutdown();

    @PowerNukkitOnly
    @Since("1.5.2.0-PN")
    Integer putResourcePacket(Player player, DataPacket packet);
}
