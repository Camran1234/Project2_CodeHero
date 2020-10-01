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
import javax.swing.JOptionPane;
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
    
    
    /**
     * Carga los elementos encontrado en el elemento XML
     * @param elementoXML 
     */
    public void SubirArchivo(Element elementoXML){
        try {
            String tipoOrden;
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            
            tipoOrden = elementoXML.getElementsByTagName("ORDEN").item(0).getTextContent();
            if(tipoOrden.equalsIgnoreCase("TRUE")){
                orden = true;
            }else if (tipoOrden.equalsIgnoreCase("FALSE")){
                orden = false;
            }
            
            descripcion = elementoXML.getElementsByTagName("DESCRIPCION").item(0).getTextContent();
            costo = Double.parseDouble(elementoXML.getElementsByTagName("COSTO").item(0).getTextContent());
            informe = elementoXML.getElementsByTagName("INFORME").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, nombre, orden, descripcion, costo, informe);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Sube los archivos a la base de datos
     * @param codigo
     * @param nombre
     * @param orden
     * @param descripcion
     * @param costo
     * @param informe
     */
    public boolean SubirArchivoParametros(String codigo, String nombre, Boolean orden, String descripcion, double costo, String informe){
        try {  
                
            Connection connection = new Conexion().CreateConnection();
            String comando = "INSERT INTO EXAMENES_LABORATORIO (Codigo,Nombre,Requerimiento_Orden,Descripcion,Costo,Informe) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setBoolean(3,orden);
            statement.setString(4, descripcion);
            statement.setDouble(5, costo);
            statement.setString(6, informe);
            statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return false;
        }    
    }
}
