package cn.nukkit.network.protocol;

import cn.nukkit.api.Since;
import java.util.EnumSet;
import java.util.Set;
import lombok.ToString;

@Since("1.3.0.0-PN")
@ToString
public class PlayerArmorDamagePacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.PLAYER_ARMOR_DAMAGE_PACKET;

    @Since("1.3.0.0-PN")
    public final Set<PlayerArmorDamageFlag> flags = EnumSet.noneOf(PlayerArmorDamageFlag.class);

    @Since("1.3.0.0-PN")
    public final int[] damage = new int[4];

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        int flagsval = this.getByte();
        for (int i = 0; i < 4; i++) {
            if ((flagsval & (1 << i)) != 0) {
                this.flags.add(PlayerArmorDamageFlag.values()[i]);
                this.damage[i] = this.getVarInt();
            }
        }
    }

    @Override
    public void encode() {
        this.reset();
        int outflags = 0;
        for (PlayerArmorDamageFlag flag : this.flags) {
            outflags |= 1 << flag.ordinal();
        }
        this.putByte((byte) outflags);

        for (PlayerArmorDamageFlag flag : this.flags) {
            this.putVarInt(this.damage[flag.ordinal()]);
        }
    }

    @Since("1.3.0.0-PN")
    public enum PlayerArmorDamageFlag {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}
