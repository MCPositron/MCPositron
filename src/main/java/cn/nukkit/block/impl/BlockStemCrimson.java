package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockStem;
import cn.nukkit.block.state.BlockState;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockStemCrimson extends BlockStem {

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStemCrimson() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStemCrimson(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CRIMSON_STEM;
    }

    @Override
    public String getName() {
        return "Crimson Stem";
    }

    @PowerNukkitOnly
    @Override
    public BlockState getStrippedState() {
        return getCurrentState().withBlockId(STRIPPED_CRIMSON_STEM);
    }
}
