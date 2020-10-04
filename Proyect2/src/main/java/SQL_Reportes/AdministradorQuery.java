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
public class AdministradorQuery {

    /**
     * Devuelve una lista con los medicos que mayor cantidad de reportes hicieron, almenos 10
     * doctores, coumnas, cantidad, codigo medico, nombre medico, fecha realizada
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> ReporteMayorCantidadReportesMedico(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los pacientes que mas informes hallan tenido o tambien que ya hallan completado las citas
            String comando = "SELECT COUNT(*), R.Codigo_Medico, M.Nombre, R.Fecha_Cita FROM REGISTRO_CITAS R "
                    + " LEFT JOIN MEDICO M ON R.Codigo_Medico = M.Codigo "
                    + "WHERE R.Cita_Realizada=true AND CAST(R.Fecha_cita as date) BETWEEN ? AND ? "
                    + "GROUP BY R.Codigo_Medico ORDER BY COUNT(*) LIMIT 10";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("COUNT(*)"));
                listaCitas.add(resultado.getString("R.Codigo_Medico"));
                listaCitas.add(resultado.getString("M.Nombre"));
                listaCitas.add(resultado.getString("R.Fecha_Cita"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    
    /**
     * Codigo, nombre medico y total de las sumas ya realizadas
     * 3 columnas
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> ReporteIngresoDoctor(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos de primero la cantidad que se repite cada 
            String comando = "SELECT Count(*) AS Cantidad, R.Codigo_Medico AS Codigo_Medico, M.Nombre AS Nombre_Medico, (Count(*)*C.Costo) AS Total "
                    + " FROM REGISTRO_CITAS R LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo "
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta= C.Codigo  "
                    + " WHERE Cita_Realizada=true AND CAST(R.Fecha_Cita as date) BETWEEN ? AND ? GROUP BY Registro_Titulo ORDER BY COUNT(*)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            Double total=0.00;
            String codigoMedicoAuxiliar = "";
            String codigoMedicoActual;
            Boolean primeraVez = true;
            while(resultado.next()){
                //Codigo para sumar los totales de los mismos nombres
                codigoMedicoActual = resultado.getString("Codigo_Medico");
                if(codigoMedicoAuxiliar.equalsIgnoreCase(codigoMedicoActual) == true){
                    total += resultado.getDouble("Total");
                }else{                    
                    if(primeraVez){
                        listaCitas.add(resultado.getString("Codigo_Medico"));
                        listaCitas.add(resultado.getString("Nombre_Medico"));
                        total += resultado.getDouble("Total");
                        primeraVez=false;
                    }else{
                        listaCitas.add(Double.toString(total));
                        total = 0.00;
                        total += resultado.getDouble("Total");
                        listaCitas.add(resultado.getString("Codigo_Medico"));
                        listaCitas.add(resultado.getString("Nombre_Medico"));
                    }
                    codigoMedicoAuxiliar = codigoMedicoActual;
                }
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve los medicos que menos citas han sido solicitado o han completado en general, en un intervalo de tiempo
     * Lanza un maximo de 5 docs,
     * Columnas: Codigo, Nombre, Veces Solicitado
     * Columnas:3
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> ReporteMenorConcurrenciaDoctoresCitas(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los pacientes que mas informes hallan tenido o tambien que ya hallan completado las citas
            String comando = "SELECT R.Codigo_Medico AS Codigo, M.Nombre AS Nombre, COUNT(*) as Veces_Solicitado "
                    + " FROM REGISTRO_CITAS R LEFT JOIN MEDICO M ON R.Codigo_Medico=M.Codigo "
                    + " WHERE CAST(R.Fecha_Cita as Date) BETWEEN ? AND ? "
                    + " GROUP BY Codigo_Medico ORDER BY COUNT(*) ASC LIMIT 5";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("Codigo"));
                listaCitas.add(resultado.getString("Nombre"));
                listaCitas.add(resultado.getString("Veces_Solicitado"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve los examenes mas utilizados en un rango de fecha usados en las tablas registro_examenes y resultados
     * Columnas: Cantidad, Examen, Codigo
     * Columnas: 3
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> ReporteExamenesMayoresDemandados(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos los pacientes que mas informes hallan tenido o tambien que ya hallan completado las citas
            String comando = "SELECT COUNT(*) AS Cantidad,M.Codigo_Examen, E.Nombre FROM"
                    + "(SELECT Codigo_Examen AS Codigo_Examen FROM REGISTRO_EXAMEN WHERE"
                    + " CAST(Fecha as date) BETWEEN ? ADN ? UNION ALL "
                    + " SELECT Examen AS Codigo_Examen FROM RESULTADO WHERE CAST(Fecha as date) BETWEEN ? AND ?) AS M "
                    + " LEFT JOIN EXAMENES_LABORATORIO E ON M.Codigo_Examen=E.Codigo"
                    + " GROUP BY M.Codigo_Examen ORDER BY COUNT(*) DESC";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            statement.setString(3, fechaInicio);
            statement.setString(4, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("CANTIDAD"));
                listaCitas.add(resultado.getString("Codigo"));
                listaCitas.add(resultado.getString("Examen"));
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Obtiene los medicos que han demandado mayor cantidad de examenes y colocar 3 de los mas pedidos en cierta fecha
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> ReporteMedicoMayorExamenesDemandados(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Dividiremos esta parte en dos comandos una para conseguir los codigos de los medicos, nombres y cantidad de examenes que escogieron 
            // y la otra sera los examenes que manejo ese medico
            String comando = "SELECT COUNT(*) AS CANTIDAD, M.Codigo as CODIGO, D.Nombre as NOMBRE "
                    + " FROM (SELECT Codigo_Medico AS Codigo FROM REGISTRO_EXAMEN WHERE Orden_Medico!=? UNION ALL SELECT Medico AS Codigo FROM RESULTADO WHERE Orden!=?) AS M "
                    + " LEFT JOIN MEDICO D ON M.Codigo = D.Codigo GROUP BY Codigo ORDER BY COUNT(*)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, "");
            statement.setString(2, "");
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                String codigo = resultado.getString("CODIGO");
                String codigoExamen = "";
                String nombreExamen = "";
                listaCitas.add(resultado.getString("CANTIDAD"));
                listaCitas.add(codigo);
                listaCitas.add(resultado.getString("NOMBRE"));
                
                String comando2 = "SELECT M.EXAMEN, E.Nombre "
                        + " FROM (SELECT Codigo_Examen AS EXAMEN FROM REGISTRO_EXAMEN WHERE Codigo_Medico=? AND CAST(Fechas as date) BETWEEN ? AND ? UNION"
                        + " SELECT Examen FROM RESULTADO WHERE Medico=? AND CAST(Fechas as date) BETWEEN ? AND ?)"
                        + " AS M LEFT JOIN EXAMENES_LABORATORIO E ON M.EXAMEN=E.Codigo LIMIT 3";
                PreparedStatement statement2 = connection.prepareStatement(comando2);
                statement2.setString(1, codigo);
                statement2.setString(2, fechaInicio);
                statement2.setString(3, fechaFinal);
                statement2.setString(4, codigo);
                statement2.setString(5, fechaInicio);
                statement2.setString(6, fechaFinal);
                ResultSet resultado2 = statement2.executeQuery();
                while(resultado2.next()){
                    codigoExamen += resultado2.getString("M.EXAMEN") + "-";
                    nombreExamen += resultado2.getString("E.Nombre") + "-";
                }
                listaCitas.add(codigoExamen);
                listaCitas.add(nombreExamen);
                codigoExamen="";
                nombreExamen="";
            }  
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Retorna los ingresos generados por cada paciente que ha pedido y completado una cita
     * @param fechaInicio
     * @param fechaFinal
     * @return
     */
    public ArrayList<String> reporteIngresosGeneradoPacientes(String fechaInicio, String fechaFinal){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            //Obtenemos de primero la cantidad que se repite cada 
            String comando = " SELECT COUNT(*) AS CANTIDAD, R.Codigo_Paciente AS Codigo_Paciente, P.Nombre AS Nombre_Paciente, (Count(*)*C.Costo) AS Total 4"
                    + " FROM REGISTRO_CITAS R LEFT JOIN PACIENTE P ON R.Codigo_Paciente=P.Codigo "
                    + " LEFT JOIN CONSULTA C ON R.Registro_Consulta = C.Codigo "
                    + " WHERE Cita_Realizada=true AND CAST(R.Fecha_Cita as date) BETWEEN ? AND ? "
                    + " GROUP BY Registro_Titulo ORDER BY COUNT(*)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            ResultSet resultado = statement.executeQuery();
            Double total=0.00;
            String codigoMedicoAuxiliar = "";
            String codigoMedicoActual;
            Boolean primeraVez = true;
            while(resultado.next()){
                //Codigo para sumar los totales de los mismos nombres
                codigoMedicoActual = resultado.getString("Codigo_Paciente");
                if(codigoMedicoAuxiliar.equalsIgnoreCase(codigoMedicoActual) == true){
                    total += resultado.getDouble("Total");
                }else{                    
                    if(primeraVez){
                        listaCitas.add(resultado.getString("Codigo_Paciente"));
                        listaCitas.add(resultado.getString("Nombre_Paciente"));
                        total += resultado.getDouble("Total");
                        primeraVez=false;
                    }else{
                        listaCitas.add(Double.toString(total));
                        total = 0.00;
                        total += resultado.getDouble("Total");
                        listaCitas.add(resultado.getString("Codigo_Paciente"));
                        listaCitas.add(resultado.getString("Nombre_Paciente"));
                    }
                    codigoMedicoAuxiliar = codigoMedicoActual;
                }
            }
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    
}
