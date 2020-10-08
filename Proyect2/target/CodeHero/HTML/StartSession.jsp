<%-- 
    Document   : StartSession
    Created on : Sep 30, 2020, 6:40:57 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SQL.CorroboradorUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>session Page</title>
    </head>
    <body>
        
    
<%
        String usuario = request.getParameter("codigo");
    String password = request.getParameter("password");
    if(usuario==null && password ==null){
        usuario = (String)session.getAttribute("codigoSession");
        password = (String)session.getAttribute("passwordSession");
    }
    session.setAttribute("codigoSession", usuario);
    session.setAttribute("passwordSession", password);
    //Para la carga de archivos
    session.setAttribute("selectMedico", false);
    
    String respuesta = new CorroboradorUsuario().CheckLogIn(usuario, password);
    if(respuesta!=null){
        %>
            <h1 style="text-align:center">Bienvenido <%=usuario%></h1>
        <%
        switch(respuesta){
            case "ADMINISTRADOR":
            %>
                <%@ include file = "../Data/AdminMenuPage.jsp"%>
             <%
                break;    
            case "MEDICO":  
            %>
                <%@ include file = "../Data/MedicoMenuPage.jsp"%>
            <%
                break;    
            case "LABORATORISTA":
            %>
                <%@ include file = "../Data/LaboratoristaMenuPage.jsp"%>
            <%
                break;
            case "PACIENTE":
            %>
                <%@ include file = "../Data/PacienteMenuPage.jsp"%>
            <%
                break;
        }
        %>
        <%
    }else{
            %>
            <h1 style="text-align:center">Usuario y Contrasena incorrectos, intente de nuevo</h1>
            <a href="../menuInicio.jsp">Reintentar</a>
            <%
    }
    %>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
