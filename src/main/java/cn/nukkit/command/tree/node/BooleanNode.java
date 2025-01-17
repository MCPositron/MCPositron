package cn.nukkit.command.tree.node;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.command.data.CommandEnum;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 * 解析对应参数为{@link Boolean}值
 * <p>
 * 所有命令枚举{@link cn.nukkit.command.data.CommandEnum#ENUM_BOOLEAN ENUM_BOOLEAN}如果没有手动指定{@link IParamNode},则会默认使用这个解析
 */
@PowerNukkitXOnly
@Since("1.19.60-r1")
public class BooleanNode extends ParamNode<Boolean> {
    private static final Set<String> ENUM_BOOLEAN = Sets.newHashSet(CommandEnum.ENUM_BOOLEAN.getValues());

    @Override
    public void fill(String arg) {
        if (ENUM_BOOLEAN.contains(arg)) this.value = Boolean.parseBoolean(arg);
        else this.error();
    }
}
