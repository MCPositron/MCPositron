package cn.nukkit.block.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockEntityHolder;
import cn.nukkit.block.property.BlockProperties;
import cn.nukkit.block.property.CommonBlockProperties;
import cn.nukkit.block.property.IntBlockProperty;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityChiseledBookshelf;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBook;
import cn.nukkit.item.ItemBookEnchanted;
import cn.nukkit.item.ItemBookWritable;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector2;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.player.Player;
import cn.nukkit.utils.Faceable;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockChiseledBookshelf extends BlockBookshelf
        implements BlockEntityHolder<BlockEntityChiseledBookshelf>, Faceable {
    public static final IntBlockProperty BOOKS_STORED = new IntBlockProperty("books_stored", false, 63);
    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.DIRECTION, BOOKS_STORED);

    public BlockChiseledBookshelf(int meta) {
        super(meta);
    }

    public BlockChiseledBookshelf() {
        this(0);
    }

    @NotNull public BlockProperties getProperties() {
        return PROPERTIES;
    }

    public int getId() {
        return CHISELED_BOOKSHELF;
    }

    public String getName() {
        return "Chiseled Bookshelf";
    }

    @Override
    public Item[] getDrops(Item item) {
        BlockEntityChiseledBookshelf blockEntity = this.getBlockEntity();
        if (blockEntity != null) {
            return blockEntity.getItems();
        }
        return Item.EMPTY_ARRAY;
    }

    @Override
    public boolean place(
            @NotNull Item item,
            @NotNull Block block,
            @NotNull Block target,
            @NotNull BlockFace face,
            double fx,
            double fy,
            double fz,
            @Nullable Player player) {
        if (player != null) {
            setBlockFace(player.getHorizontalFacing().getOpposite());
        } else {
            setBlockFace(BlockFace.SOUTH);
        }
        CompoundTag nbt = new CompoundTag();
        if (item.hasCustomName()) {
            nbt.putString("CustomName", item.getCustomName());
        }
        if (item.hasCustomBlockData()) {
            Map<String, Tag> customData = item.getCustomBlockData().getTags();
            for (Map.Entry<String, Tag> tag : customData.entrySet()) {
                nbt.put(tag.getKey(), tag.getValue());
            }
        }
        return BlockEntityHolder.setBlockAndCreateEntity(this, true, true, nbt) != null;
    }

    @NotNull @Override
    public Class<? extends BlockEntityChiseledBookshelf> getBlockEntityClass() {
        return BlockEntityChiseledBookshelf.class;
    }

    @NotNull @Override
    public String getBlockEntityType() {
        return BlockEntity.CHISELED_BOOKSHELF;
    }

    @Since("1.20.0-r2")
    @Override
    public void onPlayerRightClick(@NotNull Player player, Item item, BlockFace face, Vector3 clickPoint) {
        BlockFace blockFace = getBlockFace();
        if (player.getHorizontalFacing().getOpposite() == blockFace) {
            /*
             * south z==1  The lower left corner is the origin
             * east  x==1  The lower right corner is the origin
             * west  x==0  The lower left corner is the origin
             * north z==0  The lower right corner is the origin
             */
            Vector2 clickPos =
                    switch (blockFace) {
                        case NORTH -> new Vector2(1 - clickPoint.x(), clickPoint.y());
                        case SOUTH -> new Vector2(clickPoint.x(), clickPoint.y());
                        case WEST -> new Vector2(clickPoint.z(), clickPoint.y());
                        case EAST -> new Vector2(1 - clickPoint.z(), clickPoint.y());
                        default -> throw new IllegalArgumentException(blockFace.toString());
                    };
            int index = getRegion(clickPos);
            BlockEntityChiseledBookshelf blockEntity = this.getBlockEntity();
            if (blockEntity != null) {
                if (blockEntity.hasBook(index)) {
                    Item book = blockEntity.removeBook(index);
                    player.getInventory().addItem(book);
                } else if (item instanceof ItemBook
                        || item instanceof ItemBookEnchanted
                        || item instanceof ItemBookWritable) {
                    Item itemClone = item.clone();
                    if (!player.isCreative()) {
                        itemClone.setCount(itemClone.getCount() - 1);
                        player.getInventory().setItemInHand(itemClone);
                    }
                    itemClone.setCount(1);
                    blockEntity.setBook(itemClone, index);
                }
                this.setPropertyValue(BOOKS_STORED, blockEntity.getBooksStoredBit());
                this.getLevel().setBlock(this, this, true);
            }
        }
    }

    @Override
    public BlockFace getBlockFace() {
        return getPropertyValue(CommonBlockProperties.DIRECTION);
    }

    @Override
    public void setBlockFace(BlockFace face) {
        setPropertyValue(CommonBlockProperties.DIRECTION, face);
    }

    private int getRegion(Vector2 clickPos) {
        if (clickPos.x() - 0.333333 < 0) {
            return clickPos.y() - 0.5 < 0 ? 3 : 0;
        } else if (clickPos.x() - 0.666666 < 0) {
            return clickPos.y() - 0.5 < 0 ? 4 : 1;
        } else {
            return clickPos.y() - 0.5 < 0 ? 5 : 2;
        }
    }
}
