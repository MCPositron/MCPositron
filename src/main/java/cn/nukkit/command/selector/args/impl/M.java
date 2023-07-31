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
import cn.nukkit.player.GameMode;
import cn.nukkit.player.Player;
import java.util.function.Predicate;

@PowerNukkitXOnly
@Since("1.19.60-r1")
public class M extends CachedSimpleSelectorArgument {
    @Override
    protected Predicate<Entity> cache(
            SelectorType selectorType, CommandSender sender, Location basePos, String... arguments)
            throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        String gamemode = arguments[0];
        boolean reversed = ParseUtils.checkReversed(gamemode);
        if (reversed) gamemode = gamemode.substring(1);
        final GameMode finalGamemode = GameMode.fromString(gamemode);
        return entity -> entity instanceof Player player
                && (reversed != (player.getGamemode().equals(finalGamemode)));
    }

    @Override
    public String getKeyName() {
        return "m";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
