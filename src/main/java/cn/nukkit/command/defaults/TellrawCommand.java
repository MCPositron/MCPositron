package cn.nukkit.command.defaults;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.tree.node.PlayersNode;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.command.utils.RawText;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.player.Player;
import cn.nukkit.utils.TextFormat;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import java.util.Map;

@PowerNukkitXOnly
@Since("1.6.0.0-PNX")
public class TellrawCommand extends VanillaCommand {

    public TellrawCommand(String name) {
        super(name, "commands.tellraw.description");
        this.setPermission("nukkit.command.tellraw");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
            CommandParameter.newType("player", CommandParamType.TARGET, new PlayersNode()),
            CommandParameter.newType("rawtext", CommandParamType.RAWTEXT)
        });
        this.enableParamTree();
    }

    @Since("1.19.60-r1")
    @Override
    public int execute(
            CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        var list = result.getValue();
        try {
            List<Player> players = list.getResult(0);
            if (players.isEmpty()) {
                log.addNoTargetMatch().output();
                return 0;
            }
            RawText rawTextObject = list.getResult(1);
            rawTextObject.preParse(sender);
            for (Player player : players) {
                player.sendRawTextMessage(rawTextObject);
            }
            return 1;
        } catch (JsonSyntaxException e) {
            sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.tellraw.jsonStringException"));
            return 0;
        }
    }
}
