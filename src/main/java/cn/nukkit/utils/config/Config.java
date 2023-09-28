package cn.nukkit.utils.config;

import java.io.File;
import java.util.LinkedHashMap;
import lombok.Getter;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;

public abstract class Config {

    private final @Getter File file;
    private final @Getter ConfigurationLoader<?> loader;

    private ConfigurationNode root;

    public Config(File file, ConfigurationLoader<?> loader) {
        this.file = file;
        this.loader = loader;
        this.root = this.load();
    }

    public ConfigurationNode load() {
        try {
            return loader.load();
        } catch (ConfigurateException e) {
            throw new RuntimeException("Unable to load Config " + file, e);
        }
    }

    public void save() {
        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            throw new RuntimeException("Unable to save Config " + file, e);
        }
    }

    public ConfigNode root() {
        return new ConfigNode(root);
    }

    public void setRoot(ConfigNode node) {
        this.setRoot(node.getConfigurationNode());
    }

    public void setRoot(ConfigurationNode node) {
        this.root = node;
    }

    public void setRoot(LinkedHashMap<String, Object> values) {
        this.root = loader.createNode();
        values.forEach((key, value) -> this.root().nodes(key).setValue(value));
    }

    public ConfigNode node(Object... path) {
        return this.root().node(path);
    }

    public ConfigNode nodes(String fullPath) {
        return this.root().nodes(fullPath);
    }
}
