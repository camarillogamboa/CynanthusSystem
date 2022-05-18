package edu.cynanthus.dayi.util;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.common.reflect.ReflectUtil;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public final class PropertyInfoBuilder {

    private PropertyInfoBuilder() {}

    public static List<PropertyInfo> toPropertyInfoList(Config config) {
        List<PropertyInfo> propertyInfos = new LinkedList<>();

        for (Field field : config.getClass().getDeclaredFields()) {
            JProperty jProperty = field.getAnnotation(JProperty.class);
            if (jProperty != null) {
                Object value = ReflectUtil.safeGet(field, config);

                PropertyInfo propertyInfo = new PropertyInfo(
                    field.getName(),
                    String.valueOf(value),
                    jProperty.info()
                );
                propertyInfos.add(propertyInfo);
            }
        }

        return propertyInfos;
    }

}
