package cn.nukkit.block.impl;

import cn.nukkit.block.BlockStairs;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;

/**
 * @author xtypr
 * @since 2015/11/25
 */
public class BlockStairsWood extends BlockStairs {
    public BlockStairsWood() {
        this(0);
    }

    public BlockStairsWood(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OAK_STAIRS;
    }

    @Override
    public String getName() {
        return "Wood Stairs";
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 3;
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 20;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {toItem()};
    }
}
