package cn.nukkit.network.session;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.network.CompressionProvider;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.player.Player;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

@Since("1.19.30-r1")
@PowerNukkitXOnly
public interface NetworkPlayerSession {
    void sendPacket(DataPacket packet);

    void sendImmediatePacket(DataPacket packet, Runnable callback);

    void sendImmediatePacket(DataPacket packet);

    void disconnect(String reason);

    Player getPlayer();

    void setCompression(CompressionProvider compression);

    CompressionProvider getCompression();

    default void setEncryption(SecretKey agreedKey, Cipher encryptionCipher, Cipher decryptionCipher) {}
}
