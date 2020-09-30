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
            this.SubirArchivoParametros(codigo, nombre, DPI, password);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, String password){
        try {
                Connection connection = new Conexion().CreateConnection();
                String comando = "INSERT INTO ADMINISTRADOR (Codigo,Nombre,DPI,Password) VALUES (?,?,?,?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.setString(2, nombre);
                statement.setString(3, Long.toString(DPI));
                statement.setString(4, password);
                statement.executeUpdate();
                System.out.println("Subido");
                connection.close();
                //Ssolo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
            } catch (SQLException ex) {
                System.out.println("ERROR EN ADMIN: "+codigo);
                ex.printStackTrace();
            }
    }
    
}
