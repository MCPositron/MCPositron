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
import org.jetbrains.annotations.Nullable;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class DX extends ScopeArgument {
    @Nullable @Override
    public Predicate<Entity> getPredicate(
            SelectorType selectorType, CommandSender sender, Location basePos, String... arguments)
            throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        var x = basePos.x();
        var dx = Double.parseDouble(arguments[0]);
        return entity -> ParseUtils.checkBetween(x, x + dx, entity.x());
    }

    @Override
    public String getKeyName() {
        return "dx";
    }
}
