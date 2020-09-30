/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import Datos.Administrador;
import Datos.Cita;
import Datos.Consulta;
import Datos.ExamenLaboratorio;
import Datos.Informe;
import Datos.Laboratorista;
import Datos.Medico;
import Datos.Paciente;
import Datos.Resultado;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author camran1234
 */
public class CargadorArchivo {

    private ConvertidorXml convertidor = new ConvertidorXml();
    
    /**
     * Carga archivos y los carga a la base de datos
     * @param path 
     */
    public ArrayList<String> CargarArchivos(String path){
        ArrayList<String> nodosCargados = new ArrayList<>();
        convertidor.TransformarPathXML(path);
        ArrayList<NodeList> listasNodos = convertidor.GetElements();
        Element elementoLista;
        NodeList listaAuxiliar;
        Node nodo;
        
        for (int indexListaNodos=0; indexListaNodos<listasNodos.size();indexListaNodos++) {
            listaAuxiliar = listasNodos.get(indexListaNodos);
            
            for(int indexElementos=0;indexElementos<listaAuxiliar.getLength();indexElementos++){
                nodo = listaAuxiliar.item(indexElementos);
                nodosCargados.add("Current node name: "+ nodo.getNodeName());
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                elementoLista = (Element) nodo; 
                    switch(indexListaNodos){
                        case 0:
                            new Administrador().SubirArchivo(elementoLista);
                            break;
                        case 1:
                            new Medico().SubirArchivo(elementoLista);
                            break;
                        case 2:
                            new Laboratorista().SubirArchivo(elementoLista);
                            break;
                        case 3:
                            new Paciente().SubirArchivo(elementoLista);
                            break;
                        case 4:
                            new ExamenLaboratorio().SubirArchivo(elementoLista);
                            break;
                        case 5:
                            new Informe().SubirArchivo(elementoLista);
                            break;
                        case 6:
                            new Resultado().SubirArchivo(elementoLista);
                            break;
                        case 7:
                            new Cita().SubirArchivo(elementoLista);
                            break;
                        case 8:
                            new Consulta().SubirArchivo(elementoLista);
                            break;
                    }    
                }
                   
            }
        }
        return nodosCargados;
    }
}
