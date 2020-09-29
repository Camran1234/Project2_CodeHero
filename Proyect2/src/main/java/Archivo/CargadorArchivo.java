/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import Datos.Laboratorista;
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
    
    public void CargarArchivos(String path){
        convertidor.TransformarPathXML(path);
        ArrayList<NodeList> listasNodos = convertidor.GetElements();
        Element elementoLista;
        NodeList listaAuxiliar;
        Node nodo;
        
        for (int indexListaNodos=0; indexListaNodos<listasNodos.size();indexListaNodos++) {
            listaAuxiliar = listasNodos.get(indexListaNodos);
            
            for(int indexElementos=0;indexElementos<listaAuxiliar.getLength();indexElementos++){
                nodo = listaAuxiliar.item(indexElementos);
                System.out.println("Current node name: "+ nodo.getNodeName());
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                elementoLista = (Element) nodo; 
                    switch(indexListaNodos){
                        case 0:
                            
                            break;
                        case 1:

                            break;
                        case 2:
                            new Laboratorista().SubirArchivo(elementoLista);
                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        case 5:

                            break;
                        case 6:

                            break;
                        case 7:

                            break;
                        case 8:

                            break;
                    }    
                }
                   
            }
        }
    }
}
