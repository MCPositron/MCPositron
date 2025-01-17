package cn.nukkit.network.process.processor;

import cn.nukkit.dialog.handler.FormDialogHandler;
import cn.nukkit.dialog.response.FormResponseDialog;
import cn.nukkit.dialog.window.FormWindowDialog;
import cn.nukkit.entity.passive.EntityNPCEntity;
import cn.nukkit.event.player.PlayerDialogRespondedEvent;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.NPCDialoguePacket;
import cn.nukkit.network.protocol.NPCRequestPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.player.Player;
import cn.nukkit.player.PlayerHandle;
import org.jetbrains.annotations.NotNull;

public class NPCRequestProcessor extends DataPacketProcessor<NPCRequestPacket> {

    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull NPCRequestPacket pk) {
        Player player = playerHandle.getPlayer();
        // 若sceneName字段为空，则为玩家在编辑NPC，我们并不需要记录对话框，直接通过entityRuntimeId获取实体即可
        if (pk.getSceneName().isEmpty()
                && player.getLevel().getEntity(pk.getRequestedEntityRuntimeId()) instanceof EntityNPCEntity npcEntity) {
            FormWindowDialog dialog = npcEntity.getDialog();

            FormResponseDialog response = new FormResponseDialog(pk, dialog);
            for (FormDialogHandler handler : dialog.getHandlers()) {
                handler.handle(player, response);
            }

            PlayerDialogRespondedEvent event = new PlayerDialogRespondedEvent(player, dialog, response);
            event.call();
            return;
        }
        if (playerHandle.getDialogWindows().getIfPresent(pk.getSceneName()) != null) {
            // remove the window from the map only if the requestType is EXECUTE_CLOSING_COMMANDS
            FormWindowDialog dialog;
            if (pk.getRequestType() == NPCRequestPacket.RequestType.EXECUTE_CLOSING_COMMANDS) {
                dialog = playerHandle.getDialogWindows().getIfPresent(pk.getSceneName());
                playerHandle.getDialogWindows().invalidate(pk.getSceneName());
            } else {
                dialog = playerHandle.getDialogWindows().getIfPresent(pk.getSceneName());
            }

            FormResponseDialog response = new FormResponseDialog(pk, dialog);
            for (FormDialogHandler handler : dialog.getHandlers()) {
                handler.handle(player, response);
            }

            PlayerDialogRespondedEvent event = new PlayerDialogRespondedEvent(player, dialog, response);
            event.call();

            // close dialog after clicked button (otherwise the client will not be able to close the window)
            if (response.getClickedButton() != null
                    && pk.getRequestType() == NPCRequestPacket.RequestType.EXECUTE_ACTION) {
                NPCDialoguePacket closeWindowPacket = new NPCDialoguePacket();
                closeWindowPacket.setRuntimeEntityId(pk.getRequestedEntityRuntimeId());
                closeWindowPacket.setSceneName(response.getSceneName());
                closeWindowPacket.setAction(NPCDialoguePacket.NPCDialogAction.CLOSE);
                player.sendPacket(closeWindowPacket);
            }
            if (response.getClickedButton() != null
                    && response.getRequestType() == NPCRequestPacket.RequestType.EXECUTE_ACTION
                    && response.getClickedButton().getNextDialog() != null) {
                response.getClickedButton().getNextDialog().send(player);
            }
        }
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.NPC_REQUEST_PACKET);
    }
}
