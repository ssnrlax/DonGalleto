package org.utl.dsm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Ventas;

public class VentaDAO {

    private ConexionMysql conexionMysql;

    public VentaDAO() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }

    // Método para consultar todas las ventas registradas
    public List<Ventas> obtenerVentas() {
        List<Ventas> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                venta.setTotal(rs.getDouble("total"));

                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
