package cn.nukkit.block.impl;

import static cn.nukkit.potion.Effect.getEffect;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockSolid;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.SimpleAxisAlignedBB;
import cn.nukkit.math.Vector3;
import cn.nukkit.player.AdventureSettings;
import cn.nukkit.player.Player;
import cn.nukkit.potion.Effect;
import java.util.Random;

@PowerNukkitOnly
public class BlockHoney extends BlockSolid {
    private static final Random RANDOM = new Random();

    @PowerNukkitOnly
    public BlockHoney() {
        super();
    }

    @Override
    public String getName() {
        return "Honey Block";
    }

    @Override
    public int getId() {
        return HONEY_BLOCK;
    }

    @Override
    public double getHardness() {
        return 0;
    }

    @Override
    public double getResistance() {
        return 0;
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    @Override
    public void onEntityCollide(Entity entity) {
        if (!entity.onGround
                && entity.motionY <= 0.08
                && (!(entity instanceof Player)
                        || !((Player) entity).getAdventureSettings().get(AdventureSettings.Type.FLYING))) {
            double ex = Math.abs(x() + 0.5D - entity.x());
            double ez = Math.abs(z() + 0.5D - entity.z());
            double width = 0.4375D + (double) (entity.getWidth() / 2.0F);
            if (ex + 1.0E-3D > width || ez + 1.0E-3D > width) {
                Vector3 motion = entity.getMotion();
                motion.setY(-0.05);
                if (entity.motionY < -0.13) {
                    double m = -0.05 / entity.motionY;
                    motion.setX(motion.x() * m);
                    motion.setZ(motion.z() * m);
                }

                if (!entity.getMotion().equals(motion)) {
                    entity.setMotion(motion);
                }
                entity.resetFallDistance();

                if (RANDOM.nextInt(10) == 0) {
                    getLevel().addSound(entity, Sound.LAND_SLIME);
                }
            }
        }
    }

    @Override
    protected AxisAlignedBB recalculateCollisionBoundingBox() {
        return new SimpleAxisAlignedBB(x(), y(), z(), x() + 1, y() + 1, z() + 1);
    }

    @Override
    public double getMinX() {
        return x() + 0.1;
    }

    @Override
    public double getMaxX() {
        return x() + 0.9;
    }

    @Override
    public double getMinZ() {
        return z() + 0.1;
    }

    @Override
    public double getMaxZ() {
        return z() + 0.9;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getLightFilter() {
        return 1;
    }

    @Since("1.6.0.0-PNX")
    @PowerNukkitOnly
    @Override
    public boolean useDefaultFallDamage() {
        return false;
    }

    @Since("1.6.0.0-PNX")
    @PowerNukkitOnly
    @Override
    public void onEntityFallOn(Entity entity, float fallDistance) {
        int jumpBoost = entity.hasEffect(Effect.JUMP_BOOST)
                ? (getEffect(Effect.JUMP_BOOST).getAmplifier() + 1)
                : 0;
        float damage = (float) Math.floor(fallDistance - 3 - jumpBoost);

        damage *= 0.2F;

        if (damage > 0) {
            entity.attack(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.FALL, damage));
        }
    }

    @Since("1.19.60-r1")
    @Override
    public boolean canSticksBlock() {
        return true;
    }
}
