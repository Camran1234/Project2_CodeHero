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
public class Medico extends Usuario {
    private String colegiado;
    private int telefono;
    private ArrayList<String> titulos;
    private String horarioInicio;
    private String horarioFinal;
    
    @Override
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            DPI =Long.parseLong(elementoXML.getElementsByTagName("DPI").item(0).getTextContent()) ;
            telefono = Integer.parseInt(elementoXML.getElementsByTagName("TELEFONO").item(0).getTextContent());
            colegiado = elementoXML.getElementsByTagName("COLEGIADO").item(0).getTextContent();
            
            //Obtenemos los elementos en forma de nodoHijo con el tag Especialidad, entonces
            // al obtener los elementos transformamos los atributos a String para agregarlos mas tarde
            NodeList childNodes = (NodeList) elementoXML.getElementsByTagName("ESPECIALIDAD");
            Element elementosNode = (Element) childNodes.item(0);
            for(int indexNodo=0;indexNodo<elementosNode.getElementsByTagName("TITULO").getLength();indexNodo++){
                if(elementosNode.getElementsByTagName("TITULO").item(indexNodo).getTextContent() != null){
                    titulos.add(elementosNode.getElementsByTagName("TITULO").item(indexNodo).getTextContent());
                }
            }
            
            //Ahora obtendremos los datos para la hora inicial y final
            childNodes = (NodeList) elementoXML.getElementsByTagName("HORARIO");
            elementosNode = (Element) childNodes.item(0);
            
            horarioInicio = elementosNode.getElementsByTagName("INICIO").item(0).getTextContent();
            horarioFinal = elementosNode.getElementsByTagName("FIN").item(0).getTextContent();
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String colegiado,
            String horarioInicio, String horarioFInal, String password){
        
    }
}
