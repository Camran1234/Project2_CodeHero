/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author camran1234
 */
public class RegistroExamen {
    private String noRegistro;
    private String ordenMedico;
    private String hora;
    private String fecha;
    private String paciente;
    private String examen;
    private String medico;
    
    /**
     * Sube un registro de un examen con los parametros enviados
     * @param noRegistro
     * @param ordenMedico
     * @param hora
     * @param fecha
     * @param paciente
     * @param examen
     * @param medico
     * @return
     */
    public boolean SubirArchivoParametros(String noRegistro, String ordenMedico, String hora,
            String fecha, String paciente, String examen, String medico){
        try {  
            Boolean permisoParaSubir = true;
            //Corroboramos el formato del informe en el examen aprovechamos
            //este codigo para comprobar que si era necesario el requerimiento de examen
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM EXAMENES_LABORATORIO WHERE CODIGO=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, examen);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                Boolean requerimiento = resultado.getBoolean("Requerimiento_Orden");
                if((requerimiento == true) && (ordenMedico==null || ordenMedico.equalsIgnoreCase(""))){
                    permisoParaSubir = false;
                }                
            }
            
            //Ingresamos el examen
            if(permisoParaSubir==true){
                connection = new Conexion().CreateConnection();
                comando = "INSERT INTO REGISTRO_EXAMEN (No_Registro,Orden_Medico,Hora,Fecha,Codigo_Paciente,Codigo_Examen,Codigo_Medico) VALUES (?,?,?,?,?,?,?)";
                statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, noRegistro);
                statement.setString(2, ordenMedico);
                statement.setString(3, hora);
                statement.setString(4, fecha);
                statement.setString(5, paciente);
                statement.setString(6, examen);
                statement.setString(7, medico);
                statement.executeUpdate();
            }else{
                return false;
            }
            
            connection.close();
            return true;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return false;
        }    
    }
}
