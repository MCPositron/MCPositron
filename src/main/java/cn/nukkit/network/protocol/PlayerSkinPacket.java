package cn.nukkit.network.protocol;

import cn.nukkit.Server;
import cn.nukkit.entity.data.Skin;
import java.util.UUID;
import lombok.ToString;

@ToString
public class PlayerSkinPacket extends DataPacket {

    public UUID uuid;
    public Skin skin;
    public String newSkinName;
    public String oldSkinName;

    @Override
    public byte pid() {
        return ProtocolInfo.PLAYER_SKIN_PACKET;
    }

    @Override
    public void decode() {
        uuid = getUUID();
        skin = getSkin();
        newSkinName = getString();
        oldSkinName = getString();
        if (!feof()) { // -facepalm-
            skin.setTrusted(getBoolean());
        }
    }

    @Override
    public void encode() {
        reset();
        putUUID(uuid);
        putSkin(skin);
        putString(newSkinName);
        putString(oldSkinName);
        putBoolean(skin.isTrusted() || Server.getInstance().isForceSkinTrusted());
    }
}
