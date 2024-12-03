package org.utl.dsm.cqrs;

import java.util.List;
import org.utl.dsm.dao.VentaDAO;
import org.utl.dsm.model.Ventas;

public class VentaCQRS {

    private VentaDAO ventaDAO;

    public VentaCQRS() {
        this.ventaDAO = new VentaDAO();
    }

    // Consulta para obtener todas las ventas
    public List<Ventas> obtenerVentas() {
        return ventaDAO.obtenerVentas();
    }
}
