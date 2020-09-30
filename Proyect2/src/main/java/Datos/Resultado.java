/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;

/**
 *
 * @author camran1234
 */
public class Resultado {
    
    private String codigo;
    private String paciente;
    private String medico;
    private String examen;
    private String laboratorista;
    private String orden;
    private String informe;
    private String fecha;
    private String hora;
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            paciente = elementoXML.getElementsByTagName("PACIENTE").item(0).getTextContent();
            examen = elementoXML.getElementsByTagName("EXAMEN").item(0).getTextContent();
            laboratorista = elementoXML.getElementsByTagName("LABORATORISTA").item(0).getTextContent();
            orden = elementoXML.getElementsByTagName("ORDEN").item(0).getTextContent();
            informe = elementoXML.getElementsByTagName("INFORME").item(0).getTextContent();
            fecha = elementoXML.getElementsByTagName("FECHA").item(0).getTextContent();
            hora = elementoXML.getElementsByTagName("HORA").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, paciente, medico, examen, laboratorista, orden, informe, fecha, hora);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public void SubirArchivoParametros(String codigo, String paciente, String medico, String examen,
            String laboratorista, String orden, String informe, String fecha, String hora){
        try {  
            
            Boolean permisoParaSubir = true;
            String formatoExamen=null;
            String formatoEnviado=null;
            //Corroboramos el formato del informe en el examen
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT * FROM EXAMENES_LABORATORIO WHERE CODIGO=(?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, examen);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                formatoExamen = resultado.getString("Informe");
                Boolean requerimiento = resultado.getBoolean("Requerimiento_Orden");
                formatoEnviado = informe.substring((informe.length()-3));
                if((requerimiento == true) &&(orden==null || orden.equalsIgnoreCase(""))){
                    permisoParaSubir = false;
                }                
                if(formatoExamen.equalsIgnoreCase("IMG") && formatoEnviado.equalsIgnoreCase("PDF")){
                    permisoParaSubir = false;
                }else if(formatoExamen.equalsIgnoreCase("PDF") && formatoEnviado.equalsIgnoreCase("PDF")==false){
                    permisoParaSubir = false;
                }
            }
            
            if(permisoParaSubir==true){
                connection = new Conexion().CreateConnection();
            comando = "INSERT INTO RESULTADO (No_Registro,Orden,Informe,Fecha,Hora,Paciente,Medico,Examen,Laboratorista) VALUES (?,?,?,?,?,?,?,?,?)";
            statement = null;
            statement = connection.prepareStatement(comando);
            statement.setString(1, codigo);
            statement.setString(2, orden);
            statement.setString(3,informe);
            statement.setString(4, fecha);
            statement.setString(5, hora);
            statement.setString(6, paciente);
            statement.setString(7, medico);
            statement.setString(8, examen);
            statement.setString(9, laboratorista);
            statement.executeUpdate();
                //Solo copiar esto a las otras clases colocar la nueva base de datos ya modificaa, y de ultimo se agrega todas las clases restantes
                //de lista, se hace la interfaz de empleado y cliente y alli estaria
            }else{
                if((orden==null || orden.equalsIgnoreCase(""))){
                    JOptionPane.showMessageDialog(null, "Se requeria una orden y no se imprimio ninguna en el resultado");
                } else if((formatoEnviado!=null && formatoExamen!=null) &&(formatoEnviado.equalsIgnoreCase(formatoExamen)==false)){
                    JOptionPane.showMessageDialog(null, "El formato del resultado no coincide con el formato del examen");
                }

            }
            
            
        } catch (SQLException ex) {
               ex.printStackTrace();
        }
    }
}
