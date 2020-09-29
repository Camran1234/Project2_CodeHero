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
public class Cita {
    
    private String codigo;
    private String paciente;
    private String especialidad;
    private String fecha;
    private String hora;
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            paciente = elementoXML.getElementsByTagName("PACIENTE").item(0).getTextContent();
            especialidad = elementoXML.getElementsByTagName("ESPECIALIDAD").item(0).getTextContent();
            fecha = elementoXML.getElementsByTagName("FECHA").item(0).getTextContent();
            hora = elementoXML.getElementsByTagName("HORA").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String paciente, String especialidad, String fecha, String hora){
        
    }
}
