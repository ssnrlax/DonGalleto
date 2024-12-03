package org.utl.dsm.cqrs;

import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.dao.SaboresDAO;
import org.utl.dsm.model.Sabores;
import org.utl.dsm.model.SaboresViewModel;
import org.utl.dsm.APPService.SucursalExternaAppService;

public class SaboresCQRS {

    private SaboresDAO saboresDAO;

    public SaboresCQRS() {
        this.saboresDAO = new SaboresDAO();
    }

    // Consulta para obtener todos los sabores
    public List<Sabores> obtenerSabores() {
        return saboresDAO.obtenerSabores();
    }

    // Consulta para obtener sabores de API externa
    public List<SaboresViewModel> getAllSabores() throws Exception {
        SucursalExternaAppService sucursalExterna = new SucursalExternaAppService();
        return sucursalExterna.apiSucursalExterna();
    }

    // Comando para actualizar la cantidad de un sabor con validación
    public boolean actualizarCantidadSabor(int idSabor, int cantidad) throws SQLException {
        // Validación de existencia
        int cantidadActual = saboresDAO.obtenerCantidadPorId(idSabor);
        if (cantidadActual < cantidad) {
            String mensajeError = "No hay suficientes existencias del sabor con ID: " + idSabor + 
                                  ". Existencias actuales: " + cantidadActual + 
                                  ", solicitado: " + cantidad;
            System.err.println(mensajeError); // Imprimir mensaje en la consola
            throw new IllegalArgumentException(mensajeError);
        }
        return saboresDAO.actualizarCantidadSabor(idSabor, cantidad);
    }
}
