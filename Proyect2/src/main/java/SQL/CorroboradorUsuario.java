/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Clase para comprobar usuarios y contrasenas
 * @author camran1234
 */
public class CorroboradorUsuario {
    
    public String CheckLogIn(String usuario, String password){
        
        try {  
            //Comprobaremos en que tabla se encuentra el codigo el usuario
            //para devolver un valor que indicara el usuario que esta usando
            //corroboracion para administrador
            String comprobadorCodigo=null;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM ADMINISTRADOR WHERE Codigo=(?) AND Password=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, usuario);
            statement.setString(2, password);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Codigo");
                if(comprobadorCodigo.equalsIgnoreCase("") || comprobadorCodigo==null){
                    return "ADMINISTRADOR";
                }
            }
            
            //Corroboracion para medico
            comando = "SELECT * FROM MEDICO WHERE Codigo=(?) AND Password=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, usuario);
            statement.setString(2, password);
            resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Codigo");
                if(comprobadorCodigo.equalsIgnoreCase("") || comprobadorCodigo==null){
                    return "MEDICO";
                }
            }
            
            //Corroboracion para laboratorista
            comando = "SELECT * FROM LABORATORISTA WHERE Codigo=(?) AND Password=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, usuario);
            statement.setString(2, password);
            resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Codigo");
                if(comprobadorCodigo.equalsIgnoreCase("") || comprobadorCodigo==null){
                    return "LABORATORISTA";
                }
            }
            
            //Corroboracion Paciente
            comando = "SELECT * FROM PACIENTE WHERE Codigo=(?) AND Password=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, usuario);
            statement.setString(2, password);
            resultado = statement.executeQuery();
            if(resultado.next()){
                comprobadorCodigo = resultado.getString("Codigo");
                if(comprobadorCodigo.equalsIgnoreCase("") || comprobadorCodigo==null){
                    return "PACIENTE";
                }
            }
            
            
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
               ex.printStackTrace();
        }   
        return null;
    }
}
