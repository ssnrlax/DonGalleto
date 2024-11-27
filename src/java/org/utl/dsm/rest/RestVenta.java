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

    // Endpoint to register a sale
    @POST
    @Path("registrarVenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarVenta(String ventaJson) {
        Ventas venta = gson.fromJson(ventaJson, Ventas.class);
        long idVenta = controllerProducto.registrarVentas(venta);

        if (idVenta != -1) {
            return Response.status(Response.Status.CREATED)
                    .entity("{\"idVenta\": " + idVenta + "}")
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"No se pudo registrar la venta\"}")
                    .build();
        }
    }

    // Endpoint for registering sale details
    @POST
    @Path("registrarDetalleVenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDetalleVenta(String detalleVentaJson) {
        DetallesVenta detalleVenta = gson.fromJson(detalleVentaJson, DetallesVenta.class);
        controllerProducto.registrarDetalleVentas(detalleVenta);
        
        return Response.status(Response.Status.CREATED).build();
    }

    // Endpoint for getting all sales
    @GET
    @Path("getAllVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerVentas() {
        List<Ventas> ventas = controllerProducto.obtenerVentas();
        return gson.toJson(ventas);
    }
}

