package cn.nukkit.command.tree.node;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

/**
 * 解析为{@link String}值
 * <p>
 * 所有命令参数类型为{@link cn.nukkit.command.data.CommandParamType#TEXT TEXT} , {@link cn.nukkit.command.data.CommandParamType#STRING STRING} ,
 * {@link cn.nukkit.command.data.CommandParamType#FILE_PATH FILE_PATH}的
 * 如果没有手动指定{@link IParamNode},则会默认使用这个解析
 */
@PowerNukkitXOnly
@Since("1.19.60-r1")
public class StringNode extends ParamNode<String> {
    @Override
    public void fill(String arg) {
        this.value = arg;
    }
}
