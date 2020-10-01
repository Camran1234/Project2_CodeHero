/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private String registro;
    
    @Override
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            registro = elementoXML.getElementsByTagName("REGISTRO").item(0).getTextContent();
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
                    System.out.println(elementosNode.getElementsByTagName("DIA").item(indexNodo).getTextContent());
                }
            }
            trabajo = elementoXML.getElementsByTagName("TRABAJOF").item(0).getTextContent();
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, nombre, DPI, telefono, examen, correo, diasTrabajo, trabajo, password, registro);
            diasTrabajo = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String examen, String correo,ArrayList<String> diasTrabajo, String trabajo, String password, String registro){
        try {
            String dias="";
                for(int indexArray=0;indexArray<diasTrabajo.size();indexArray++){
                    dias += diasTrabajo.get(indexArray) + "-";
                }
            
                Connection connection = new Conexion().CreateConnection();
                String comando = "INSERT INTO LABORATORISTA (Codigo,Nombre,Password,Numero_Registro_Ministerio_Salud,DPI,"
                        + "Telefono,Examen_trabajo,Dias_Trabajo,Correo_Electronico,Fecha_Inicio) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.setString(2, nombre);
                statement.setString(3, password);
                statement.setString(4, registro);
                statement.setString(5, Long.toString(DPI));
                statement.setString(6, Integer.toString(telefono));
                statement.setString(7, examen);
                statement.setString(8, dias);
                statement.setString(9, correo);
                statement.setString(10, trabajo);
                statement.executeUpdate();
                return true;
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
            return false;
        }
    }
}
