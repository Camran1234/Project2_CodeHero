<%-- 
    Document   : adminAddPage
    Created on : Sep 30, 2020, 8:27:15 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Agregar Page</title>
    </head>
    <body>
<%  String opcion = request.getParameter("opcionIngresarDato");
    
    switch(opcion){
        case "medico":
            %>
            <%@ include file = "../Data/adminAddMedico.html"%>
            <%
            break;    
        case "laboratorista":
            %>
            <%@ include file = "../Data/adminAddLaboratorista.html"%>
            <%
            break;    
        case "paciente":
            %>
            <%@ include file = "../Data/adminAddPaciente.html"%>
            <%
            break;    
        case "examenLaboratorio":
            %>
            <%@ include file = "../Data/adminAddExamen.html"%>
            <%
            break;
    }
    
    
    %>
    <a href="../Data/adminMenuPage.jsp">Regresar</a>
    </body>
</html>
