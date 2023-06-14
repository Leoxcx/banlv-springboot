package com.sgz.banlv.utils;

import java.lang.reflect.Field;

public class CommonUtils {

    public static Boolean isLongNotEmpty(Long value) {
        return value != 0 && value != null ;
    }

    public static boolean isIntegerNotEmpty(Integer value) {
        return value != 0 && value != null ;
    }

    /**
     * 使用反射实现对象拷贝
     */
    public static void copy(Object source, Object target) throws IllegalAccessException {
        // 获取源对象的Class对象
        Class<?> sourceClass = source.getClass();
        // 获取目标对象的Class对象
        Class<?> targetClass = target.getClass();
        // 获取源对象的所有成员变量
        Field[] sourceFields = sourceClass.getDeclaredFields();
        // 遍历源对象的所有成员变量
        for (Field sourceField : sourceFields) {
            // 设置成员变量可访问
            sourceField.setAccessible(true);
            // 获取成员变量的值
            Object value = sourceField.get(source);
            // 获取成员变量的名称
            String fieldName = sourceField.getName();
            try {
                // 获取目标对象对应的成员变量
                Field targetField = targetClass.getDeclaredField(fieldName);
                // 设置目标对象对应成员变量的值
                targetField.setAccessible(true);
                targetField.set(target, value);
            } catch (NoSuchFieldException e) {
                // 如果目标对象没有对应的成员变量，则忽略该成员变量
            }
        }
    }
}
