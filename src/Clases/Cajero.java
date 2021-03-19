/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Scanner;
import Conector.BancoBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase instanciable que representa a un cajero automatico
 * @author Brayan
 */
public class Cajero {
    
    private static Connection conn;
    private static Statement sent;
    private static boolean estTarj = false;
    private static Usuario usuario;
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int numcnta = 0;
        boolean term = false;
        while (!estTarj) {
            System.out.println(Mensaje.getMensaje(0));
            numcnta = sc.nextInt();
            compPin(numcnta);
            setTarj();
        }
        usuario = new Usuario(numcnta);
        while (!term) {
            System.out.println(Mensaje.getMensaje(1));
            Operacion.setUsuario(usuario);
            int op = sc.nextInt();
            switch (op) {
                case 0 ->
                    consulta();
            }
            System.out.println(Mensaje.getMensaje(2));
            int sel = sc.nextInt();
            if (sel == 0) {
                term = true;
            }
        }
        
    }

    /**
     * Metodo void utilizado para hacer la comparacion del pin ingresado con la
     * BD Si no se ingresa el pin correcto no se pasara de esta pantalla.
     *
     * @param numcnta El numero de cuenta del usuario.
     */
    private static void compPin(int numcnta) {
        Scanner sc = new Scanner(System.in);
        boolean acceso = false;
        boolean correcto = false;
        int pinc, pin;
        try {
            conn = BancoBD.getConnection();
            String sql = "SELECT PIN FROM PINES WHERE numcuenta=" + numcnta;
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            rs.next();
            pinc = rs.getInt("PIN");
            while (!correcto) {
                System.out.println("Ingrese su pin");
                pin = sc.nextInt(4);
                if (pinc != pin) {
                    System.out.println("Pin incorrecto");
                } else {
                    System.out.println("Bienvenido");
                    correcto = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al obtener los datos del usuario.");
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo void que realiza las operaciones de consulta haciendo uso de la clase Operaciones
     */
    private static void consulta() {
        Scanner sc = new Scanner(System.in);
        boolean term = false;
        while (!term) {
            System.out.println(Mensaje.getMensaje(2));
            int op = sc.nextInt();
            switch (op) {
                case 0:
                    double saldo = Operacion.consultasaldo();
                    System.out.println("Su saldo es: " + saldo);
                    break;
                case 1:
                    Movimiento ultdep = Operacion.ultimodeposito();
                    if (ultdep == null) {
                        System.out.println("No hay depositos recientes en esta cuenta");
                    } else {
                        System.out.println("Su ultimo deposito fue en fecha: " + ultdep.getFecha());
                        System.out.println("La cantidad depositada fue: " + ultdep.getMonto());
                        break;
                    }
                case 2:
                    Movimiento ultret = Operacion.ultimodeposito();
                    if (ultret == null) {
                        System.out.println("No hay retiros recientes en esta cuenta");
                    } else {
                        System.out.println("Su ultimo retiro fue en fecha: " + ultret.getFecha());
                        System.out.println("La cantidad retirada fue: " + ultret.getMonto());
                        break;
                    }
                case 3:
                    Movimiento ulttran = Operacion.ultimodeposito();
                    if (ulttran == null) {
                        System.out.println("No hay transferencias recientes en esta cuenta");
                    } else {
                        System.out.println("Su ultimo transferencia fue en fecha: " + ulttran.getFecha());
                        System.out.println("La cantidad transferida fue: " + ulttran.getMonto());
                        break;
                    }
                case 4:
                    ArrayList<Movimiento> hist = Operacion.verhistorial();
                    if (hist == null) {
                        System.out.println("No hay operaciones recientes en esta cuenta");
                    } else {
                        System.out.println("----------Historial de movimientos----------");
                        for (int i = 0; i < hist.size(); i++) {
                            Movimiento mov = hist.get(i);
                            String tipo = mov.getTipo();
                            Date fecha = mov.getFecha();
                            double monto = mov.getMonto();
                            System.out.println("Tipo: " + tipo + "  Fecha: " + fecha + "  monto: " + monto);
                        }
                    }
            }
            System.out.println(Mensaje.getMensaje(2));
            int sel = sc.nextInt();
            if (sel == 0) {
                term = true;
            }
        }
    }

    /**
     * Metodo void que verifica si se ingreso una tajeta al cajero
     */
    private static void setTarj() {
        estTarj = !estTarj;
    }
}
