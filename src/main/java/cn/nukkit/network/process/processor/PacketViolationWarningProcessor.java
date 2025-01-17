package cn.nukkit.network.process.processor;

import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.PacketViolationWarningPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.PlayerHandle;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class PacketViolationWarningProcessor extends DataPacketProcessor<PacketViolationWarningPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull PacketViolationWarningPacket pk) {
        Optional<String> packetName = Arrays.stream(ProtocolInfo.class.getDeclaredFields())
                .filter(field -> field.getType() == Byte.TYPE)
                .filter(field -> {
                    try {
                        return field.getByte(null) == pk.packetId;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .map(Field::getName)
                .findFirst();
        log.warn(
                "Violation warning from {}{}",
                playerHandle.getPlayer().getName(),
                packetName.map(name -> " for packet " + name).orElse("") + ": " + pk);
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.PACKET_VIOLATION_WARNING_PACKET);
    }
}
