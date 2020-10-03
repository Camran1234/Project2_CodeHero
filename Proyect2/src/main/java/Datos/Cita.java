/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Archivo.IntervaloTiempo;
import SQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class Cita {
    
    private String codigo;
    private String paciente;
    private ArrayList<String> especialidad = new ArrayList<>();
    private String fecha;
    private String hora;
    private String medico;
    
    public void SubirArchivo(Element elementoXML){
        try {
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            paciente = elementoXML.getElementsByTagName("PACIENTE").item(0).getTextContent();
            medico = elementoXML.getElementsByTagName("MEDICO").item(0).getTextContent();
            
            //Colocamos el try en caso de que no este la etiqueta Especialidad
            try{
                //Agregamos especialidades de la cita, puesto que pueden ser varias
            NodeList childNodes = (NodeList) elementoXML.getElementsByTagName("ESPECIALIDAD");
            for(int indexNodo=0;indexNodo<childNodes.getLength();indexNodo++){
                if(childNodes.item(indexNodo).getTextContent() != null){
                    especialidad.add(childNodes.item(indexNodo).getTextContent());
                }
            }
            }catch (Exception ex){
            
            }
            
            fecha = elementoXML.getElementsByTagName("FECHA").item(0).getTextContent();
            hora = elementoXML.getElementsByTagName("HORA").item(0).getTextContent();
            this.SubirArchivoParametros(codigo, paciente, false,medico,especialidad, fecha, hora, null,null);
            especialidad = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean SubirArchivoParametros(String codigo, String paciente, Boolean citaRealizada,String medico,ArrayList<String> especialidad, String fecha,
            String hora,String codigoConsulta, String informeCita){
        try {  
            String especialidades="";
            for(int indexEspecialidad=0; indexEspecialidad<especialidad.size();indexEspecialidad++){
                especialidades += especialidad.get(indexEspecialidad);
            }
            //Variable que nos indica si todas las especialidades existen para el medico
            boolean allPdhConfirmed = false;
            Connection connection = new Conexion().CreateConnection();
            String comando = "SELECT No_Registro FROM TITULO WHERE Codigo_Medico = (?) AND Especialidad = (?)";
            String resultadoCodigoTitulo;
            PreparedStatement statement = null;
            //Condicion para comprobar de que existen especialidades
            if(especialidad.size()!=0){
                //Verificamos que el pediatra tenga dicho titulo en la base de datos
                for(int indexEspecialidad=0;indexEspecialidad<especialidad.size();indexEspecialidad++){
                    statement = null;
                    statement = connection.prepareStatement(comando);
                    statement.setString(1, medico);
                    statement.setString(2, especialidad.get(indexEspecialidad));

                        ResultSet resultado = statement.executeQuery();
                        if(resultado.next()){
                            resultadoCodigoTitulo = Integer.toString(resultado.getInt("No_Registro"));
                            if(resultadoCodigoTitulo != null){
                                allPdhConfirmed = true;
                            }else{
                                allPdhConfirmed = false;
                                break;
                            }
                        }   

                }
            }else {
                allPdhConfirmed = true;
            }
            
            //Verificamos si la hora de la cita esta dentro del rango del horario de trabajo del doctor
            comando = "SELECT Horario_Entrada, Horario_Salida FROM MEDICO WHERE Codigo=(?)";
            statement = connection.prepareStatement(comando);
            statement.setString(1,medico);
            ResultSet resultadoHorario = statement.executeQuery();
            if(resultadoHorario.next()){
                String horaPrimero = resultadoHorario.getString("Horario_Entrada");
                String horaUltimo = resultadoHorario.getString("Horario_Salida");
                IntervaloTiempo intervalo = new IntervaloTiempo(horaPrimero,horaUltimo);
                if(intervalo.IsInDateRange(hora,fecha)==false){
                    //Aprovechamos esta variable para denegar el ingreso de la cita
                    allPdhConfirmed=false;
                }
            }
            
            
            //Condicion que indica si todas las especialidades las tiene el doc
            if(allPdhConfirmed==true){
                comando = "INSERT INTO REGISTRO_CITAS (No_Registro,Fecha_Cita,Hora_Cita,Cita_Realizada,Codigo_Paciente,Codigo_Medico,Registro_Titulo,Registro_Consulta"
                    + ",Informe_Cita) VALUES (?,?,?,?,?,?,?,?,?)";
                if(informeCita==null && codigoConsulta!=null){
                    comando = "INSERT INTO REGISTRO_CITAS (No_Registro,Fecha_Cita,Hora_Cita,Cita_Realizada,Codigo_Paciente,Codigo_Medico,Registro_Titulo,Registro_Consulta"
                    + ") VALUES (?,?,?,?,?,?,?,?)";
                } else if (codigoConsulta==null && informeCita!=null){
                    comando = "INSERT INTO REGISTRO_CITAS (No_Registro,Fecha_Cita,Hora_Cita,Cita_Realizada,Codigo_Paciente,Codigo_Medico,Registro_Titulo"
                    + ",Informe_Cita) VALUES (?,?,?,?,?,?,?,?)";
                } else if(codigoConsulta==null && informeCita==null){
                    comando = "INSERT INTO REGISTRO_CITAS (No_Registro,Fecha_Cita,Hora_Cita,Cita_Realizada,Codigo_Paciente,Codigo_Medico,Registro_Titulo"
                    + ") VALUES (?,?,?,?,?,?,?)";
                }
                
                statement = connection.prepareStatement(comando);
                statement.setString(1, codigo);
                statement.setString(2, fecha);
                statement.setString(3, hora);
                statement.setBoolean(4, citaRealizada);
                statement.setString(5, paciente);
                statement.setString(6, medico);
                statement.setString(7, especialidades);
                if(codigoConsulta!=null){
                    statement.setInt(8, Integer.parseInt(codigoConsulta));
                }
                if(informeCita!=null && codigoConsulta==null){
                    statement.setString(8, informeCita);
                } else if(informeCita!=null && codigoConsulta!=null){
                    statement.setString(9, informeCita);
                }
                statement.executeUpdate();
                //Reiniciamos el arrayList
            }else{
                return false;
            }
            
            
            this.especialidad = new ArrayList<>();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Los formatos de la cita: "+codigo+" son incorrectos, el tipo de consulta no la puede manejar el doctor o el horario no"
                    + "esta dentro de lo establecido o "+ex.getMessage());
            return false;
        }
    }
}
