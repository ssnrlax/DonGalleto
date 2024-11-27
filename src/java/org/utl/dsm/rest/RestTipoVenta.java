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
import org.utl.dsm.controller.ControllerTipoVenta;
import org.utl.dsm.model.TipoVenta;

/**
 *
 * @author snorl
 */
@Path("tipoVenta")
public class RestTipoVenta {

    // Crear instancias
    ControllerTipoVenta controller = new ControllerTipoVenta();
    Gson gson = new Gson();

    // Endpoint para obtener todos los tipos de venta
    @GET
    @Path("getAllTiposVenta")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerTiposVenta() {
        List<TipoVenta> tiposVenta = controller.obtenerTiposVenta();
        return gson.toJson(tiposVenta);
    }

}
