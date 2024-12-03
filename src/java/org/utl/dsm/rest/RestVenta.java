package org.utl.dsm.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.utl.dsm.controller.ControllerVenta;
import org.utl.dsm.model.DetallesVenta;
import org.utl.dsm.model.Ventas;
import java.util.List;

@Path("productos")
public class RestVenta {

    private ControllerVenta controllerProducto = new ControllerVenta();
    private Gson gson = new Gson();

    // Endpoint for getting all sales
    @GET
    @Path("getAllVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerVentas() {
        List<Ventas> ventas = controllerProducto.obtenerVentas();
        return gson.toJson(ventas);
    }
}

