package cn.nukkit.utils.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class ConfigNode {

    private final ConfigurationNode node;

    public ConfigNode(ConfigurationNode node) {
        this.node = node;
    }

    public ConfigurationNode getConfigurationNode() {
        return node;
    }

    public ConfigNode node(Object... path) {
        return new ConfigNode(node.node(path));
    }

    public ConfigNode nodes(String fullPath) {
        String[] pathArray = fullPath.split("\\.");
        ConfigurationNode currentNode = node;

        for (String path : pathArray) {
            currentNode = currentNode.node(path);
        }

        return new ConfigNode(currentNode);
    }

    public Object getValue() {
        return getValue(null);
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(T defaultValue) {
        Object rawValue = node.raw();
        return (rawValue != null) ? (T) rawValue : defaultValue;
    }

    public <T> T getValue(Class<T> classOfT) {
        try {
            return node.get(classOfT);
        } catch (SerializationException e) {
            return null;
        }
    }

    public void setValue(Object value) {
        try {
            node.set(value);
        } catch (SerializationException ignore) {

        }
    }

    public Object raw() {
        return node.raw();
    }

    public boolean isNull() {
        return node.raw() == null;
    }

    public String getString() {
        return getString(null);
    }

    public String getString(String defaultValue) {
        return String.valueOf(getValue(defaultValue));
    }

    public boolean isString() {
        return node.raw() instanceof String;
    }

    public Integer getInt() {
        return getInt(0);
    }

    public Integer getInt(Integer defaultValue) {
        return Integer.parseInt(String.valueOf(getValue(defaultValue)));
    }

    public boolean isInt() {
        return node.raw() instanceof Integer;
    }

    public Long getLong() {
        return getLong(0L);
    }

    public Long getLong(Long defaultValue) {
        return Long.parseLong(String.valueOf(getValue(defaultValue)));
    }

    public boolean isLong() {
        return node.raw() instanceof Long;
    }

    public Double getDouble() {
        return getDouble(0D);
    }

    public Double getDouble(Double defaultValue) {
        return Double.parseDouble(String.valueOf(getValue(defaultValue)));
    }

    public boolean isDouble() {
        return node.raw() instanceof Double;
    }

    public Float getFloat() {
        return getFloat(0F);
    }

    public Float getFloat(Float defaultValue) {
        return Float.parseFloat(String.valueOf(getValue(defaultValue)));
    }

    public boolean isFloat() {
        return node.raw() instanceof Float;
    }

    public Boolean getBoolean() {
        return getBoolean(false);
    }

    public Boolean getBoolean(Boolean defaultValue) {
        return getValue(defaultValue);
    }

    public boolean isBoolean() {
        return node.raw() instanceof Boolean;
    }

    public <T> List<T> getList() {
        return getList(Collections.emptyList());
    }

    public <T> List<T> getList(List<T> defaultValue) {
        return getValue(defaultValue);
    }

    public boolean isList() {
        return node.isList();
    }

    public List<String> getStringList() {
        return getStringList(Collections.emptyList());
    }

    public List<String> getStringList(List<String> defaultValue) {
        return getValue(defaultValue);
    }

    public <K, V> Map<K, V> getMap() {
        return getMap(Collections.emptyMap());
    }

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> getMap(Map<K, V> defaultValue) {
        Object value = node.raw();
        return (value instanceof Map) ? (Map<K, V>) value : defaultValue;
    }

    public boolean isMap() {
        return node.isMap();
    }

    @Override
    public String toString() {
        return node.toString();
    }
}
