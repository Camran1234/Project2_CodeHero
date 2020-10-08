<%-- 
    Document   : LaboratoristaRealizarExamen
    Created on : Oct 2, 2020, 2:59:04 PM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.OpcionesEspecialesLaboratorista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    String codigoExamen = (String) session.getAttribute("codigoExamenL");
    String noRegistro = (String) session.getAttribute("registroL");
    String informe = (String) session.getAttribute("urlArchivoMenu");
    String fecha = (String) session.getAttribute("fechaL");
    String hora = (String) session.getAttribute("horaL");
    String laboratorista = (String) session.getAttribute("codigoSession");
    OpcionesEspecialesLaboratorista opciones = new OpcionesEspecialesLaboratorista();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados Page</title>
    </head>
    <body>
        <%
            if(opciones.CompleteJob(codigoExamen, noRegistro, informe, fecha, hora,laboratorista)){
                %>
                <h1 style="text-align:center">  Resultado <%=codigoExamen%> subido </h1>
                <%
            }else{
                %>
                <h1 style="text-align:center">  No se pudo crear el resultado, intente de nuevo <%=codigoExamen%> subido </h1>
                <%
            }
            
            %>
    </body>
    <a href="../HTML/StartSession.jsp"> Continuar </a>
</html>
