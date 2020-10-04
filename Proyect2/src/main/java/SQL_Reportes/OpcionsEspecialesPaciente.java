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
public class OpcionsEspecialesPaciente {

    private ArrayList<String> resultados = new ArrayList<>();
    private ArrayList<String> citas = new ArrayList<>();
    /**
     * 
     * @param codigo
     * @return 
     */
    public boolean EstablecerHistorialMedico(String codigo){

        try {  
            resultados = new ArrayList<>();
            citas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT R.Fecha, R.Hora, P.Nombre, M.Nombre, E.Nombre, L.Nombre"
                    + " FROM RESULTADO R INNER JOIN PACIENTE P ON R.Paciente=P.Codigo LEFT JOIN MEDICO M ON R.Medico=M.Codigo"
                    + " INNER JOIN EXAMENES_LABORATORIO E ON R.Examen = E.Codigo INNER JOIN LABORATORISTA L ON R.Laboratorista = L.Codigo WHERE R.Paciente=(?) "
                    + "ORDER BY CONVERT(R.Fecha, date) desc";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                resultados.add(resultado.getString("R.Fecha"));
                resultados.add(resultado.getString("R.Hora"));
                resultados.add(resultado.getString("P.Nombre"));
                resultados.add(resultado.getString("M.Nombre"));
                resultados.add(resultado.getString("E.Nombre"));
                resultados.add(resultado.getString("L.Nombre"));
            }
            
            //Obtenemos las citas ya realizadas
            comando ="SELECT Fecha_Cita, Hora_Cita, P.Nombre, M.Nombre, T.Especialidad, C.Tipo "
                    + "FROM REGISTRO_CITAS RC INNER JOIN PACIENTE P ON RC.Codigo_Paciente=P.Codigo "
                    + "INNER JOIN MEDICO M ON RC.Codigo_Medico=M.Codigo LEFT JOIN TITULO T ON RC.Registro_Titulo=T.No_Registro "
                    + "LEFT JOIN CONSULTA C ON RC.Registro_Consulta=C.Codigo WHERE RC.Codigo_Paciente=(?) AND RC.Cita_Realizada=true "
                    + "ORDER BY CONVERT(Fecha_Cita, date) desc";
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            resultado = statement.executeQuery();
            while(resultado.next()){
                citas.add(resultado.getString("RC.Fecha_Cita"));
                citas.add(resultado.getString("RC.Hora_Cita"));
                citas.add(resultado.getString("P.Nombre"));
                citas.add(resultado.getString("M.Nombre"));
                citas.add(resultado.getString("T.Especialidad"));
                citas.add(resultado.getString("C.Tipo"));
            }
            connection.close();
            return true;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return false;
        }
    }

    /**
     * Obtiene una lista de datos de citas pendientes con las columnas no registro, fecha cita, hora cita
     * , nombre medico, tipo consulta 
     * Numero Columnas: 5
     * @param codigoPaciente
     * @return
     */
    public ArrayList<String> GetCitasPendientes(String codigoPaciente){
        try {  
            ArrayList<String> listaCitas = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT RC.No_Registro, RC.Fecha_Cita, RC.Hora_Cita, M.Nombre, C.Tipo"
                    + "  FROM REGISTRO_CITAS RC LEFT JOIN CONSULTA C ON RC.Registro_Consulta = C.Codigo"
                    + " LEFT JOIN MEDICO M ON RC.Codigo_Medico=M.Codigo WHERE RC.Codigo_Paciente = ? AND RC.Cita_Realizada=false";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaCitas.add(resultado.getString("RC.No_Registro"));
                listaCitas.add(resultado.getString("RC.Fecha_Cita"));
                listaCitas.add(resultado.getString("RC.Hora_Cita"));
                listaCitas.add(resultado.getString("M.Nombre"));
                listaCitas.add(resultado.getString("C.Tipo"));
            }
            
            
            connection.close();
            return listaCitas;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve una lista de examenes que estan pendientes de la tabla REGISTRO_EXAMEN 
     * donde se busca por el codigo del paciente, cabe mencionar que devolvera las columnas
     * no_registro, orden del medico, hora, fecha, nombre examen, nombre medico
     * TOtal Columnas: 6
     * @param codigoPaciente
     * @return
     */
    public ArrayList<String> GetExamenesPendientes(String codigoPaciente){
        try {  
            ArrayList<String> listaExamenes = new ArrayList<>();
            //Obtenemos los resultados
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT RE.No_Registro, RE.Orden_Medico, RE.Hora, RE.Fecha, E.Nombre, M.Nombre"
                    + " FROM REGISTRO_EXAMEN RE LEFT JOIN MEDICO M ON RE.Codigo_Medico = M.Codigo "
                    + " LEFT JOIN EXAMENES_LABORATORIO E ON RE.Codigo_Examen = E.Codigo WHERE RE.Codigo_Paciente=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigoPaciente);
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                listaExamenes.add(resultado.getString("RE.No_Registro"));
                listaExamenes.add(resultado.getString("RE.Orden_Medico"));
                listaExamenes.add(resultado.getString("RE.Hora"));
                listaExamenes.add(resultado.getString("RE.Fecha"));
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
     * Devuelve en una lista con todos los datos de las citas del paciente, el orden es:
     * fecha, hora, paciente, medico, titulo, consulta
     * @return 
     */
    public ArrayList<String> GetCitas(){
        return citas;
    }
    /**
     * Devuelve una lista de los resultados con todos los datos del paciente, el orden es:
     * fecha, hora, paciente,medico, examen, laboratorista
     * @return 
     */
    public ArrayList<String> GetResultados(){
        return resultados;
    }
    
    
}
