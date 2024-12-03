package org.utl.dsm.controller;

import java.util.List;
import org.utl.dsm.cqrs.TipoVentaCQRS;
import org.utl.dsm.model.TipoVenta;

public class ControllerTipoVenta {

    private TipoVentaCQRS tipoVentaCQRS;

    public ControllerTipoVenta() {
        this.tipoVentaCQRS = new TipoVentaCQRS();
    }

    // Método para obtener todos los tipos de venta registrados desde CQRS
    public List<TipoVenta> obtenerTiposVenta() {
        return tipoVentaCQRS.obtenerTiposVenta();
    }
}
