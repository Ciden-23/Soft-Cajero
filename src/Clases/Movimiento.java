/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Brayan
 */
public class Movimiento {
    private final String tipo;
    private final double monto;
    private final Date fecha;
    
    public Movimiento(String tipo, double monto , Date fecha){
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        
    }
    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

}
