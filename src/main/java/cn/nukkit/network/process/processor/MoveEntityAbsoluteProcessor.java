package cn.nukkit.network.process.processor;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityBoat;
import cn.nukkit.event.vehicle.VehicleMoveEvent;
import cn.nukkit.level.Location;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.MoveEntityAbsolutePacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class MoveEntityAbsoluteProcessor extends DataPacketProcessor<MoveEntityAbsolutePacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull MoveEntityAbsolutePacket pk) {
        Player player = playerHandle.getPlayer();
        if (!player.isAlive() || !player.isSpawned() || player.getRiding() == null) {
            return;
        }
        Entity movedEntity = player.getLevel().getEntity(pk.eid);
        if (!(movedEntity instanceof EntityBoat)) {
            return;
        }

        player.temporalVector.setComponents(pk.x, pk.y - ((EntityBoat) movedEntity).getBaseOffset(), pk.z);
        if (!movedEntity.equals(player.getRiding())
                || !movedEntity.isControlling(player)
                || player.temporalVector.distanceSquared(movedEntity) > 10 * 10) {
            movedEntity.addMovement(
                    movedEntity.x(),
                    movedEntity.y(),
                    movedEntity.z(),
                    movedEntity.yaw(),
                    movedEntity.pitch(),
                    movedEntity.yaw());
            return;
        }

        Location from = movedEntity.getLocation();
        movedEntity.setPositionAndRotation(player.temporalVector, pk.headYaw, 0);
        Location to = movedEntity.getLocation();
        if (!from.equals(to)) {
            new VehicleMoveEvent(player, from, to).call();
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.MOVE_ENTITY_ABSOLUTE_PACKET);
    }
}
