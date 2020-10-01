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
public class Informe {
 
    private String codigo;
    private String paciente;
    private String medico;
    private String informe;
    private String fecha;
    private String hora;
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            paciente = elementoXML.getElementsByTagName("PACIENTE").item(0).getTextContent();
            medico = elementoXML.getElementsByTagName("MEDICO").item(0).getTextContent();
            informe = elementoXML.getElementsByTagName("INFORME").item(0).getTextContent();
            fecha = elementoXML.getElementsByTagName("FECHA").item(0).getTextContent();
            hora = elementoXML.getElementsByTagName("HORA").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, paciente, medico, informe, fecha, hora);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean SubirArchivoParametros(String codigo, String paciente, String medico, String informe, String fecha, String hora){
        try {  
                
            Connection connection = new Conexion().CreateConnection();
            String comando = "INSERT INTO INFORME_MEDICO (No_Registro,Paciente,Medico,Informe,Fecha,Hora) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, paciente);
            statement.setString(3,medico);
            statement.setString(4, informe);
            //Nota colocar un codigo de que compruebe que si sea hora y fecha
            statement.setString(5, fecha);
            statement.setString(6, hora);
            statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }    
    }
}
