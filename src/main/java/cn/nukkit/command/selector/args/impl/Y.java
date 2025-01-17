package cn.nukkit.command.selector.args.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.exceptions.SelectorSyntaxException;
import cn.nukkit.command.selector.ParseUtils;
import cn.nukkit.command.selector.SelectorType;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import java.util.function.Predicate;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class Y extends CoordinateArgument {
    @Override
    public Predicate<Entity> getPredicate(
            SelectorType selectorType, CommandSender sender, Location basePos, String... arguments)
            throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        basePos.setY(ParseUtils.parseOffsetDouble(arguments[0], basePos.y()));
        return null;
    }

    @Override
    public String getKeyName() {
        return "y";
    }
}
