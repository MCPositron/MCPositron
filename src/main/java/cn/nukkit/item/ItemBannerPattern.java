package cn.nukkit.item;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.utils.BannerPattern;

@Since("1.2.1.0-PN")
public class ItemBannerPattern extends Item {
    @PowerNukkitOnly
    public static final int PATTERN_CREEPER_CHARGE = 0;

    @PowerNukkitOnly
    public static final int PATTERN_SKULL_CHARGE = 1;

    @PowerNukkitOnly
    public static final int PATTERN_FLOWER_CHARGE = 2;

    @PowerNukkitOnly
    public static final int PATTERN_THING = 3;

    @PowerNukkitOnly
    public static final int PATTERN_FIELD_MASONED = 4;

    @PowerNukkitOnly
    public static final int PATTERN_BORDURE_INDENTED = 5;

    @PowerNukkitOnly
    @Since("FUTURE")
    public static final int PATTERN_SNOUT = 6;

    @PowerNukkitXOnly
    @Since("1.20.0-r2")
    public static final int PATTERN_GLOBE = 7;

    public ItemBannerPattern() {
        this(0, 1);
    }

    public ItemBannerPattern(Integer meta) {
        this(meta, 1);
    }

    public ItemBannerPattern(Integer meta, int count) {
        super(BANNER_PATTERN, meta, count, "Bone");
        updateName();
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    protected ItemBannerPattern(int id, Integer meta, int count, String name) {
        super(id, meta, count, name);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void setDamage(Integer meta) {
        super.setDamage(meta);
        updateName();
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BannerPattern.Type getPatternType() {
        if (getId() != BANNER_PATTERN) {
            return BannerPattern.Type.PATTERN_CREEPER;
        }
        return switch (getDamage()) {
            default -> BannerPattern.Type.PATTERN_CREEPER;
            case PATTERN_SKULL_CHARGE -> BannerPattern.Type.PATTERN_SKULL;
            case PATTERN_FLOWER_CHARGE -> BannerPattern.Type.PATTERN_FLOWER;
            case PATTERN_THING -> BannerPattern.Type.PATTERN_MOJANG;
            case PATTERN_FIELD_MASONED -> BannerPattern.Type.PATTERN_BRICK;
            case PATTERN_BORDURE_INDENTED -> BannerPattern.Type.PATTERN_CURLY_BORDER;
            case PATTERN_SNOUT -> BannerPattern.Type.PATTERN_SNOUT;
            case PATTERN_GLOBE -> BannerPattern.Type.PATTERN_GLOBE;
        };
    }

    @PowerNukkitOnly
    protected void updateName() {
        if (getId() != BANNER_PATTERN) {
            return;
        }
        switch (super.meta) {
            case PATTERN_CREEPER_CHARGE -> name = "Creeper Charge Banner Pattern";
            case PATTERN_SKULL_CHARGE -> name = "Skull Charge Banner Pattern";
            case PATTERN_FLOWER_CHARGE -> name = "Flower Charge Banner Pattern";
            case PATTERN_THING -> name = "Thing Banner Pattern";
            case PATTERN_FIELD_MASONED -> name = "Field Banner Pattern";
            case PATTERN_BORDURE_INDENTED -> name = "Bordure Indented Banner Pattern";
            case PATTERN_SNOUT -> name = "Snout Banner Pattern";
            case PATTERN_GLOBE -> name = "Globe Banner Pattern";
            default -> name = "Banner Pattern";
        }
    }
}
