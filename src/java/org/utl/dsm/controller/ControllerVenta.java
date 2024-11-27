package org.utl.dsm.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Ventas;
import org.utl.dsm.model.DetallesVenta;

public class ControllerVenta {

    private ConexionMysql conexionMysql;

    public ControllerVenta() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }

    // Método para registrar una nueva venta usando un procedimiento almacenado
    public long registrarVentas(Ventas venta) {
    String sql = "{CALL sp_registrar_venta(?, ?, ?)}";
    long idVentas = -1;

    try (Connection conn = getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
        stmt.setDouble(1, venta.getTotal());
        stmt.registerOutParameter(2, Types.BIGINT);
        stmt.setTimestamp(3, Timestamp.valueOf(venta.getFecha())); // If needed, for saving the timestamp
        stmt.executeUpdate();

        idVentas = stmt.getLong(2); // Obtains the generated ID
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idVentas;
}


    // Método para registrar detalles de la venta usando un procedimiento almacenado
    public void registrarDetalleVentas(DetallesVenta detalleVentas) {
        String sql = "{CALL sp_registrar_detalle_venta(?, ?, ?, ?, ?)}";

        try (Connection conn = getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, detalleVentas.getIdVenta());
            stmt.setInt(2, detalleVentas.getIdTipoVenta());
            stmt.setInt(3, detalleVentas.getIdSabor());
            stmt.setInt(4, detalleVentas.getCantidad());
            stmt.setDouble(5, detalleVentas.getSubtotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar todas las ventas registradas
    public List<Ventas> obtenerVentas() {
        List<Ventas> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setIdVenta((int) rs.getLong("idVenta"));
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
