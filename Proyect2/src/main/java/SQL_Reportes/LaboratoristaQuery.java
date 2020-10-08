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
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class LaboratoristaQuery {

    /**
     * Devuelve una lista de datos para tabla, que contiene los examenes del turno del dia 
     * Columnas: registro, orden medico, hora, fecha, nombre paciente, nombre examen, nombre medico
     * Columnas: 7
     * @param codigoLaboratorista
     * @return
     */
    public ArrayList<String> ReporteExamenesTurno(String codigoLaboratorista){
        try {  
            //Obtenemos los codigos de examenes que tiene que realizar
            ArrayList<String> listaCodigosExamenes = new OpcionesEspecialesLaboratorista().GetLaboratoristaJobs(codigoLaboratorista);
            ArrayList<String> listaExamenes = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los pacientes que mas informes hallan tenido o tambien que ya hallan completado las citas
            String comando = "SELECT R.No_Registro, R.Orden_Medico, R.Hora, R.Fecha, P.Nombre, E.Nombre, M.Nombre FROM REGISTRO_EXAMEN R LEFT JOIN PACIENTE P ON R.Codigo_Paciente"
                    + "=P.Codigo LEFT JOIN EXAMENES_LABORATORIO E ON R.Codigo_Examen=E.Codigo LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo WHERE R.Codigo_Examen= (SELECT Codigo FROM EXAMENES_LABORATORIO WHERE"
                    + " Nombre=(SELECT Examen_trabajo FROM LABORATORISTA WHERE Codigo=(?)))"
                    + "AND CAST(R.Fecha as date) = (SELECT CURRENT_DATE()) GROUP BY R.No_Registro";
            PreparedStatement statement = null;
            //Obtenemos las columnas para dar una lista de datos mas informativa                
            statement = connection.prepareStatement(comando);            
            statement.setString(1, codigoLaboratorista);            
            ResultSet resultado = statement.executeQuery();            
            if(resultado.next()){            
                listaExamenes.add(resultado.getString("R.No_Registro"));                
                listaExamenes.add(resultado.getString("R.Orden_Medico"));                
                listaExamenes.add(resultado.getString("R.Hora"));                
                listaExamenes.add(resultado.getString("R.Fecha"));                
                listaExamenes.add(resultado.getString("P.Nombre"));                
                listaExamenes.add(resultado.getString("E.Nombre"));                
                listaExamenes.add(resultado.getString("M.Nombre"));                
            }
            
            connection.close();
            return listaExamenes;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Lista de datos de los resultados realizados en el dia por el laboratorista indicado, columnas: registro, orden, fecha,
     * hora, nombre paciente, nombre medico, nombre examen
     * Columnas:8
     * @param codigoLaboratorista
     * @return
     */
    public ArrayList<String> ReporteExamenesRealizadosEnDia(String codigoLaboratorista){
        try {  
            //Obtenemos los codigos de examenes que tiene que realizar
            ArrayList<String> listaExamenes = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los resultados realizados por el laboratorista ese mismo dia
            String comando = "SELECT R.No_Registro, R.Orden, R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre"
                    + " FROM RESULTADO R LEFT JOIN PACIENTE P ON R.Paciente = P.Codigo LEFT JOIN MEDICO M ON R.Medico=M.Codigo"
                    + " LEFT JOIN EXAMENES_LABORATORIO E ON R.Examen=E.Codigo WHERE R.Laboratorista = ? "
                    + "AND CAST(R.Fecha as date) = (SELECT CURRENT_DATE())";
            PreparedStatement statement = null;
            //Obtenemos las columnas para dar una lista de datos mas informativa
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoLaboratorista);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){            
                listaExamenes.add(resultado.getString("R.No_Registro"));                
                listaExamenes.add(resultado.getString("R.Orden"));                
                listaExamenes.add(resultado.getString("R.Fecha"));                
                listaExamenes.add(resultado.getString("R.Hora"));
                listaExamenes.add(resultado.getString("P.Nombre"));
                listaExamenes.add(resultado.getString("E.Nombre"));
                listaExamenes.add(resultado.getString("M.Nombre"));    
            }
            connection.close();
            return listaExamenes;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Retorna los resultados obtenidos en los dias
     * @param codigoLaboratorista
     * @return
     */
    public ArrayList<String> ReporteExamenesRealizados(String codigoLaboratorista, String fechaInicio, String fechaFinal){
        try {  
            //Obtenemos los codigos de examenes que tiene que realizar
            ArrayList<String> listaExamenes = new ArrayList<>();
            ArrayList<String> listaDias = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los resultados realizados por el laboratorista ese mismo dia
            String comando = "SELECT Dias_Trabajo FROM LABORATORISTA WHERE Codigo=?";
            PreparedStatement statement = null;
            //Obtenemos las columnas para dar una lista de datos mas informativa
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoLaboratorista);
            ResultSet resultado = statement.executeQuery();
            
            String dias = null;
            
            if(resultado.next()){            
                dias = resultado.getString("Dias_Trabajo");
            }
            
            String[] diasSeparados = dias.split("-");
            //Lo transformamos en numero para que nos acepte la funcion de DAYWEEk en sql
            for(String diaSeparado:diasSeparados){
                switch(diaSeparado){
                    case "Lunes":
                        listaDias.add("2");
                        break;
                    case "Martes":
                        listaDias.add("3");
                        break;
                    case "Miercoles":
                        listaDias.add("4");
                        break;
                    case "Jueves":
                        listaDias.add("5");
                        break;
                    case "Viernes":
                        listaDias.add("6");
                        break;
                    case "Sabado":
                        listaDias.add("7");
                        break;                        
                    case "Domingo":
                        listaDias.add("1");
                        break;
                }
            }
            
            int contadora=0;
            String diaResultado = null;
            //Obtenemos los dias de la semana con el codigo de laboratorista y en cierta fecha
            comando="SELECT DAYOFWEEK(Fecha), R.No_Registro, R.Orden, R.Informe, R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre "
                    + " FROM RESULTADO R LEFT JOIN PACIENTE P ON R.Paciente=P.Codigo "
                    + " LEFT JOIN MEDICO M ON R.Medico = M.Codigo LEFT JOIN EXAMENES_LABORATORIO E ON R.Examen = E.Codigo "
                    + " WHERE R.Laboratorista=? AND CAST(R.Fecha as date) BETWEEN ? AND ?  ORDER BY R.Fecha ASC";
            
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoLaboratorista);
            statement.setString(2, fechaInicio);
            statement.setString(3, fechaFinal);
            resultado = statement.executeQuery();
            
            while(resultado.next()){
                diaResultado = resultado.getString("DAYOFWEEK(Fecha)");
                //Si los dias son similares subimos la lista
                if(listaDias.contains(diaResultado)){
                    listaExamenes.add(resultado.getString("R.No_Registro"));                                
                    listaExamenes.add(resultado.getString("R.Orden"));                                
                    listaExamenes.add(resultado.getString("R.Informe"));                                
                    listaExamenes.add(resultado.getString("R.Fecha"));                                
                    listaExamenes.add(resultado.getString("R.Hora"));                
                    listaExamenes.add(resultado.getString("P.Nombre"));                
                    listaExamenes.add(resultado.getString("E.Nombre"));                
                    listaExamenes.add(resultado.getString("M.Nombre"));    
                }
            }
                        
            connection.close();
            return listaExamenes;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve una lista de las fechas mas ocupadas
     * @param codigoLaboratorista
     * @return
     */
    public ArrayList<String> ReporteFechasMasOcupadas(String codigoLaboratorista){
        try {  
            //Obtenemos los codigos de examenes que tiene que realizar
            ArrayList<String> listaExamenes = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los resultados realizados por el laboratorista ese mismo dia
            String comando="SELECT DAYOFWEEK(Fecha), R.No_Registro, R.Orden, R.Informe, R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre "
                    + " FROM RESULTADO R LEFT JOIN PACIENTE P ON R.Paciente=P.Codigo "
                    + " LEFT JOIN MEDICO M ON R.Medico = M.Codigo LEFT JOIN EXAMENES_LABORATORIO E ON R.Examen = E.Codigo "
                    + " WHERE R.Laboratorista=? GROUP BY R.Fecha  ORDER BY Count(*) DESC LIMIT 10";
            PreparedStatement statement = null;
            //Obtenemos las columnas para dar una lista de datos mas informativa
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoLaboratorista);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){            
                listaExamenes.add(resultado.getString("R.No_Registro"));                                
                listaExamenes.add(resultado.getString("R.Fecha"));                
                listaExamenes.add(resultado.getString("P.Nombre"));
                listaExamenes.add(resultado.getString("E.Nombre"));
                listaExamenes.add(resultado.getString("M.Nombre"));    
            }
            connection.close();
            return listaExamenes;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
}
