package cn.nukkit.utils.config;

import java.io.File;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;

public class JsonConfig extends Config {

    public JsonConfig(File file) {
        super(file, GsonConfigurationLoader.builder().file(file).build());
    }

    public JsonConfig(String fileName) {
        this(new File(fileName));
    }
}
