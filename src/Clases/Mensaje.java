/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 * Clase que contiene los mensajes repetidos del cajero.
 * @author Brayan
 */
public class Mensaje {

    private static final String[] mensaje = {"Inserte Tarjeta","Seleccione una operacion:","¿Desea relizar otra operacion?"};

    /**
     * Metodo String que devuelve un mensaje seleccionado del arreglo de mensajes.
     * @param n El indice del mensaje
     * @return El mensaje a ser mostrado
     */
    public static String getMensaje(int n) {
        return mensaje[n];
    }
}
