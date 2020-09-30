<%-- 
    Document   : startSession
    Created on : Sep 30, 2020, 6:40:57 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SQL.CorroboradorUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>session Page</title>
    </head>
    <body>
        
    
<%
    String usuario = request.getParameter("codigo");
    String password = request.getParameter("password");
    String respuesta = new CorroboradorUsuario().CheckLogIn(usuario, password);
    if(respuesta!=null){
        switch(respuesta){
            case "ADMINISTRADOR":

                break;    
            case "MEDICO":

                break;    
            case "LABORATORISTA":

                break;
            case "PACIENTE":

                break;
        }
    }else{
            %>
            <h1 style="text-align:center">Usuario y Contrasena incorrectos, intente de nuevo</h1>
            <a href="../menuInicio.jsp">Reintentar</a>
            <%
    }
    %>

    </body>
</html>
