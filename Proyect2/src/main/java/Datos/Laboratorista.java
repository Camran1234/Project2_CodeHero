/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class Laboratorista extends Usuario{
    
    private int telefono;
    private String examen;
    private String correo;
    private ArrayList<String> diasTrabajo = new ArrayList<>();
    private String trabajo;
    
    @Override
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            DPI =Long.parseLong(elementoXML.getElementsByTagName("DPI").item(0).getTextContent()) ;
            telefono = Integer.parseInt(elementoXML.getElementsByTagName("TELEFONO").item(0).getTextContent());
            examen = elementoXML.getElementsByTagName("EXAMEN").item(0).getTextContent();
            correo = elementoXML.getElementsByTagName("CORREO").item(0).getTextContent();
            
            //Obtenemos los elementos en forma de nodoHijo con el tag Trabajo, entonces
            // al obtener los elementos transformamos los atributos a String para agregarlos mas tarde
            NodeList childNodes = (NodeList) elementoXML.getElementsByTagName("TRABAJO");
            Element elementosNode = (Element) childNodes.item(0);
            for(int indexNodo=0;indexNodo<elementosNode.getElementsByTagName("DIA").getLength();indexNodo++){
                if(elementosNode.getElementsByTagName("DIA").item(indexNodo).getTextContent() != null){
                    diasTrabajo.add(elementosNode.getElementsByTagName("DIA").item(indexNodo).getTextContent());
                }
            }
            trabajo = elementoXML.getElementsByTagName("TRABAJOF").item(0).getTextContent();
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String examen, String correo, String trabajo, String password){
        
    }
}
