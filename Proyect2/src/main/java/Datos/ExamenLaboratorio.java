/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class ExamenLaboratorio {
    private String codigo;
    private String nombre;
    private Boolean orden;
    private String descripcion;
    private double costo;
    private String informe;
    
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            orden = Boolean.getBoolean(elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent());
            descripcion = elementoXML.getElementsByTagName("DESCRIPCION").item(0).getTextContent();
            costo = Double.parseDouble(elementoXML.getElementsByTagName("COSTO").item(0).getTextContent());
            informe = elementoXML.getElementsByTagName("INFORME").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, String orden, String descripcion, double costo, String informe){
        
    }
}
