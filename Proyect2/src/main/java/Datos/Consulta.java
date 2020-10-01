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
            this.SubirArchivoParametros(tipo, costo);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+tipo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean SubirArchivoParametros(String tipo, double costo){
       try {
            
                Connection connection = new Conexion().CreateConnection();
                String comando = "INSERT INTO CONSULTA (Tipo,Costo) VALUES (?,?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, tipo);
                statement.setDouble(2, costo);
                statement.executeUpdate();
                connection.close();
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
}
