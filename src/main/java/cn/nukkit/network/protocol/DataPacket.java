package cn.nukkit.network.protocol;

import cn.nukkit.Server;
import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.network.Network;
import cn.nukkit.utils.Binary;
import cn.nukkit.utils.BinaryStream;
import com.nukkitx.network.raknet.RakNetReliability;
import javax.annotation.Nonnegative;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public abstract class DataPacket extends BinaryStream implements Cloneable {
    @PowerNukkitOnly
    public static final DataPacket[] EMPTY_ARRAY = new DataPacket[0];

    public volatile boolean isEncoded = false;
    private int channel = 0;

    public RakNetReliability reliability = RakNetReliability.RELIABLE_ORDERED;

    @Deprecated(since = "1.19.70")
    @DeprecationDetails(
            since = "1.19.70-r1",
            reason = "pid could be more than 255, so it should be an int",
            replaceWith = "packetId()")
    public abstract byte pid();

    /**
     * @return The id of the packet
     */
    @Since("1.19.70-r1")
    @PowerNukkitXOnly
    public @Nonnegative int packetId() {
        return ProtocolInfo.toNewProtocolID(this.pid());
    }

    /**
     * @return The protocol version of the packet. If it is lower than CURRENT_PROTOCOL, pnx will try to translate it.
     */
    public int getProtocolUsed() {
        return ProtocolInfo.CURRENT_PROTOCOL;
    }

    public abstract void decode();

    public abstract void encode();

    public final void tryEncode() {
        if (!this.isEncoded) {
            this.isEncoded = true;
            this.encode();
        }
    }

    @Override
    public DataPacket reset() {
        super.reset();
        this.putUnsignedVarInt(this.packetId());
        return this;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getChannel() {
        return channel;
    }

    public DataPacket clean() {
        this.setBuffer(null);
        this.setOffset(0);
        this.isEncoded = false;
        return this;
    }

    @Override
    public DataPacket clone() {
        try {
            DataPacket packet = (DataPacket) super.clone();
            packet.setBuffer(this.getBuffer()); // prevent reflecting same buffer instance
            packet.offset = this.offset;
            packet.count = this.count;
            return packet;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public BatchPacket compress() {
        return compress(Server.getInstance().networkCompressionLevel);
    }

    public BatchPacket compress(int level) {
        BatchPacket batch = new BatchPacket();
        byte[][] batchPayload = new byte[2][];
        byte[] buf = getBuffer();
        batchPayload[0] = Binary.writeUnsignedVarInt(buf.length);
        batchPayload[1] = buf;
        try {
            batch.payload = Network.deflateRaw(batchPayload, level);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return batch;
    }
}
