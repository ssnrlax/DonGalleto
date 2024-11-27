/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.utl.dsm.controller.ControllerSabores;
import org.utl.dsm.model.Sabores;

/**
 *
 * @author snorl
 */
@Path("sabores")
public class RestSabores {
    // Crear instancias
    ControllerSabores controller = new ControllerSabores();
    Gson gson = new Gson();
    
    // Endpoint para obtener todos los sabores
    @GET
    @Path("getAllSabores")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerSabores() {
        List<Sabores> sabores = controller.obtenerSabores();
        return gson.toJson(sabores);
    }
}
