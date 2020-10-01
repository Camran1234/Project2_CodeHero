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
public class LectorTuplas {

    /**
     * Devolvera una lista con los datos en orden de la tabla PACIENTE
     * los datos en orden son: codigo, nombre, password, sexo, dpi, fecha de nacimiento
     * telefono, peso, tipo sangre, correo electronico
     * @param codigo
     * @return
     */
    public ArrayList<String> GetTuplaPaciente(String codigo){
        ArrayList<String> listaDatos = new ArrayList<>();

        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM PACIENTE WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next()){
                listaDatos.add(resultado.getString("Codigo"));
                listaDatos.add(resultado.getString("Nombre"));
                listaDatos.add(resultado.getString("Password"));
                listaDatos.add(resultado.getString("Sexo"));
                listaDatos.add(resultado.getString("DPI"));
                listaDatos.add(resultado.getString("Fecha_Nacimiento"));
                listaDatos.add(resultado.getString("Telefono"));
                listaDatos.add(Double.toString(resultado.getDouble("Peso")));
                listaDatos.add(resultado.getString("Tipo_Sangre"));
                listaDatos.add(resultado.getString("Correo_Electronico"));
            }
            connection.close();
            return listaDatos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Devuelve una lista con los datos de una tupla de la tabla MEDICO, el orden de los datos es de:
     * codigo, nombre, password, nummero colegiado, dpi, telefono, correo electronico, horario entrada, horario salida, fecha inicio
     * @param codigo
     * @return 
     */
    public ArrayList<String> GetTuplaMedico(String codigo){
        ArrayList<String> listaDatos = new ArrayList<>();

        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM MEDICO JOIN TITULO WHERE TITULO.Codigo_Medico =(?) AND MEDICO.Codigo=(?)";
            String especialidades = "";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, codigo);
            ResultSet resultado = statement.executeQuery();
            Boolean onlyOneTimeCharged=false;
            
            //Obtenemos solo una primera vez todos los datos con excepcion de especialidad
            //especialidades agregara todas las especialidades que posea el doctor
            while(resultado.next()){
                if(onlyOneTimeCharged==false){
                    listaDatos.add(resultado.getString("Codigo"));
                    listaDatos.add(resultado.getString("Nombre"));
                    listaDatos.add(resultado.getString("Password"));
                    listaDatos.add(resultado.getString("Numero_Colegiado"));
                    listaDatos.add(resultado.getString("DPI"));
                    listaDatos.add(resultado.getString("Telefono"));
                    listaDatos.add(resultado.getString("Correo_Electronico"));
                    listaDatos.add(resultado.getString("Horario_Entrada"));
                    listaDatos.add(resultado.getString("Horario_Salida"));
                    listaDatos.add(resultado.getString("Fecha_Inicio"));
                    onlyOneTimeCharged=true;
                }
                especialidades += resultado.getString("Especialidad") + "-";
            }
            listaDatos.add(especialidades);
            connection.close();
            return listaDatos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Obtiene la lista de datos de una tupla de la tabla laboratorista, donde el orden de los datos son:
     * codigo, nombre, password, numero registro ministerio salud, dpi, telefono, examen trabajo, dias trabajo, 
     * correo electronico, fecha inicio
     * @param codigo
     * @return 
     */
    public ArrayList<String> GetTuplaLaboratorista(String codigo){
        ArrayList<String> listaDatos = new ArrayList<>();

        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM LABORATORISTA WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next()){
                listaDatos.add(resultado.getString("Codigo"));
                listaDatos.add(resultado.getString("Nombre"));
                listaDatos.add(resultado.getString("Password"));
                listaDatos.add(resultado.getString("Numero_Registro_Ministerio_Salud"));
                listaDatos.add(resultado.getString("DPI"));
                listaDatos.add(resultado.getString("Telefono"));
                listaDatos.add(resultado.getString("Examen_trabajo"));
                listaDatos.add(resultado.getString("Dias_Trabajo"));
                listaDatos.add(resultado.getString("Correo_Electronico"));
                listaDatos.add(resultado.getString("Fecha_Inicio"));
            }
            connection.close();
            return listaDatos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    /**
     * Devuelve una lista de datos de una tupla de la tabla EXAMENES_LABORATORIO donde el orden de la lista es:
     * codigo, nombre, requerimiento orden, descripcion, costo, informe
     * @param codigo
     * @return 
     */
    public ArrayList<String> GetTuplaExamen(String codigo){
        ArrayList<String> listaDatos = new ArrayList<>();

        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM EXAMENES_LABORATORIO WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next()){
                listaDatos.add(resultado.getString("Codigo"));
                listaDatos.add(resultado.getString("Nombre"));
                listaDatos.add(Boolean.toString(resultado.getBoolean("Requerimiento_Orden")));
                listaDatos.add(resultado.getString("Descripcion"));
                listaDatos.add(Double.toString(resultado.getDouble("Costo")));
                listaDatos.add(resultado.getString("Informe"));
            }
            connection.close();
            return listaDatos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
    
    /**
     * Obtiene una lista de datos de una tupla de la tabla CONSULTA donde el orden de los datos de la lista es:
     * Codigo, tipo, costo
     * @param codigo
     * @return 
     */
    public ArrayList<String> GetTuplaConsulta(String codigo){
        ArrayList<String> listaDatos = new ArrayList<>();

        try {  
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM CONSULTA WHERE Codigo=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setInt(1, Integer.parseInt(codigo));
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next()){
                listaDatos.add(Integer.toString(resultado.getInt("Codigo")));
                listaDatos.add(resultado.getString("Tipo"));
                listaDatos.add(Double.toString(resultado.getDouble("Costo")));
            }
            connection.close();
            return listaDatos;
        } catch (SQLException ex) {
               ex.printStackTrace();
               return null;
        }
    }
}
