package com.jyothiyanapu.csms.util;

import com.jyothiyanapu.csms.annotations.Column;
import com.jyothiyanapu.csms.annotations.Entity;
import com.jyothiyanapu.csms.annotations.Table;

import java.lang.reflect.Field;

public class SqlGenerator {

    public static void generateCreateTableSQL(Class<?> clazz) {

        if (!clazz.isAnnotationPresent(Entity.class)) {
            System.out.println("Not an entity class");
            return;
        }

        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.name();

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(tableName).append(" (\n");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                sql.append("  ")
                        .append(column.name())
                        .append(" ")
                        .append(mapType(field.getType()))
                        .append(",\n");
            }
        }

        sql.deleteCharAt(sql.length() - 2);
        sql.append("\n);");

        System.out.println(sql);
    }

    private static String mapType(Class<?> type) {
        if (type == int.class) return "INT";
        if (type == double.class) return "DOUBLE";
        if (type == String.class) return "VARCHAR(255)";
        return "UNKNOWN";
    }
}
