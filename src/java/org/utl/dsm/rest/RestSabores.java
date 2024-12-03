/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import org.utl.dsm.APPService.SucursalExternaAppService;
import org.utl.dsm.controller.ControllerSabores;
import org.utl.dsm.model.Sabores;
import org.utl.dsm.model.SaboresViewModel;

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
    
      @GET
    @Path("getAllSaboresExternos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSabores() {
        String out;
        try {
            // Llamamos al servicio de la API externa para obtener los sabores
            SucursalExternaAppService servicio = new SucursalExternaAppService();
            List<SaboresViewModel> sabores = servicio.apiSucursalExterna();
            
            // Imprime los sabores para ver el resultado
            System.out.println("Sabores obtenidos: " + sabores.size());
            for (SaboresViewModel sabor : sabores) {
                System.out.println(sabor);
            }
            
            // Convertimos la lista a JSON para la respuesta
            Gson gson = new Gson();
            out = gson.toJson(sabores);
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\": \"" + ex.getMessage() + "\"}";
        }
        
        // Devolvemos la respuesta con código HTTP 200 OK
        return Response.status(Response.Status.OK).entity(out).build();
    }

  // Endpoint para actualizar las cantidades de los sabores
 @POST
@Path("actualizar")
@Consumes(MediaType.APPLICATION_JSON)  // Indicamos que estamos recibiendo JSON
@Produces(MediaType.APPLICATION_JSON)  // Indicamos que estamos produciendo JSON en la respuesta
public Response actualizarCantidades(String datos) throws SQLException {
    // Usamos Gson para convertir el JSON (String) en una lista de objetos Sabores
    Gson gson = new Gson();
    // Convertimos el JSON recibido en una lista de objetos Sabores
    List<Sabores> ajustes = gson.fromJson(datos, new TypeToken<List<Sabores>>(){}.getType());

    // Procesamos la lista de ajustes
    for (Sabores ajuste : ajustes) {
        boolean actualizado = controller.actualizarCantidadSabor(ajuste.getIdSabor(), ajuste.getCantidad());
        if (!actualizado) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"mensaje\": \"Error al actualizar el sabor con id: " + ajuste.getIdSabor() + "\"}")
                    .build();
        }
    }

    return Response.ok("{\"mensaje\": \"Cantidades actualizadas correctamente\"}").build();
}

}
