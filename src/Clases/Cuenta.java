/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import Conector.BancoBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Brayan
 */
public class Cuenta {

    private Connection conn;
    private Statement sent;
    private char tipocuenta;
    private int numcuenta;
    private double saldo;
    private ArrayList<Movimiento> historial;

    public Cuenta(int numcuenta) {
        try {
            conn = BancoBD.getConnection();
            String sql = "SELECT * FROM CUENTAS WHERE numcuenta=" + numcuenta;
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            rs.next();
            tipocuenta = rs.getString("tipocuenta").charAt(0);
            this.numcuenta = numcuenta;
            saldo = rs.getDouble("saldo");
            llenarhistorial();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error intente mas tarde.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo void, el cual tiene la funcion de conectarse a la BD del banco 
     * y obtener el historial de movimientos del a cuenta.
     */
    private void llenarhistorial() {
        try {
            conn = BancoBD.getConnection();
            String sql = "SELECT * FROM HISTORIAL WHERE numcuenta=" + numcuenta;
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            historial = new ArrayList<>();
            while (rs.next()) {
                String tipo;
                double monto;
                Date fecha;
                tipo = rs.getString("tipo");
                monto = rs.getDouble("monto");
                fecha = rs.getDate("fecha");
                historial.add(new Movimiento(tipo, monto, fecha));
            }

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al obtener los ultimos movimientos.");
            e.printStackTrace();
        }
    }

    public char getTipocuenta() {
        return tipocuenta;
    }

    public int getNumcuenta() {
        return numcuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Movimiento> getHistorial() {
        return historial;
    }
}
