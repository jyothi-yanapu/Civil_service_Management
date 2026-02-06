
package com.jyothiyanapu.csms.dao;

import com.jyothiyanapu.csms.CivilServant;
import com.jyothiyanapu.csms.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CivilServantDaoImpl implements CivilServantDao {

    @Override
    public void save(CivilServant cs) {
        String sql = "INSERT INTO civil_servant (id, name, department, designation, salary) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cs.getId());
            ps.setString(2, cs.getName());
            ps.setString(3, cs.getDepartment());
            ps.setString(4, cs.getDesignation());
            ps.setDouble(5, cs.getSalary());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CivilServant findById(int id) {
        String sql = "SELECT * FROM civil_servant WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<CivilServant> findAll() {
        List<CivilServant> list = new ArrayList<>();
        String sql = "SELECT * FROM civil_servant";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void update(CivilServant cs) {
        String sql = "UPDATE civil_servant SET name=?, department=?, designation=?, salary=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cs.getName());
            ps.setString(2, cs.getDepartment());
            ps.setString(3, cs.getDesignation());
            ps.setDouble(4, cs.getSalary());
            ps.setInt(5, cs.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM civil_servant WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private CivilServant mapRow(ResultSet rs) throws SQLException {
        CivilServant cs = new CivilServant();

        cs.setId(rs.getInt("id"));
        cs.setName(rs.getString("name"));
        cs.setDepartment(rs.getString("department"));
        cs.setDesignation(rs.getString("designation"));
        cs.setSalary(rs.getDouble("salary"));

        return cs;
    }
}
