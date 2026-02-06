package com.jyothiyanapu.csms.dao;

import com.jyothiyanapu.csms.annotations.*;
import com.jyothiyanapu.csms.db.DBConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenericDao<T> implements GenericDao<T> {

    private final Class<T> type;
    private final Table table;

    public JdbcGenericDao(Class<T> type) {

        this.type = type;
        table = type.getAnnotation(Table.class);
    }

    @Override
    public void save(T entity) {

        try {
            // 1️⃣ Read @Table annotation
            if (table == null) {
                throw new RuntimeException("Missing @Table annotation");
            }
            String tableName = table.name();

            // 2️⃣ Build column names and placeholders
            Field[] fields = type.getDeclaredFields();
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();

            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    columns.append(column.name()).append(",");
                    placeholders.append("?").append(",");
                }
            }

            // remove last comma
            columns.deleteCharAt(columns.length() - 1);
            placeholders.deleteCharAt(placeholders.length() - 1);

            // 3️⃣ Create SQL
            String sql = "INSERT INTO " + tableName +
                    " (" + columns + ") VALUES (" + placeholders + ")";

            // 4️⃣ Create PreparedStatement
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                // 5️⃣ Set values dynamically
                int index = 1;
                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        field.setAccessible(true);
                        ps.setObject(index++, field.get(entity));
                    }
                }
                ps.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findById(int id) {
        //Get one record from DB
        System.out.println("Finding by id in: " + type.getSimpleName());
        String sql = "SELECT * FROM " + table.name() + " WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //Create empty object using reflection.
                T obj = type.getDeclaredConstructor().newInstance();
                //Loop through fields and set values
                Field[] fields = type.getDeclaredFields();

                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        field.setAccessible(true);
                        // Read value from ResultSet using column name
                        Object value = rs.getObject(column.name());
                        field.set(obj, value);
                    }
                }
                return obj;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<T> findAll() {

        List<T> result = new ArrayList<>();
        String sql = "SELECT * FROM " + table.name();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // 1️⃣ Create NEW object for each row  T
                T obj = type.getDeclaredConstructor().newInstance();

                // 2️⃣ Get all fields of the class
                Field[] fields = type.getDeclaredFields();

                // 3️⃣ Set values from ResultSet → object
                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);

                    if (column != null) {
                        field.setAccessible(true);

                        // Read value using column name
                        Object value = rs.getObject(column.name());

                        // Put value into object field
                        field.set(obj, value);
                    }
                }

                // 4️⃣ Add populated object to list
                result.add(obj);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean deleteById(int id) {
        //Remove record from DB
        System.out.println("Deleting from: " + type.getSimpleName());

        try {
            // 1️⃣ Read @Table annotation

            if (table == null) {
                throw new RuntimeException("Missing @Table annotation");
            }
            String tableName = table.name();
            String sql = "DELETE FROM " + tableName + " WHERE id=?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                return rows > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            if (table == null) {
                throw new RuntimeException("Missing @Table annotation");
            }

            String tableName = table.name();
            Field[] fields = type.getDeclaredFields();

            StringBuilder setClause = new StringBuilder();
            String idColumn = null;
            Object idValue = null;

            // 1️⃣ Build SET part and find ID column
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);

                if (column != null) {
                    field.setAccessible(true);

                    if ("id".equalsIgnoreCase(column.name())) {
                        idColumn = column.name();
                        idValue = field.get(entity);
                    } else {
                        setClause.append(column.name()).append("=?,");
                    }
                }
            }

            // remove last comma
            setClause.deleteCharAt(setClause.length() - 1);

            // 2️⃣ Create SQL
            String sql = "UPDATE " + tableName +
                    " SET " + setClause +
                    " WHERE " + idColumn + "=?";

            // 3️⃣ Execute update
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                int index = 1;

                // 4️⃣ Set column values (except ID)
                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);

                    if (column != null && !"id".equalsIgnoreCase(column.name())) {
                        field.setAccessible(true);
                        ps.setObject(index++, field.get(entity));
                    }
                }

                // 5️⃣ Set ID in WHERE clause
                ps.setObject(index, idValue);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
