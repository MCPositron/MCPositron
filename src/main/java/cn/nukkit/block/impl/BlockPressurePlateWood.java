package cn.nukkit.block.impl;

import cn.nukkit.block.BlockPressurePlateBase;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.AxisAlignedBB;

/**
 * @author Nukkit Project Team
 */
public class BlockPressurePlateWood extends BlockPressurePlateBase {

    public BlockPressurePlateWood(int meta) {
        super(meta);
        this.onPitch = 0.8f;
        this.offPitch = 0.7f;
    }

    public BlockPressurePlateWood() {
        this(0);
    }

    @Override
    public String getName() {
        return "Oak Pressure Plate";
    }

    @Override
    public int getId() {
        return WOODEN_PRESSURE_PLATE;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public double getHardness() {
        return 0.5D;
    }

    @Override
    public double getResistance() {
        return 0.5D;
    }

    @Override
    protected int computeRedstoneStrength() {
        AxisAlignedBB bb = getCollisionBoundingBox();

        for (Entity entity : this.getLevel().getCollidingEntities(bb)) {
            if (entity.doesTriggerPressurePlate()) {
                return 15;
            }
        }

        return 0;
    }
}
