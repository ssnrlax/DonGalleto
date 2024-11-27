/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

/**
 *
 * @author snorl
 */
public class DetallesVenta {
    private int idDetalleVenta; // AUTO_INCREMENT PRIMARY KEY
    private int idVenta; // Relación con la venta
    private int idTipoVenta; // Relación con el tipo de venta
    private int idSabor; // Relación con el sabor
    private int cantidad; // Cantidad vendida
    private double subtotal; // Subtotal por esta línea

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(int idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetallesVenta{");
        sb.append("idDetalleVenta=").append(idDetalleVenta);
        sb.append(", idVenta=").append(idVenta);
        sb.append(", idTipoVenta=").append(idTipoVenta);
        sb.append(", idSabor=").append(idSabor);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", subtotal=").append(subtotal);
        sb.append('}');
        return sb.toString();
    }
}
