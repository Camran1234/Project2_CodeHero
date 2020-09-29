/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class Consulta {
    private String tipo;
    private double costo;
    
    public void SubirArchivo(Element elementoXML){
        try {
            tipo = elementoXML.getElementsByTagName("TIPO").item(0).getTextContent();
            costo = Double.parseDouble(elementoXML.getElementsByTagName("COSTO").item(0).getTextContent());
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+tipo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String tipo, double costo){
        
    }
}
