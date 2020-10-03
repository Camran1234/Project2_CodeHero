<%-- 
    Document   : cargarArchivos
    Created on : Sep 30, 2020, 5:48:54 AM
    Author     : camran1234
--%>



<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="Archivo.Archivo"%>
<%@page import="Archivo.CargadorArchivo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="java.lang.Object"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>


<%   
    String path = (String) session.getAttribute("urlArchivoMenu");
    String error = (String) session.getAttribute("error");
    %>
    
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado carga archivos</title>
    </head>
    <body>
        <%
        try {
            CargadorArchivo cargador = new CargadorArchivo();
            ArrayList<String> archivosCargados =cargador.CargarArchivos(path);
            %>
            <h1>Archivos Leidos:</h1>
        <%
            for(int indexArchivos=0;indexArchivos<archivosCargados.size();indexArchivos++){
            %>
            <%=archivosCargados.get(indexArchivos)%>
            <%
            }
         } catch(Exception ex){
           String mensaje = ex.getMessage();
           String printTrace = ex.toString();
            %>
         <h3 style="text-align:center"> No se escogio ningun archivo para leer error: <%=error%></h3> 
         <%
        }
            %>
        <a href="../menuInicio.jsp">Regresar</a>
    </body>
</html>
