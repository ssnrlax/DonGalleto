package org.utl.dsm.cqrs;

import java.util.List;
import org.utl.dsm.dao.TipoVentaDAO;
import org.utl.dsm.model.TipoVenta;

public class TipoVentaCQRS {

    private TipoVentaDAO tipoVentaDAO;

    public TipoVentaCQRS() {
        this.tipoVentaDAO = new TipoVentaDAO();
    }

    // Consulta para obtener todos los tipos de venta
    public List<TipoVenta> obtenerTiposVenta() {
        return tipoVentaDAO.obtenerTiposVenta();
    }
}
