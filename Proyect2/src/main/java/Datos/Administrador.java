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
public class Administrador extends Usuario{
    
    
    @Override
    public void SubirArchivo(Element elementoXML){
        
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            DPI =Long.parseLong(elementoXML.getElementsByTagName("DPI").item(0).getTextContent()) ;
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();    
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, String password){
        
    }
    
}
