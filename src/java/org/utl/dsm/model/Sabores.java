/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

/**
 *
 * @author snorl
 */
public class Sabores {
    private int idSabor; // AUTO_INCREMENT PRIMARY KEY
    private String sabor; // Ejemplo: Chocolate, Vainilla

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores{");
        sb.append("idSabor=").append(idSabor);
        sb.append(", sabor=").append(sabor);
        sb.append('}');
        return sb.toString();
    }
}
