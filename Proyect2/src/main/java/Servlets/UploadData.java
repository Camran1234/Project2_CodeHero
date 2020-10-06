/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author camran1234
 */
@WebServlet("/UploadData")
public class UploadData extends HttpServlet {



    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final long serialVersionUID = 1L;
        final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
        final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
        final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
        final String UPLOAD_DIRECTORY = "upload";
        // checks if the request actually contains upload file
        String path;
 
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("file")
                + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        // redireccionamos a la pagina correspondiente segun el atributo establecido en la sesion
        String urlRedireccion = (String) request.getSession().getAttribute("paginaRedireccionar");
        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    //Para conseguir la path del archivo subido
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        path = filePath;
                        if(path !=null){
                            request.getSession().setAttribute("urlArchivoMenu", path);
                        }else{
                            request.getSession().setAttribute("urlArchivoMenu", " ");
                        }
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                    } else if (item.isFormField()) {
                        //Convertimos los items en su nombre y valor, verificamos a cada uno para enviarlo a sus respectivos lugares
                        String nombreInput = item.getFieldName();
                        String valorInput = item.getString();
                        if(urlRedireccion.equalsIgnoreCase("./Data/CrearOrden.jsp")){
                            switch(nombreInput){
                                case "opcionListaR":
                                    request.getSession().setAttribute("pacienteR", valorInput);
                                    break;    
                                case "opcionExamenesR":
                                    request.getSession().setAttribute("examenR", valorInput);
                                    break;    
                                case "registroR":
                                    request.getSession().setAttribute("registroRC", valorInput);
                                    break;    
                                case "fechaR":
                                    request.getSession().setAttribute("fechaRC", valorInput);
                                    break;    
                                case "horaR":
                                    request.getSession().setAttribute("horaRC", valorInput);
                                    break;    
                                case "opcionMedico":
                                    String opcionMedico = valorInput;
                                    //Condicion que nos permite recibir al nombre de nuestro doctor
                                    try{
                                        if(opcionMedico!=null){
                                            if(opcionMedico.equalsIgnoreCase("")==false){
                                                request.getSession().setAttribute("actualMedico", opcionMedico);    
                                            }
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    break;
                            } 
                        }else{
                            switch(nombreInput){
                                case"opcion":
                                    request.getSession().setAttribute("codigoExamenL", valorInput);
                                    break;                                    
                                case"registro":
                                    request.getSession().setAttribute("registroL",valorInput);
                                    break;                                    
                                case"fecha":
                                    request.getSession().setAttribute("fechaL", valorInput);
                                    break;                                    
                                case"hora":
                                    request.getSession().setAttribute("horaL", valorInput);
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            String error = ex.getMessage();
            String error2 = ex.toString();
            request.getSession().setAttribute("error", error + error2);
            ex.printStackTrace();
        }
        
                
        

        
//          Codigo Antiguo, no funcional, ya que no se puede hacer request.getParameter() o derivados si se llama a un
//                  form con encytipe="multi-form data"
//        if(urlRedireccion.equalsIgnoreCase("./Data/CrearOrden.jsp")){
//            
//            
//        request.getSession().setAttribute("pacienteR", request.getParameter("opcionListaR"));
//        request.getSession().setAttribute("examenR", request.getParameter("opcionExamenesR"));
//        request.getSession().setAttribute("registroRC", request.getParameter("registroR"));
//        request.getSession().setAttribute("fechaRC", request.getParameter("fechaR"));
//        request.getSession().setAttribute("horaRC", request.getParameter("horaR"));
//        String opcionMedico = request.getParameter("opcionMedico");
//        try{
//            if(opcionMedico!=null){
//                if(opcionMedico.equalsIgnoreCase("")==false){
//                    request.getSession().setAttribute("actualMedico", opcionMedico);    
//                }
//            }
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        }else {
//        request.getSession().setAttribute("codigoExamenL", request.getParameter("opcion"));
//        request.getSession().setAttribute("registroL",request.getParameter("registro"));
//        request.getSession().setAttribute("fechaL", request.getParameter("fecha"));
//        request.getSession().setAttribute("horaL", request.getParameter("hora"));
//       
//        }
        response.sendRedirect(urlRedireccion);
    }


}
