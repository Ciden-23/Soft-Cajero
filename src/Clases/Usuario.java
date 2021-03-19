/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

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
public class Usuario {

    private Connection conn;
    private Statement sent;
    private String nombre;
    private String apellido;
    private int ci;
    private Date fchnac;
    private String direcc;
    private Cuenta Cuenta;

    public Usuario(int numcuenta) {
        try {
            conn = BancoBD.getConnection();
            String sql = "SELECT * FROM TITULARES WHERE numcuenta=" + numcuenta;
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            rs.next();
            nombre = rs.getString("nombre");
            apellido = rs.getString("apellido");
            ci = rs.getInt("ci");
            fchnac = rs.getDate("fchnac");
            direcc = rs.getString("direccion");
            Cuenta = new Cuenta(numcuenta);
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al obtener los datos del usuario.");
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getCi() {
        return ci;
    }

    public Date getFchnac() {
        return fchnac;
    }

    public String getDirecc() {
        return direcc;
    }

    public Cuenta getCuenta() {
        return Cuenta;
    }
}
