package cn.nukkit.block.impl;

import cn.nukkit.block.BlockDoor;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemDoorWood;
import cn.nukkit.item.ItemTool;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class BlockDoorWood extends BlockDoor {

    public BlockDoorWood() {
        this(0);
    }

    public BlockDoorWood(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Wood Door Block";
    }

    @Override
    public int getId() {
        return OAK_DOOR_BLOCK;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public Item toItem() {
        return new ItemDoorWood();
    }
}
