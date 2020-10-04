/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_Reportes;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class OpcionesEspecialesLaboratorista {

    /**
     * Devuelve las pruebas de examenes pendientes de cierto tipo en el cual el laboratorista
     * esta especializado, solo los codigos
     * @param laboratorista
     * @return
     */
    public ArrayList<String> GetLaboratoristaJobs(String laboratorista){
        try {   
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM REGISTRO_EXAMEN WHERE "
                    + " Codigo_Examen=(SELECT Codigo FROM EXAMENES_LABORATORIO WHERE"
                    + " Nombre=(SELECT Examen_trabajo FROM LABORATORISTA WHERE Codigo=(?)))";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, laboratorista);
            ResultSet resultados = statement.executeQuery();
            ArrayList<String> registros = new ArrayList<>();
            while(resultados.next()){
                registros.add(resultados.getString("No_Registro"));
            }
            connection.close();
            return registros;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Clase que con los parametros enviados termina una tupla de la tabla REGISTRO_EXAMEN la elimina 
     * y crea un resultado en base a eso
     * @param codigoExamen
     * @param noRegistro
     * @param informe
     * @param fecha
     * @param hora
     * @param laboratorista
     * @return 
     */
    public boolean CompleteJob(String codigoExamen,String noRegistro,String informe,String fecha,String hora, String laboratorista){
        try {
                String paciente = null;
                String medico = null;
                String examen = null;
                String orden = null;
                //Obtenemos el codigo del paciente, medico, y el codigo del examen que tiene que trabajar
                Connection connection = new Conexion().CreateConnection();
                String comando = "SELECT * FROM REGISTRO_EXAMEN WHERE No_Registro=(?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                //codigo Examen es el codigo en REGISTRO_EXAMENES, examen es el codigo del examen
                statement.setString(1, codigoExamen);
                ResultSet resultados = statement.executeQuery();
                if(resultados.next()){
                    paciente = resultados.getString("Codigo_Paciente");
                    medico = resultados.getString("Codigo_Medico");
                    examen = resultados.getString("Codigo_Examen");
                    orden =  resultados.getString("Orden_Medico");
                }    
                comando = "INSERT INTO RESULTADO (No_Registro,Orden,Informe,Fecha,Hora,Paciente,Medico,Examen,Laboratorista)"
                        + " VALUES(?,?,?,?,?,?,?,?,?)";
                //Lo manejamos como ciclo para colocar varios titulos
                statement = connection.prepareStatement(comando);
                statement.setString(1, noRegistro);
                statement.setString(2, orden);
                statement.setString(3, informe);
                statement.setString(4, fecha);
                statement.setString(5, hora);
                statement.setString(6, paciente);
                statement.setString(7, medico);
                statement.setString(8, examen);
                statement.setString(9, laboratorista);
                statement.executeUpdate();
                //AL SUBIR EL resultado eliminamos su contenido en la tabla registro_examenes
                comando = "DELETE FROM REGISTRO_EXAMEN WHERE No_Registro=(?)";
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigoExamen);
                statement.executeUpdate();
                connection.close();
                return true;
                
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

