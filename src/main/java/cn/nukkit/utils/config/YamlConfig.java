package cn.nukkit.utils.config;

import java.io.File;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class YamlConfig extends Config {

    public YamlConfig(File file) {
        super(
                file,
                YamlConfigurationLoader.builder()
                        .file(file)
                        .nodeStyle(NodeStyle.BLOCK)
                        .indent(2)
                        .build());
    }

    public YamlConfig(String fileName) {
        this(new File(fileName));
    }
}
