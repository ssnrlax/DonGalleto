package org.utl.dsm.controller;

import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.cqrs.SaboresCQRS;
import org.utl.dsm.model.Sabores;
import org.utl.dsm.model.SaboresViewModel;

public class ControllerSabores {

    private SaboresCQRS saboresCQRS;

    public ControllerSabores() {
        this.saboresCQRS = new SaboresCQRS();
    }

    // Método para obtener todos los sabores de la base de datos
    public List<Sabores> obtenerSabores() {
        return saboresCQRS.obtenerSabores();
    }

    // Método para obtener sabores desde una API externa
    public List<SaboresViewModel> getAllSabores() {
        try {
            return saboresCQRS.getAllSabores();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los sabores desde la API externa: " + e.getMessage());
        }
    }

    // Método para actualizar la cantidad de un sabor con validación de existencia
    public boolean actualizarCantidadSabor(int idSabor, int cantidad) {
        try {
            return saboresCQRS.actualizarCantidadSabor(idSabor, cantidad);
        } catch (IllegalArgumentException e) {
            System.err.println("Validación fallida: " + e.getMessage());
            return false; // Regresar un valor para indicar el error al cliente
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el sabor en la base de datos: " + e.getMessage());
        }
    }
}
