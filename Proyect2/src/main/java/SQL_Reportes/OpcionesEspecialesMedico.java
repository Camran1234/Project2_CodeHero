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

/**
 * Clase que permite al medico realizar ciertas acciones como confirmar una cita
 * @author camran1234
 */
public class OpcionesEspecialesMedico {

    /**
     * Confirma una cita para al ingresar un informe que se subira a la base de datos
     * y cambiara el estado de la cita
     * @param registroInforme
     * @param informe
     * @param fecha
     * @param hora
     * @param registroCita
     * @return 
     */
    public boolean ConfirmarCita(String registroInforme, String informe, String fecha, String hora, String registroCita){
        try {
            //Insertamos el informe a la base de datos
            Connection connection = new Conexion().CreateConnection();            
            PreparedStatement statement = null;
            String paciente = null;
            String medico = null;
            String comando = "SELECT Codigo_Paciente, Codigo_Medico FROM REGISTRO_CITAS WHERE No_Registro=?";
            statement = connection.prepareStatement(comando);
            statement.setString(1, registroCita);
            ResultSet resultado = statement.executeQuery();
            //Encontramos el codigo del doctor y del paciente para subirlo a la base de datos
            if(resultado.next()){
                paciente = resultado.getString("Codigo_Paciente");
                medico = resultado.getString("Codigo_Medico");
            }
            
            comando = "INSERT INTO INFORME_MEDICO (No_Registro, Paciente, Medico, Informe, Fecha, Hora) VALUES(?,?,?,?,?,?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1, registroInforme);
            statement.setString(2, paciente);
            statement.setString(3, medico);
            statement.setString(4, informe);
            statement.setString(5, fecha);
            statement.setString(6, hora);
            statement.executeUpdate();
            
            //Insertamos el registro del informe a nuestro registro citas y la marcamos como una cita completada
            comando = "UPDATE REGISTRO_CITAS SET Cita_Realizada=?, Informe_Cita=? WHERE No_Registro=?";
            statement = connection.prepareStatement(comando);
            statement.setBoolean(1, true);
            statement.setString(2, registroInforme);
            statement.setString(3, registroCita);
            statement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
               ex.printStackTrace();
               System.out.println("Registro: "+registroInforme);
               return false;
        }
    }
}
