package cn.nukkit.network.protocol.types;

import cn.nukkit.block.property.value.StructureBlockType;
import lombok.Value;

@Value
public class StructureEditorData {
    String name;
    String dataField;
    boolean includingPlayers;
    boolean boundingBoxVisible;
    StructureBlockType type;
    StructureSettings settings;
    StructureRedstoneSaveMode redstoneSaveMode;
}
