package org.utl.dsm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Sabores;

public class SaboresDAO {

    private ConexionMysql conexionMysql;

    public SaboresDAO() {
        this.conexionMysql = new ConexionMysql();
    }

    private Connection getConnection() throws SQLException {
        return conexionMysql.open();
    }

    public List<Sabores> obtenerSabores() {
        List<Sabores> sabores = new ArrayList<>();
        String sql = "SELECT * FROM sabores";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Sabores sabor = new Sabores();
                sabor.setIdSabor(rs.getInt("idSabor"));
                sabor.setSabor(rs.getString("sabor"));
                sabor.setCantidad(rs.getInt("cantidad"));

                sabores.add(sabor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sabores;
    }

    public boolean actualizarCantidadSabor(int idSabor, int cantidad) throws SQLException {
        String sql = "UPDATE sabores SET cantidad = cantidad - ? WHERE idSabor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cantidad);
            stmt.setInt(2, idSabor);
            return stmt.executeUpdate() > 0;
        }
    }

    public int obtenerCantidadPorId(int idSabor) throws SQLException {
        String sql = "SELECT cantidad FROM sabores WHERE idSabor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSabor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad");
                }
            }
        }
        return 0; // Retorna 0 si no se encuentra el registro.
    }
}
