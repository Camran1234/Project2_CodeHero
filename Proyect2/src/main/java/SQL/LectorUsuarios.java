/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase para obtener codigos de usuarios u aspectos a modificar
 * @author camran1234
 */
public class LectorUsuarios {

    /**
     * Funcion que devuelve los medicos de la base
     * de datos
     * @return
     */
    public ArrayList<String> GetCodigoMedicos(){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Codigo FROM MEDICO";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(resultado.getString("Codigo"));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
    /**
     * Retorna una lista con los codigos de los pacientes en la base de datos
     * @return 
     */
    public ArrayList<String> GetCodigoPaciente(){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Codigo FROM PACIENTE";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(resultado.getString("Codigo"));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
    
    /**
     * Funcion que devuelve los codigos de los laboratoristas almacenados en la base de datos
     * @return 
     */
    public ArrayList<String> GetCodigoLaboratoristas(){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Codigo FROM LABORATORISTA";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(resultado.getString("Codigo"));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
    
    /**
     * Funcion que retorna los codigos de las consultas almacenados en la base de datos
     * @return 
     */
    public ArrayList<String> GetCodigoConsultas(){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Codigo FROM CONSULTA";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(Integer.toString(resultado.getInt("Codigo")));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
    
    /**
     * FUncion que devuelve los codigos de los examenes de laboratorio
     * almacenados en la base de datos
     * @return
     */
    public ArrayList<String> GetCodigoExamenes(){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM EXAMENES_LABORATORIO";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(resultado.getString("Codigo"));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
    
    /**
     * Retorna los codigos de las citas donde no se han realizado y el encargado es el codigo del doctor indicado
     * @return 
     */
    public ArrayList<String> GetCodigoCitas(String codigoMedico){
        try {  
            ArrayList<String> codigos = new ArrayList<>();
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT No_Registro FROM REGISTRO_CITAS WHERE Cita_Realizada=false AND Codigo_Medico=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoMedico);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                codigos.add(resultado.getString("No_Registro"));
            }
            return codigos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }   
    }
}
