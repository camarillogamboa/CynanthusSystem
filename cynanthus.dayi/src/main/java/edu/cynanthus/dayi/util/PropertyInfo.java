package edu.cynanthus.dayi.util;

import java.util.Objects;

public class PropertyInfo {

    private final String key;

    private final String value;

    private final String info;

    public PropertyInfo(String key, String value, String info) {
        this.key = key;
        this.value = value;
        this.info = info;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyInfo that = (PropertyInfo) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, info);
    }

    @Override
    public String toString() {
        return "{" +
            "key:'" + key + '\'' +
            ",value:'" + value + '\'' +
            ",info:'" + info + '\'' +
            '}';
    }

}
