/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



/**
 *
 * @author camran1234
 */
public class Archivo {

    public Archivo(){
        
    }

    
    /**
     * Genera un archivo tipo org.w3c.dom.Document en base al String path 
     * Si el archivo indicado en la path no existe entonces retornara valores nulo
     * o de igual forma de valores nulo
     * @param path
     * @return
     */
    org.w3c.dom.Document loadFile(String path) {
        //Generamos de primero un documentBuilderFactory que es un objeto que nos permite generar una instancia en base a 
        //a las propiedades de la computadora, luego creamos una nueva instancia DocumentBuilder en base a la instancia 
        //anterior, y el generamos una instancia document que por medio de nuestra instancia DocumentBuilder convertimos
        //el archivo File a uno XML y lo retornamos
        try{
            if(new File(path).exists()){
                File referencedFile = new File(path);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                org.w3c.dom.Document document = (org.w3c.dom.Document) documentBuilder.parse(referencedFile);
                return document;
            }
            //Si el archivo no existe retorna nulo
            return null;
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.out.println("Error: "+ex.getMessage());
            return null;
        }
        
    }
    
    /**
     * Abre un seleccionador de archivos, y devuelve el archivo seleccionado
     * @return
     */
    public String getPathFile(){
        try{
            JFileChooser file = new JFileChooser();
            /*file.addChoosableFileFilter(new FileNameExtensionFilter("text") );
            file.setAcceptAllFileFilterUsed(false);*/
            int seleccion = file.showOpenDialog(new JFrame());
            if(seleccion == javax.swing.JFileChooser.APPROVE_OPTION){
                return (file.getSelectedFile().getPath());
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    
}
