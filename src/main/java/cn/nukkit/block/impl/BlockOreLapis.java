package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockOre;
import cn.nukkit.item.Item;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.item.enchantment.Enchantment;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;

/**
 * @author MagicDroidX (Nukkit Project)
 */
@PowerNukkitDifference(since = "FUTURE", info = "Extends BlockOre instead of BlockSolid only in PowerNukkit")
public class BlockOreLapis extends BlockOre {

    public BlockOreLapis() {
        // Does nothing
    }

    @Override
    public int getId() {
        return LAPIS_ORE;
    }

    @Override
    public String getName() {
        return "Lapis Lazuli Ore";
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= getToolTier()) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int count = 4 + random.nextInt(5);
            Enchantment fortune = item.getEnchantment(Enchantment.ID_FORTUNE_DIGGING);
            if (fortune != null && fortune.getLevel() >= 1) {
                int i = random.nextInt(fortune.getLevel() + 2) - 1;

                if (i < 0) {
                    i = 0;
                }

                count *= (i + 1);
            }

            return new Item[] {MinecraftItemID.LAPIS_LAZULI.get(count)};
        } else {
            return Item.EMPTY_ARRAY;
        }
    }

    @Since("FUTURE")
    @PowerNukkitOnly
    @Nullable @Override
    protected MinecraftItemID getRawMaterial() {
        return MinecraftItemID.LAPIS_LAZULI;
    }

    @Override
    public int getDropExp() {
        return ThreadLocalRandom.current().nextInt(2, 6);
    }
}
