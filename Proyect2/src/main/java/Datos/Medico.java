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
public class Medico extends Usuario {
    private String colegiado;
    private int telefono;
    private ArrayList<String> titulos = new ArrayList<>();
    private String horarioInicio;
    private String horarioFinal;
    private String correoElectronico;
    private String fechaInicio;
    
    @Override
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            DPI =Long.parseLong(elementoXML.getElementsByTagName("DPI").item(0).getTextContent()) ;
            telefono = Integer.parseInt(elementoXML.getElementsByTagName("TELEFONO").item(0).getTextContent());
            colegiado = elementoXML.getElementsByTagName("COLEGIADO").item(0).getTextContent();
            fechaInicio = elementoXML.getElementsByTagName("TRABAJO").item(0).getTextContent();
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
            correoElectronico = elementoXML.getElementsByTagName("CORREO").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, nombre, DPI, telefono, colegiado, titulos, horarioInicio, horarioFinal, password,correoElectronico, fechaInicio);
            titulos = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String colegiado,
            ArrayList<String> titulos,String horarioInicio, String horarioFinal, String password, String correoElectronico, String fechaInicio){
        try {
                Connection connection = new Conexion().CreateConnection();
                String comando = "INSERT INTO MEDICO (Codigo,Nombre,Password,Numero_Colegiado,DPI,"
                        + "Telefono,Correo_Electronico,Horario_Entrada,Horario_Salida,Fecha_Inicio) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.setString(2, nombre);
                statement.setString(3, password);
                statement.setString(4, colegiado);
                statement.setString(5, Long.toString(DPI));
                statement.setString(6, Integer.toString(telefono));
                statement.setString(7, correoElectronico);
                statement.setString(8, horarioInicio);
                statement.setString(9, horarioFinal);
                statement.setString(10, fechaInicio);
                statement.executeUpdate();
                
                comando = "INSERT INTO TITULO (Codigo_Medico,Especialidad) VALUES (?,?)";
                
                //Lo manejamos como ciclo para colocar varios titulos
                for(int indexTitulo=0;indexTitulo<titulos.size();indexTitulo++){
                    statement = null;
                    statement = connection.prepareStatement(comando);
                    statement.setString(1, codigo);
                    statement.setString(2, titulos.get(indexTitulo));
                    statement.executeUpdate();
                }
                connection.close();
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
        }
    }
}
