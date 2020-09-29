/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

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
            peso = Double.parseDouble(elementoXML.getElementsByTagName("PESOO").item(0).getTextContent());
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void SubirArchivoParametros(String codigo, String nombre, Long DPI, int telefono, String sexo, String fechaNacimiento,
            String tipoSangre, double peso, String password){
        
    }
    
}
