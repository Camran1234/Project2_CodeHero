<%-- 
    Document   : AdminModifySelect
    Created on : Oct 1, 2020, 9:20:25 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    
    String opcion = request.getParameter("boton");
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Seleccion de datos Page</title>
    </head>
    <body>
        <%--Utilizaremos esta etiqueta para poder mostrar como indicador de que tabla buscar--%>
        <h1 style="text-align:center"><%=opcion%></h1>    
        <%  
    switch(opcion){
        case "Seleccionar Medico":
            %>
            <%@include file="../Modificadores/AdminModifyMedico.jsp"%>
            <%
            break;    
        case "Seleccionar Laboratorista":
            %>
            <%@include file="../Modificadores/AdminModifyLaboratorista.jsp"%>
            <%
            break;    
        case "Seleccionar Paciente":
            %>
            <%@include file="../Modificadores/AdminModifyPaciente.jsp"%>
            <%           
            break;    
        case "Seleccionar Examen":
            %>
            <%@include file="../Modificadores/AdminModifyExamen.jsp"%>
            <%            
            break;
        case "Seleccionar Consulta":
            %>
            <%@include file="../Modificadores/AdminModifyConsulta.jsp"%>
            <%            
            break;
    }
    %>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
