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
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class OpcionesBuscarMedico {

    /**
     *Devuelve una lista con los datos del medico buscado a base de su nombre, devolviendo Codigo, nombre
     * , Correo_Electronico, Horario Entrada, Horario Salida, Fecha Inicio, Especialidades
     *Columnas en total: 7
     * @param nombre
     * @return
     */
    public ArrayList<String> SearchMedicByName (String nombre){
        try {
            ArrayList<String> registros = new ArrayList<>();
            //Variable que permitira copiar una sola tupla de las querys
            Boolean justOneRowCopied = false;
            String codigoAuxiliar = null;
            String especialidades = "";
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT Codigo, Nombre, Telefono, Correo_Electronico, Horario_Entrada, Horario_Salida, Fecha_Inicio"
                    + " FROM MEDICO WHERE Nombre like ? ORDER BY Nombre";
            String comandoTitulo = "SELECT Especialidad FROM TITULO WHERE Codigo_Medico like ?";
            ResultSet resultadosTitulos = null;
            PreparedStatement statementTitulos = null;
            
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, "%"+nombre+"%");
            ResultSet resultados = statement.executeQuery();
            while(resultados.next()){
                codigoAuxiliar = resultados.getString("Codigo");
                statementTitulos = connection.prepareStatement(comandoTitulo);
                statementTitulos.setString(1, "%"+codigoAuxiliar+"%");
                resultadosTitulos = statementTitulos.executeQuery();
                while(resultadosTitulos.next()){
                    especialidades += resultadosTitulos.getString("Especialidad") + "-";
                    
                    if(justOneRowCopied==false){
                        
                        registros.add(codigoAuxiliar);
                        registros.add(resultados.getString("Nombre"));
                        registros.add(resultados.getString("Correo_Electronico"));
                        registros.add(resultados.getString("Horario_Entrada"));
                        registros.add(resultados.getString("Horario_Salida"));
                        registros.add(resultados.getString("Fecha_Inicio"));                        
                        justOneRowCopied = true;
                    }
                }
                justOneRowCopied=false;
                registros.add(especialidades);
                especialidades = "";
                
            }
            connection.close();
            return registros;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<String> SearchMedicByEspecialidad (String especialidad){
        try {
            ArrayList<String> registros = new ArrayList<>();
            //Variable que permitira copiar una sola tupla de las querys
            Boolean justOneRowCopied = false;
            String codigoAuxiliar = "1";
            String codigoAnterior = "";
            String especialidades = "";
            Connection connection = new Conexion().CreateConnection();
            //Comando buscamos con el codigo del doc y comandoTitulo buscamos a los codigos de los docs mas parecidos a la especialidad
            String comando = "SELECT Codigo, Nombre, Telefono, Correo_Electronico, Horario_Entrada, Horario_Salida, Fecha_Inicio"
                    + " FROM MEDICO WHERE Codigo like ? ORDER BY Nombre";
            String comandoTitulo = "SELECT Codigo_Medico, Especialidad FROM TITULO WHERE Especialidad like ? ORDER BY Codigo_Medico";
            ResultSet resultadosTitulos = null;
            PreparedStatement statementTitulos = null;
            
            //De primero encontramos el codigo del medico
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comandoTitulo);
            statement.setString(1, "%"+especialidad+"%");
            ResultSet resultados = statement.executeQuery();
            //Esta parte del codigo comprueba de que no repitamos
            //los codigos de los medicos por lo que solo 
            //cargaremos una vez la query de los atributos del medico y lo demas estaremos
            //cargando la variable especialidades
            while(resultados.next()){
                codigoAuxiliar = resultados.getString("Codigo_Medico");
                if (codigoAnterior.equalsIgnoreCase(codigoAuxiliar) ==false){
                    if(codigoAnterior.equalsIgnoreCase("")==false){
                        registros.add(especialidades);
                    }
                    especialidades = "";
                    especialidades += resultados.getString("Especialidad") + "-";
                    codigoAnterior = codigoAuxiliar;
                    //luego cargamos su tupla
                    statementTitulos = connection.prepareStatement(comando);
                    statementTitulos.setString(1, "%"+codigoAuxiliar+"%");
                    resultadosTitulos = statementTitulos.executeQuery();
                    if(resultadosTitulos.next()){                            
                        registros.add(codigoAuxiliar);                        
                        registros.add(resultadosTitulos.getString("Nombre"));                        
                        registros.add(resultadosTitulos.getString("Correo_Electronico"));                        
                        registros.add(resultadosTitulos.getString("Horario_Entrada"));                        
                        registros.add(resultadosTitulos.getString("Horario_Salida"));                        
                        registros.add(resultadosTitulos.getString("Fecha_Inicio"));                                                
                    }
                }else{
                    especialidades += resultados.getString("Especialidad") + "-";
                }    
            }
            registros.add(especialidades);
            connection.close();
            return registros;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<String> SearchMedicByDate (String horaInicial, String horaFinal){
        try {
            ArrayList<String> registros = new ArrayList<>();
            //Variable que permitira copiar una sola tupla de las querys
            String codigoAuxiliar = "1";
            String especialidades = "";
            Connection connection = new Conexion().CreateConnection();
            //Comando buscamos con el codigo del doc y comandoTitulo buscamos a los codigos de los docs mas parecidos a la especialidad
            String comando = "SELECT * FROM MEDICO WHERE CONVERT(Horario_Entrada,time(0))>=CONVERT(?,time(0)) AND CONVERT(Horario_Salida,time(0))<=CONVERT(?,time(0))";
            String comandoTitulo = "SELECT Especialidad FROM TITULO WHERE Codigo_Medico = ?";
            ResultSet resultadosTitulos = null;
            PreparedStatement statementTitulos = null;
            
            //De primero encontramos el codigo del medico
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, horaInicial);
            statement.setString(2, horaFinal);
            ResultSet resultados = statement.executeQuery();
            //Cargamos las tuplas que nos lanza el rango de las fechas
            while(resultados.next()){
                especialidades ="";
                codigoAuxiliar = resultados.getString("Codigo");                                              
                registros.add(codigoAuxiliar);                                                                
                registros.add(resultados.getString("Nombre"));                                                            
                registros.add(resultados.getString("Correo_Electronico"));                                                            
                registros.add(resultados.getString("Horario_Entrada"));                                                            
                registros.add(resultados.getString("Horario_Salida"));                                            
                registros.add(resultados.getString("Fecha_Inicio"));                                                                    
                //luego cargamos su tupla de titulos                
                statementTitulos = connection.prepareStatement(comandoTitulo);                
                statementTitulos.setString(1, codigoAuxiliar);                
                resultadosTitulos = statementTitulos.executeQuery();
                
                while(resultadosTitulos.next()){                                            
                    especialidades += resultadosTitulos.getString("Especialidad") + "-";
                }
                registros.add(especialidades);
            }
            connection.close();
            return registros;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Devuelve tuplas de consultas
    * columnas 3
    * @return
     */
    public ArrayList<String> GetTuplasConsultas(){
        
        try {
                ArrayList<String> consultaTupla = new ArrayList<>();
                Connection connection = new Conexion().CreateConnection();
                String comando = "SELECT * FROM CONSULTA";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                ResultSet resultado = statement.executeQuery();
                while(resultado.next()){
                    consultaTupla.add(resultado.getString("Codigo"));
                    consultaTupla.add(resultado.getString("Tipo"));
                    consultaTupla.add(resultado.getString("Costo"));
                }
                connection.close();
                return consultaTupla;
        } catch (SQLException ex) {
            return null;
        }
        
    }
    
    /**
     * Obtiene el tipo de consulta segun su codigo
     * @param consulta
     * @return 
     */
    public String GetEspecialidadFromCosulta(String consulta){
        try {
                String tipo = null;
                Connection connection = new Conexion().CreateConnection();
                String comando = "SELECT Tipo FROM CONSULTA WHERE Codigo=?";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, consulta);
                ResultSet resultado = statement.executeQuery();
                if(resultado.next()){
                    tipo = resultado.getString("Tipo");
                }
                connection.close();
                return tipo;
        } catch (SQLException ex) {
            return null;
        }
    }
}
