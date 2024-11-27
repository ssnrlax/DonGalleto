/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Sabores;

/**
 *
 * @author snorl
 */
public class ControllerSabores {
    private ConexionMysql conexionMysql;

    public ControllerSabores() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }
    // Método para consultar todos los sabores registrados
    public List<Sabores> obtenerSabores() {
        List<Sabores> sabores = new ArrayList<>();
        String sql = "SELECT * FROM sabores";  // Asumimos que la tabla se llama 'sabores'

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Sabores sabor = new Sabores();
                sabor.setIdSabor(rs.getInt("idSabor"));  // Asumimos que el campo en la base de datos es 'idSabor'
                sabor.setSabor(rs.getString("sabor"));  // Asumimos que el campo en la base de datos es 'sabor'

                sabores.add(sabor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabores;
    }

}
