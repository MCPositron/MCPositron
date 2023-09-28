package cn.nukkit.utils.config;

import java.io.File;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

public class HoconConfig extends Config {

    public HoconConfig(File file) {
        super(file, HoconConfigurationLoader.builder().file(file).build());
    }

    public HoconConfig(String fileName) {
        this(new File(fileName));
    }
}
