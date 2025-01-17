package cn.nukkit.command.selector.args.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.exceptions.SelectorSyntaxException;
import cn.nukkit.command.selector.ParseUtils;
import cn.nukkit.command.selector.SelectorType;
import cn.nukkit.command.selector.args.ISelectorArgument;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import java.util.function.Predicate;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class R implements ISelectorArgument {
    @Override
    public Predicate<Entity> getPredicate(
            SelectorType selectorType, CommandSender sender, Location basePos, String... arguments)
            throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        final var r = Double.parseDouble(arguments[0]);
        return entity -> entity.distanceSquared(basePos) < Math.pow(r, 2);
    }

    @Override
    public String getKeyName() {
        return "r";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
