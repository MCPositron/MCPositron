package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.level.generator.object.ObjectNyliumVegetation;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.player.Player;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public abstract class BlockNylium extends BlockSolid {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockNylium() {
        // Does nothing
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_RANDOM && !up().isTransparent()) {
            getLevel().setBlock(this, Block.get(NETHERRACK), false);
            return type;
        }
        return 0;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, @Nullable Player player) {
        Block up = up();
        if (item.isNull() || !item.isFertilizer() || up.getId() != AIR) {
            return false;
        }

        if (player != null && !player.isCreative()) {
            item.count--;
        }

        grow();

        getLevel().addParticle(new BoneMealParticle(up));

        return true;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public boolean grow() {
        ObjectNyliumVegetation.growVegetation(getLevel(), this, new NukkitRandom());
        return true;
    }

    @Override
    public double getResistance() {
        return 0.4;
    }

    @Override
    public double getHardness() {
        return 0.4;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= ItemTool.TIER_WOODEN) {
            return new Item[] {Item.get(NETHERRACK)};
        }
        return Item.EMPTY_ARRAY;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
