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
import org.utl.dsm.model.TipoVenta;

/**
 *
 * @author snorl
 */
public class ControllerTipoVenta {

    private ConexionMysql conexionMysql;

    public ControllerTipoVenta() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }
    
    // Método para consultar todos los tipos de venta registrados
    public List<TipoVenta> obtenerTiposVenta() {
        List<TipoVenta> tiposVenta = new ArrayList<>();
        String sql = "SELECT * FROM tiposVenta";  // Asumimos que la tabla se llama 'tipo_venta'

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TipoVenta tipoVenta = new TipoVenta();
                tipoVenta.setIdTipoVenta(rs.getInt("idTipoVenta"));  // Asumimos que el campo en la base de datos es 'idTipoVenta'
                tipoVenta.setTipo(rs.getString("tipo"));  // Asumimos que el campo en la base de datos es 'tipo'
                tipoVenta.setPrecio(rs.getDouble("precio"));  // Asumimos que el campo en la base de datos es 'precio'

                tiposVenta.add(tipoVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposVenta;
    }

}
