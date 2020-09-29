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
public class Resultado {
    
    private String codigo;
    private String paciente;
    private String medico;
    private String examen;
    private String laboratorista;
    private String orden;
    private String informe;
    private String fecha;
    private String hora;
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            paciente = elementoXML.getElementsByTagName("PACIENTE").item(0).getTextContent();
            medico = elementoXML.getElementsByTagName("MEDICO").item(0).getTextContent();
            examen = elementoXML.getElementsByTagName("EXAMEN").item(0).getTextContent();
            laboratorista = elementoXML.getElementsByTagName("LABORATORISTA").item(0).getTextContent();
            orden = elementoXML.getElementsByTagName("ORDEN").item(0).getTextContent();
            informe = elementoXML.getElementsByTagName("INFORME").item(0).getTextContent();
            fecha = elementoXML.getElementsByTagName("FECHA").item(0).getTextContent();
            hora = elementoXML.getElementsByTagName("HORA").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public void SubirArchivoParametros(String codigo, String paciente, String medico, String examen,
            String laboratorista, String orden, String informe, String fecha, String hora){
        
    }
}
