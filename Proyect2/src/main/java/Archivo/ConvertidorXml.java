/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;
import Archivo.Archivo;
import java.io.InputStream;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class ConvertidorXml {

    private ArrayList<NodeList> listasXML = new ArrayList<>();
    
    
    /**
     * Transformamos los atributos del xml a nodos o en su clase NodeList 
     * para extraer los elementos
     * @param path 
     */
    public void TransformarPathXML(String path){
        Archivo archivo = new Archivo();
        Document doc = archivo.loadFile(path);
        doc.getDocumentElement().normalize();
               
        
        NodeList listaAdmin = doc.getElementsByTagName("admin");
        NodeList listaDoctor = doc.getElementsByTagName("doctor");
        NodeList listaLaboratorista = doc.getElementsByTagName("laboratorista");
        NodeList listaPaciente = doc.getElementsByTagName("paciente");
        NodeList listaExamen = doc.getElementsByTagName("examen");
        NodeList listaInforme = doc.getElementsByTagName("reporte");
        NodeList listaResultado = doc.getElementsByTagName("resultado");
        NodeList listaCita = doc.getElementsByTagName("cita");
        NodeList listaConsulta = doc.getElementsByTagName("consulta");
        
        listasXML.add(listaAdmin);
        listasXML.add(listaDoctor);
        listasXML.add(listaLaboratorista);
        listasXML.add(listaPaciente);
        listasXML.add(listaExamen);
        listasXML.add(listaInforme);
        listasXML.add(listaResultado);
        listasXML.add(listaCita);
        listasXML.add(listaConsulta);
    }
    
    /**
     * Retorna una lista de los nodos encontrados
     * en el archivo xml
     * Asegurarse haber transformado antes el archivo
     * @return 
     */
    public ArrayList<NodeList> GetElements(){
        return this.listasXML;
    } 

    void TransformarPathXMLInputStream(InputStream path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
