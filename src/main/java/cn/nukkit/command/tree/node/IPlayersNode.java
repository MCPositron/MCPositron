package cn.nukkit.command.tree.node;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.exceptions.SelectorSyntaxException;
import cn.nukkit.command.selector.EntitySelectorAPI;
import cn.nukkit.entity.Entity;
import cn.nukkit.player.IPlayer;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 解析为{@code List<IPlayer>}值
 * <p>
 * 不会默认使用，需要手动指定
 */
@PowerNukkitXOnly
@Since("1.19.60-r1")
public class IPlayersNode extends ParamNode<List<IPlayer>> {
    @Override
    public void fill(String arg) {
        if (arg.isBlank()) {
            this.error();
        } else if (EntitySelectorAPI.getAPI().checkValid(arg)) {
            List<Entity> entities;
            List<IPlayer> result;
            try {
                entities = EntitySelectorAPI.getAPI().matchEntities(this.parent.parent.getSender(), arg);
            } catch (SelectorSyntaxException exception) {
                error(exception.getMessage());
                return;
            }
            result = entities.stream()
                    .filter(entity -> entity instanceof IPlayer)
                    .map(entity -> (IPlayer) entity)
                    .collect(Collectors.toList());
            if (!result.isEmpty()) this.value = result;
            else error("commands.generic.noTargetMatch");
        } else {
            error("commands.generic.player.notFound");
        }
    }
}
