package cn.nukkit.network.process.processor;

import cn.nukkit.event.inventory.PlayerTypingAnvilInventoryEvent;
import cn.nukkit.inventory.AnvilInventory;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.FilterTextPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class FilterTextProcessor extends DataPacketProcessor<FilterTextPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull FilterTextPacket pk) {
        Player player = playerHandle.getPlayer();
        if (pk.text == null || pk.text.length() > 64) {
            log.debug(playerHandle.getUsername() + ": FilterTextPacket with too long text");
            return;
        }
        FilterTextPacket textResponsePacket = new FilterTextPacket();

        if (player.craftingType == Player.CRAFTING_ANVIL) {
            AnvilInventory anvilInventory = (AnvilInventory) player.getWindowById(Player.ANVIL_WINDOW_ID);
            if (anvilInventory != null) {
                PlayerTypingAnvilInventoryEvent playerTypingAnvilInventoryEvent = new PlayerTypingAnvilInventoryEvent(
                        player, anvilInventory, anvilInventory.getNewItemName(), pk.getText());
                playerTypingAnvilInventoryEvent.call();
                anvilInventory.setNewItemName(playerTypingAnvilInventoryEvent.getTypedName());
            }
        }

        textResponsePacket.text = pk.text;
        textResponsePacket.fromServer = true;
        player.sendPacket(textResponsePacket);
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.FILTER_TEXT_PACKET);
    }
}
