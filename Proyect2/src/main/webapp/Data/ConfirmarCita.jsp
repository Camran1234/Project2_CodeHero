<%-- 
    Document   : ConfirmarCita
    Created on : Oct 1, 2020, 8:25:57 PM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="SQL_Reportes.OpcionesEspecialesMedico"%>
<%
        String codigoCita = request.getParameter("opcionCitas");
        String noRegistro = request.getParameter("registro");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        OpcionesEspecialesMedico opcionesMedico = new OpcionesEspecialesMedico();
        %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Cita Page</title>
    </head>
    
    <body>
        <%
        if(opcionesMedico.ConfirmarCita(noRegistro, descripcion, fecha, hora, codigoCita)){
            %>
                <h1 style="text-align:center">REGISTRO CITA:</h1>
                <p class="text-justify"> Se ingreso un nuevo reporte de codigo: <%=noRegistro%> el <%=fecha%> a la hora de <%=hora%>, se ha completado la cita </p>
            <%
        }else{
            %>
                <h1 style="text-align:center">NO SE REGISTRO LA CITA:</h1>
                <p class="text-justify"> Ocurrio un error al ingresar la cita, porque no hay citas disponibles vuelve a intentar de nuevo cuando tengas citas</p>
            <%
        }
            %>
            <a href="../HTML/StartSession.jsp">Continuar</a>
    </body>
</html>
