/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 * Clase estatica que contiene la implementacion de operaciones del cajero.
 * @author Brayan
 */
public class Operacion {

    private static Usuario usr;
/**
 * Metodo void que asigna un usuario que relizara operaciones
 * @param usr El usuario que utiliza el cajero
 */
    public static void setUsuario(Usuario usr) {
        Operacion.usr = usr;
    }

    /**
     * Metodo double que consulta el saldo actual del usuario
     * @return El saldo de cuenta del usuario.
     */
    public static double consultasaldo() {
        return usr.getCuenta().getSaldo();
    }

    /**
     * Metodo que busca el ultimo deposito realizado.
     * @return El movimiento que contiene los datos.
     */
    public static Movimiento ultimodeposito() {
        ArrayList<Movimiento> hist = usr.getCuenta().getHistorial();
        Movimiento res = null;
        for (int i = 0; i < hist.size(); i++) {
            Movimiento mov = hist.get(i);
            String tipo = mov.getTipo();
            if ("Deposito".equals(tipo)) {
                res = mov;
            }
        }
        return res;
    }

     /**
     * Metodo que busca el ultimo retiro realizado.
     * @return El movimiento que contiene los datos.
     */
    public static Movimiento ultimoretiro() {
        ArrayList<Movimiento> hist = usr.getCuenta().getHistorial();
        Movimiento res = null;
        for (int i = 0; i < hist.size(); i++) {
            Movimiento mov = hist.get(i);
            String tipo = mov.getTipo();
            if ("Retiro".equals(tipo)) {
                res = mov;
            }
        }
        return res;
    }

     /**
     * Metodo que busca la ultima transferencia realizada.
     * @return El movimiento que contiene los datos.
     */
    public static Movimiento ultimatransferencia() {
        ArrayList<Movimiento> hist = usr.getCuenta().getHistorial();
        Movimiento res = null;
        for (int i = 0; i < hist.size(); i++) {
            Movimiento mov = hist.get(i);
            String tipo = mov.getTipo();
            if ("Transferencia".equals(tipo)) {
                res = mov;
            }
        }
        return res;
    }

     /**
     * Metodo que devuelve el historial de la cuenta.
     * @return Una lista que contiene el historial de movimentos.
     */
    public static ArrayList<Movimiento> verhistorial() {
        return usr.getCuenta().getHistorial();
    }
}
