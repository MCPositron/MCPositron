package cn.nukkit.command.selector.args.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.exceptions.SelectorSyntaxException;
import cn.nukkit.command.selector.ParseUtils;
import cn.nukkit.command.selector.SelectorType;
import cn.nukkit.command.selector.args.CachedSimpleSelectorArgument;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import java.util.function.Predicate;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class RXM extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(
            SelectorType selectorType, CommandSender sender, Location basePos, String... arguments)
            throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        final var rxm = Double.parseDouble(arguments[0]);
        if (!ParseUtils.checkBetween(-90d, 90d, rxm))
            throw new SelectorSyntaxException("RXM out of bound (-90 - 90): " + rxm);
        return entity -> entity.pitch() >= rxm;
    }

    @Override
    public String getKeyName() {
        return "rxm";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
