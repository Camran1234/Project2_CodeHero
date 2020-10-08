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
 *
 * @author camran1234
 */
public class ModificadorUsuario {
    
    /**
     * Modifica una tupla de la tabla PACIENTE con los parametros nuevos enviados
     * Retorna verdadero si se cargo la query correctamente
     * @param codigo
     * @param nombre
     * @param DPI
     * @param telefono
     * @param sexo
     * @param fechaNacimiento
     * @param tipoSangre
     * @param peso
     * @param password
     * @param correoElectronico
     * @return 
     */
    public boolean ModificarPaciente(String codigo, String nombre, Long DPI, int telefono, String sexo, String fechaNacimiento,
            String tipoSangre, double peso, String password, String correoElectronico){
        try {
                Connection connection = new Conexion().CreateConnection();
                String comando = "UPDATE PACIENTE SET Password=(?), Nombre=(?), Sexo=(?), Fecha_Nacimiento=(?), DPI=(?),"
                        + "Telefono=(?), Peso=(?), Tipo_Sangre=(?), Correo_Electronico=(?) WHERE Codigo=(?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, password);
                statement.setString(2, nombre);
                statement.setString(3, sexo);
                statement.setString(4, fechaNacimiento);
                statement.setString(5, Long.toString(DPI));
                statement.setString(6, Integer.toString(telefono));
                statement.setDouble(7, peso);
                statement.setString(8, tipoSangre);
                statement.setString(9, correoElectronico);
                statement.setString(10, codigo);
                statement.executeUpdate();
                return true;
                //Codigo para modificar una tupla de la tabla PACIENTE de la base de datos CODEHERO
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Modifica una tubla de la tabla MEDICO con los parametros enviados
     * Retorna verdadero si se cargo la query correctamente
     * @param codigo
     * @param nombre
     * @param DPI
     * @param telefono
     * @param colegiado
     * @param titulos
     * @param horarioInicio
     * @param horarioFinal
     * @param password
     * @param correoElectronico
     * @param fechaInicio
     * @return 
     */
    public boolean ModificarMedico(String codigo, String nombre, Long DPI, int telefono, String colegiado,
            ArrayList<String> titulos,String horarioInicio, String horarioFinal, String password, String correoElectronico, String fechaInicio){
        try {
                Connection connection = new Conexion().CreateConnection();
                String comando = "UPDATE MEDICO SET Nombre=(?), Password=(?), Numero_Colegiado=(?), DPI=(?), Telefono=(?),"
                        + "Correo_Electronico=(?), Horario_Entrada=(?), Horario_Salida=(?), Fecha_Inicio=(?) WHERE Codigo=(?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, nombre);
                statement.setString(2, password);
                statement.setString(3, colegiado);
                statement.setString(4, Long.toString(DPI));
                statement.setString(5, Integer.toString(telefono));
                statement.setString(6, correoElectronico);
                statement.setString(7, horarioInicio);
                statement.setString(8, horarioFinal);
                statement.setString(9, fechaInicio);
                statement.setString(10, codigo);
                statement.executeUpdate();
                
                comando = "DELETE FROM TITULO WHERE Codigo_Medico=(?)";
                statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.executeUpdate();
                
                comando = "INSERT INTO TITULO (Codigo_Medico,Especialidad) VALUES (?,?)";
                
                //Lo manejamos como ciclo para colocar varios titulos
                for(int indexTitulo=0;indexTitulo<titulos.size();indexTitulo++){
                    statement = null;
                    statement = connection.prepareStatement(comando);
                    statement.setString(1, codigo);
                    statement.setString(2, titulos.get(indexTitulo));
                    statement.executeUpdate();
                }
                return true;
                //Codigo para modificar una tupla de la tabla PACIENTE de la base de datos CODEHERO
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Modifica una tupla de la tabla LABORATORISTA, retorna verdadero si logro actualizar la base de datos
     * @param codigo
     * @param nombre
     * @param DPI
     * @param telefono
     * @param colegiado
     * @param titulos
     * @param horarioInicio
     * @param horarioFinal
     * @param password
     * @param correoElectronico
     * @param fechaInicio
     * @return 
     */
    public boolean ModificarLaboratorista(String codigo, String nombre, Long DPI, int telefono, String examen, String correo, 
            ArrayList<String> diasTrabajo, String trabajo, String password, String registro){
        try {
                String dias="";
                for(int indexArray=0;indexArray<diasTrabajo.size();indexArray++){
                    dias += diasTrabajo.get(indexArray) + "-";
                }
            
                Connection connection = new Conexion().CreateConnection();
                String comando = "UPDATE LABORATORISTA SET Nombre=(?), Password=(?), Numero_Registro_Ministerio_Salud=(?), DPI=(?), Telefono=(?), Examen_trabajo=(?),"
                        + "Dias_Trabajo=(?), Correo_Electronico=(?), Fecha_Inicio=(?) WHERE Codigo=(?) ";
                
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, nombre);
                statement.setString(2, password);
                statement.setString(3, registro);
                statement.setString(4, Long.toString(DPI));
                statement.setString(5, Integer.toString(telefono));
                statement.setString(6, examen);
                statement.setString(7, dias);
                statement.setString(8, correo);
                statement.setString(9, trabajo);
                statement.setString(10, codigo);
                statement.executeUpdate();
                return true;
                //Codigo para modificar una tupla de la tabla PACIENTE de la base de datos CODEHERO
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Modifica la tupla de la tabla EXAMENES_LABORATORIO con los parametros enviados, retorna verdadero si logro realizar la actualizacion
     * @param codigo
     * @param nombre
     * @param orden
     * @param descripcion
     * @param costo
     * @param informe
     * @return 
     */
    public boolean ModificarExamenLaboratorio (String codigo, String nombre, Boolean orden, String descripcion, double costo, String informe){
        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "UPDATE EXAMENES_LABORATORIO SET Nombre=(?), Requerimiento_Orden=(?), Descripcion=(?), Costo=(?), Informe=(?) WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, nombre);
            statement.setBoolean(2,orden);
            statement.setString(3, descripcion);
            statement.setDouble(4, costo);
            statement.setString(5, informe);
            statement.setString(6, codigo);
            statement.executeUpdate();
            return true;
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
               ex.printStackTrace();
               return false;
        }
    }
    
    /**
     * Modifica el costo de una tupla de la tabla CONSULTA, retorna verdadero si logro realizar la actualizacion
     * @param codigo
     * @param costo
     * @return 
     */
    public boolean ModificarConsulta (String codigo, double costo){
        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "UPDATE CONSULTA SET Costo=(?) WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setDouble(1, costo);
            statement.setInt(2, Integer.parseInt(codigo));
            statement.executeUpdate();
            return true;
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
               ex.printStackTrace();
               return false;
        }
    }
    
    
    
    
}
