/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project2_codehero;

import Archivo.CargadorArchivo;

/**
 *
 * @author camran1234
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        String cadena = "Lunes-Miercoles-Jueves-Sabado-";
        String[] a = cadena.split("-");
        for(int index=0; index<a.length; index++){
            System.out.println(a[index]);
        }
    }
}
