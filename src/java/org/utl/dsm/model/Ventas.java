/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

import java.time.LocalDateTime;

/**
 *
 * @author snorl
 */
public class Ventas {
    private int idVenta; // AUTO_INCREMENT PRIMARY KEY
    private LocalDateTime fecha; // Fecha de la venta
    private double total; // Total de la venta

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ventas{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", fecha=").append(fecha);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
