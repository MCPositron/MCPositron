package cn.nukkit.command.defaults;

import cn.nukkit.api.Since;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.tree.node.PlayersNode;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.player.Player;
import java.util.List;
import java.util.Map;

/**
 * @author xtypr
 * @since 2015/11/12
 */
public class TellCommand extends VanillaCommand {

    public TellCommand(String name) {
        super(name, "commands.tell.description", "", new String[] {"w", "msg"});
        this.setPermission("nukkit.command.tell");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
            CommandParameter.newType("player", CommandParamType.TARGET, new PlayersNode()),
            CommandParameter.newType("message", CommandParamType.MESSAGE)
        });
        this.enableParamTree();
    }

    @Since("1.19.60-r1")
    @Override
    public int execute(
            CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        var list = result.getValue();
        List<Player> players = list.getResult(0);
        if (players.isEmpty()) {
            log.addNoTargetMatch().output();
            return 0;
        }
        String msg = list.getResult(1);
        for (Player player : players) {
            if (player == sender) {
                log.addError("commands.message.sameTarget").output();
                continue;
            }
            log.addSuccess("commands.message.display.outgoing", player.getName(), msg);
            player.sendMessage(new TranslationContainer("commands.message.display.incoming", sender.getName(), msg));
        }
        log.output();
        return 1;
    }
}
