package cn.nukkit.event.inventory;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.blockentity.BlockEntityCampfire;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.block.BlockEvent;
import cn.nukkit.item.Item;

/**
 * @author MagicDroidX (Nukkit Project)
 */
@PowerNukkitOnly
public class CampfireSmeltEvent extends BlockEvent implements Cancellable {

    private final BlockEntityCampfire campfire;
    private final Item source;
    private Item result;
    private boolean keepItem;

    @PowerNukkitOnly
    public CampfireSmeltEvent(BlockEntityCampfire campfire, Item source, Item result) {
        super(campfire.getBlock());
        this.source = source.clone();
        this.source.setCount(1);
        this.result = result;
        this.campfire = campfire;
    }

    @PowerNukkitOnly
    public BlockEntityCampfire getCampfire() {
        return campfire;
    }

    @PowerNukkitOnly
    public Item getSource() {
        return source;
    }

    @PowerNukkitOnly
    public Item getResult() {
        return result;
    }

    @PowerNukkitOnly
    public void setResult(Item result) {
        this.result = result;
    }

    @PowerNukkitOnly
    public boolean getKeepItem() {
        return keepItem;
    }

    @PowerNukkitOnly
    public void setKeepItem(boolean keepItem) {
        this.keepItem = keepItem;
    }
}
