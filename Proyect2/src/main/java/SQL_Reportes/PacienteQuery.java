/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_Reportes;

import Archivo.IntervaloTiempo;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class PacienteQuery {

    /**
     * Obtenemos los 5 ultimos 5 resultados delpaciente indicado
     * COlumnas: registro, orden medico, informe, fecha, hora, nombre paciente, nombre medico, nombre Examen, nombre Laboratorista
     * @param codigoPaciente
     * @return
     */
    public ArrayList<String> ReporteUltimosExamenes(String codigoPaciente){
        try {  
            ArrayList<String> listaResultado = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Orden, R.Informe, R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre, L.Nombre "
                    + "FROM RESULTADO R LEFT JOIN PACIENTE P ON R.Paciente=P.Codigo "
                    + "LEFT JOIN LABORATORISTA L ON R.LABORATORISTA=L.Codigo "
                    + " LEFT JOIN MEDICO M ON R.Medico = M.Codigo LEFT JOIN EXAMENES_LABORATORIO E ON R.Examen = E.Codigo WHERE R.Paciente = ? ORDER BY Fecha ASC LIMIT 5";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaResultado.add(resultado.getString("R.No_Registro"));
                listaResultado.add(resultado.getString("R.Orden"));
                listaResultado.add(resultado.getString("R.Informe"));
                listaResultado.add(resultado.getString("R.Fecha"));
                listaResultado.add(resultado.getString("R.Hora"));
                listaResultado.add(resultado.getString("P.Nombre"));
                listaResultado.add(resultado.getString("M.Nombre"));
                listaResultado.add(resultado.getString("E.Nombre"));
                listaResultado.add(resultado.getString("L.Nombre"));
            }
            connection.close();
            return listaResultado;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * reporte que ordena segun la especialidad y un intervalo de tiempo
     * Columnas devueltas:9
     * @param codigoPaciente
     * @param especialidad
     * @param horaInicial
     * @param horaFinal
     * @return
     */
    public ArrayList<String> ReporteExamenTiempoEspecifico(String codigoPaciente,String especialidad, String fechaInicial, String fechaFinal){
        try {  
            ArrayList<String> listaResultado = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Orden, R.Informe, R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre, L.Nombre "
                    + " FROM RESULTADO R LEFT JOIN PACIENTE P ON R.Paciente=P.Codigo "
                    + " LEFT JOIN LABORATORISTA L ON R.LABORATORISTA=L.Codigo "
                    + " LEFT JOIN MEDICO M ON R.Medico = M.Codigo "
                    + " LEFT JOIN EXAMENES_LABORATORIO E ON R.Examen = E.Codigo "
                    + " WHERE R.Paciente = ? AND E.Nombre like ? AND CAST(R.Fecha as date) BETWEEN ? AND ?  ORDER BY Fecha ASC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            statement.setString(2, "%"+especialidad+"%");
            statement.setString(3, fechaInicial);
            statement.setString(4, fechaFinal);
            
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaResultado.add(resultado.getString("R.No_Registro"));
                listaResultado.add(resultado.getString("R.Orden"));
                listaResultado.add(resultado.getString("R.Informe"));
                listaResultado.add(resultado.getString("R.Fecha"));
                listaResultado.add(resultado.getString("R.Hora"));
                listaResultado.add(resultado.getString("P.Nombre"));
                listaResultado.add(resultado.getString("M.Nombre"));
                listaResultado.add(resultado.getString("E.Nombre"));
                listaResultado.add(resultado.getString("L.Nombre"));
            }
            
            
            connection.close();
            return listaResultado;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
        
    /**
     * Devuelve una lista de datos: registro, fecha, hora, nombre paciente, nombre medico, registro titulo, tipo consulta, informe cita
     * COlumnas: 8
     * @param codigoPaciente
     * @return
     */
    public ArrayList<String> ReporteUltimasConsultas(String codigoPaciente){
        try {  
            ArrayList<String> listaResultado = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Fecha_Cita, R.Hora_Cita, P.Nombre, M.Nombre, R.Registro_Titulo, C.Tipo, R.Informe_Cita"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo"
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo"
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo"
                    + " WHERE R.Codigo_Paciente = ? AND R.Cita_Realizada=false ORDER BY R.Fecha_Cita LIMIT 5";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaResultado.add(resultado.getString("R.No_Registro"));
                listaResultado.add(resultado.getString("R.Fecha_Cita"));
                listaResultado.add(resultado.getString("R.Hora_Cita"));
                listaResultado.add(resultado.getString("P.Nombre"));
                listaResultado.add(resultado.getString("M.Nombre"));
                listaResultado.add(resultado.getString("R.Registro_Titulo"));
                listaResultado.add(resultado.getString("C.Tipo"));
                listaResultado.add(resultado.getString("R.Informe_Cita"));
            }
            connection.close();
            return listaResultado;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * DEVUELVE UNA LISTA DE LAS ULTIMAS CONUSLTAS REALIZADAS segun un medico y tiempo en especifico, las columnas son
     * registro, fecha, hora, nombre paciente, nombre medico, registro titulo, tipo consulta, informe cita
     * COlumnas:8
     * @param codigoPaciente
     * @param medico
     * @param horaInicial
     * @param horaFinal
     * @return
     */
    public ArrayList<String> ReporteConsultasTiempoEspecifico(String codigoPaciente,String medico, String fechaInicial, String fechaFinal){
        try {  
            ArrayList<String> listaResultado = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Unimos las tablas de paciente, medico, examen y laboratorista
            //left join con la tabla correspondiente en la columna que son ifuales y a cambio
            //pedimos atributos de la tabla 
            String comando = "SELECT R.No_Registro, R.Fecha_Cita, R.Hora_Cita, P.Nombre, M.Nombre, R.Registro_Titulo, C.Tipo, R.Informe_Cita"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo"
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo"
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo"
                    + " WHERE R.Codigo_Paciente=? AND M.Nombre like ? AND CAST(R.Fecha_Cita as date) BETWEEN ? AND ?  ORDER BY R.Fecha_Cita ASC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            statement.setString(2, "%"+medico+"%");
            statement.setString(3, fechaInicial);
            statement.setString(4, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaResultado.add(resultado.getString("R.No_Registro"));
                listaResultado.add(resultado.getString("R.Fecha_Cita"));
                listaResultado.add(resultado.getString("R.Hora_Cita"));
                listaResultado.add(resultado.getString("P.Nombre"));
                listaResultado.add(resultado.getString("M.Nombre"));
                listaResultado.add(resultado.getString("R.Registro_Titulo"));
                listaResultado.add(resultado.getString("C.Tipo"));
                listaResultado.add(resultado.getString("R.Informe_Cita"));
            }
            connection.close();
            return listaResultado;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
}
