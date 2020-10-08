<%-- 
    Document   : AdminAddPage
    Created on : Sep 30, 2020, 8:27:15 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Agregar Page</title>
    </head>
    <body>
<%  String opcion = request.getParameter("opcionIngresarDato");
    switch(opcion){
        case "medico":
            %>
            <%@ include file = "../Data/AdminAddMedico.html"%>
            <%
            break;    
        case "laboratorista":
            %>
            <%@ include file = "../Data/AdminAddLaboratorista.html"%>
            <%
            break;    
        case "paciente":
            %>
            <%@ include file = "../Data/AdminAddPaciente.html"%>
            <%
            break;    
        case "examenLaboratorio":
            %>
            <%@ include file = "../Data/AdminAddExamen.html"%>
            <%
            break;
    }
    
    
    %>
    <a href="../HTML/StartSession.jsp">Regresar</a>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>    
    </body>
</html>
