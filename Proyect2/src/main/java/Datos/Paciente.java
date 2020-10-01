/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class Paciente extends Usuario{
    private String sexo;
    private String fechaNacimiento;
    private String tipoSangre;
    private int telefono;
    private double peso;
    private String correoElectronico;
    
    
    @Override
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            DPI =Long.parseLong(elementoXML.getElementsByTagName("DPI").item(0).getTextContent()) ;
            telefono = Integer.parseInt(elementoXML.getElementsByTagName("TELEFONO").item(0).getTextContent());
            sexo = elementoXML.getElementsByTagName("SEXO").item(0).getTextContent();
            fechaNacimiento = elementoXML.getElementsByTagName("BIRTH").item(0).getTextContent();
            tipoSangre = elementoXML.getElementsByTagName("SANGRE").item(0).getTextContent();
            peso = Double.parseDouble(elementoXML.getElementsByTagName("PESO").item(0).getTextContent());
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
            correoElectronico = elementoXML.getElementsByTagName("CORREO").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, nombre, DPI, telefono, sexo, fechaNacimiento, tipoSangre, peso, password,correoElectronico);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String sexo, String fechaNacimiento,
            String tipoSangre, double peso, String password, String correoElectronico){
        try {
                Connection connection = new Conexion().CreateConnection();
                String comando = "INSERT INTO PACIENTE (Codigo,Password,Nombre,Sexo,Fecha_Nacimiento,"
                        + "DPI,Telefono,Peso,Tipo_Sangre,Correo_Electronico) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = null;
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.setString(2, password);
                statement.setString(3, nombre);
                statement.setString(4, sexo);
                statement.setString(5, fechaNacimiento);
                statement.setString(6, Long.toString(DPI));
                statement.setString(7, Integer.toString(telefono));
                statement.setDouble(8, peso);
                statement.setString(9, tipoSangre);
                statement.setString(10, correoElectronico);
                statement.executeUpdate();
                return true;
                //Ssolo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
        } catch (SQLException ex) {
            return false;
        }
    }
    
}
