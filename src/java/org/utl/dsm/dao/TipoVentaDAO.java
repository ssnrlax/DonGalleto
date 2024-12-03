package org.utl.dsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.TipoVenta;

public class TipoVentaDAO {

    private ConexionMysql conexionMysql;

    public TipoVentaDAO() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }

    // Método para consultar todos los tipos de venta registrados
    public List<TipoVenta> obtenerTiposVenta() {
        List<TipoVenta> tiposVenta = new ArrayList<>();
        String sql = "SELECT * FROM tiposVenta"; 

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TipoVenta tipoVenta = new TipoVenta();
                tipoVenta.setIdTipoVenta(rs.getInt("idTipoVenta")); 
                tipoVenta.setTipo(rs.getString("tipo"));
                tipoVenta.setPrecio(rs.getDouble("precio"));
                tipoVenta.setGalletas(rs.getInt("galletas")); 

                tiposVenta.add(tipoVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposVenta;
    }
}
