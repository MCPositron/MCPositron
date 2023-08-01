package cn.nukkit.network.process.processor;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySpawnable;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.BlockEntityDataPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import java.io.IOException;
import java.nio.ByteOrder;
import org.jetbrains.annotations.NotNull;

public class BlockEntityDataProcessor extends DataPacketProcessor<BlockEntityDataPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull BlockEntityDataPacket pk) {
        Player player = playerHandle.getPlayer();
        if (!player.isSpawned() || !player.isAlive()) {
            return;
        }

        player.craftingType = Player.CRAFTING_SMALL;
        player.resetCraftingGridType();

        Vector3 pos = new Vector3(pk.x, pk.y, pk.z);
        if (pos.distanceSquared(player) > 10000) {
            return;
        }

        BlockEntity t = player.getLevel().getBlockEntity(pos);
        if (t instanceof BlockEntitySpawnable) {
            CompoundTag nbt;
            try {
                nbt = NBTIO.read(pk.namedTag, ByteOrder.LITTLE_ENDIAN, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (!((BlockEntitySpawnable) t).updateCompoundTag(nbt, player)) {
                ((BlockEntitySpawnable) t).spawnTo(player);
            }
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.BLOCK_ENTITY_DATA_PACKET);
    }
}
