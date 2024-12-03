package org.utl.dsm.controller;

import java.util.List;
import org.utl.dsm.cqrs.VentaCQRS;
import org.utl.dsm.model.Ventas;

public class ControllerVenta {

    private VentaCQRS ventaCQRS;

    public ControllerVenta() {
        this.ventaCQRS = new VentaCQRS();
    }

    // Método para obtener todas las ventas registradas desde CQRS
    public List<Ventas> obtenerVentas() {
        return ventaCQRS.obtenerVentas();
    }
}
