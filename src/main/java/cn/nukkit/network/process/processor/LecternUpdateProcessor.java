package cn.nukkit.network.process.processor;

import cn.nukkit.block.Block;
import cn.nukkit.block.impl.BlockLectern;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityLectern;
import cn.nukkit.event.block.LecternPageChangeEvent;
import cn.nukkit.math.BlockVector3;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.LecternUpdatePacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class LecternUpdateProcessor extends DataPacketProcessor<LecternUpdatePacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull LecternUpdatePacket pk) {
        Player player = playerHandle.getPlayer();
        BlockVector3 blockPosition = pk.blockPosition;
        player.temporalVector.setComponents(blockPosition.x, blockPosition.y, blockPosition.z);
        if (pk.dropBook) {
            Block blockLectern = player.getLevel().getBlock(player.temporalVector);
            if (blockLectern instanceof BlockLectern && blockLectern.distance(player) <= 6) {
                ((BlockLectern) blockLectern).dropBook(player);
            }
        } else {
            BlockEntity blockEntityLectern = player.getLevel().getBlockEntity(player.temporalVector);
            if (blockEntityLectern instanceof BlockEntityLectern lectern) {
                LecternPageChangeEvent lecternPageChangeEvent = new LecternPageChangeEvent(player, lectern, pk.page);
                lecternPageChangeEvent.call();
                if (!lecternPageChangeEvent.isCancelled()) {
                    lectern.setRawPage(lecternPageChangeEvent.getNewRawPage());
                    lectern.spawnToAll();
                    Block blockLectern = lectern.getBlock();
                    if (blockLectern instanceof BlockLectern) {
                        ((BlockLectern) blockLectern).executeRedstonePulse();
                    }
                }
            }
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.LECTERN_UPDATE_PACKET);
    }
}
