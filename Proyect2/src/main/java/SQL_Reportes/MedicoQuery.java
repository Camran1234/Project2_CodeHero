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
public class MedicoQuery {

    /**
     * Devuelve una lista de las citas agendadas del doctor en un intervalo de tiempo de fechas
     * las columnas son
     * registro, fecha, hora, nombre paciente, nombre medico, registro titulo, tipo consulta, informe cita
     * COlumnas:8
     * @param codigoMedico
     * @param horaInicial
     * @param horaFinal
     * @return
     */
    public ArrayList<String> ReporteCitasAgendadas(String codigoMedico, String horaInicial, String horaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Fecha_Cita, R.Hora_Cita, P.Nombre, M.Nombre, R.Registro_Titulo, C.Tipo, R.Informe_Cita"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo"
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo"
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo"
                    + " WHERE R.Codigo_Medico= ? AND R.Cita_Realizada=false AND CAST(R.Fecha_Cita as date) BETWEEN ? AND ?  ORDER BY R.Fecha_Cita ASC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoMedico);
            statement.setString(2, horaInicial);
            statement.setString(3, horaFinal);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("R.No_Registro"));
                listaCitas.add(resultado.getString("R.Fecha_Cita"));
                listaCitas.add(resultado.getString("R.Hora_Cita"));
                listaCitas.add(resultado.getString("P.Nombre"));
                listaCitas.add(resultado.getString("M.Nombre"));
                listaCitas.add(resultado.getString("R.Registro_Titulo"));
                listaCitas.add(resultado.getString("C.Tipo"));
                listaCitas.add(resultado.getString("R.Informe_Cita"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * LIstado que devuelve las citas del dia del doc
     * las columnas son
     * registro, fecha, hora, nombre paciente, nombre medico, registro titulo, tipo consulta, informe cita
     * columnas:8
     * @param codigoMedico
     * @return
     */
    public ArrayList<String> ReporteCitasDia(String codigoMedico){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Fecha_Cita, R.Hora_Cita, P.Nombre, M.Nombre, R.Registro_Titulo, C.Tipo, R.Informe_Cita"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo"
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo"
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo"
                    + " WHERE R.Codigo_Medico= ? AND R.Cita_Realizada=false AND CAST(R.Fecha_Cita as date) = (SELECT CURRENT_DATE()) ORDER BY R.Hora_Cita ASC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoMedico);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("R.No_Registro"));
                listaCitas.add(resultado.getString("R.Fecha_Cita"));
                listaCitas.add(resultado.getString("R.Hora_Cita"));
                listaCitas.add(resultado.getString("P.Nombre"));
                listaCitas.add(resultado.getString("M.Nombre"));
                listaCitas.add(resultado.getString("R.Registro_Titulo"));
                listaCitas.add(resultado.getString("C.Tipo"));
                listaCitas.add(resultado.getString("R.Informe_Cita"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve 3 de los codigos del paciente y su nombre de quienes han tenido mayor cantidad de informes
     * o citas realizadas
     * @param codigoMedico
     * @param horaInicial
     * @param horaFinal
     * @return 
     */
    public ArrayList<String> ReportePacientesConMayorInformes(String codigoMedico, String horaInicial, String horaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los pacientes que mas informes hallan tenido o tambien que ya hallan completado las citas
            String comando = "SELECT R.No_Registro, R.Fecha_Cita, R.Hora_Cita, P.Nombre, M.Nombre, R.Registro_Titulo, C.Tipo, R.Informe_Cita, R.Codigo_Paciente"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo"
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo"
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo"
                    + " WHERE R.Cita_Realizada=true AND CAST(Fecha_Cita as date) BETWEEN ? AND ? GROUP BY Codigo_Paciente ORDER BY COUNT(*) LIMIT 3";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoMedico);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("R.Codigo_Paciente"));
                listaCitas.add(resultado.getString("P.Nombre"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    
    
    
}
