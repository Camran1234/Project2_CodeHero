<%-- 
    Document   : IngresarCita
    Created on : Oct 2, 2020, 9:15:41 PM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SQL_Reportes.OpcionesBuscarMedico"%>
<%@page import="Datos.Cita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String retornar = (String) session.getAttribute("urlRetornoCita");
    String codigoMedico = request.getParameter("opcionListaMedico");
        String registro = request.getParameter("registro");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String consulta = request.getParameter("opcionListaConsulta");
        String pacienteIngresar = request.getParameter("nombrePaciente");
        String tipo = new OpcionesBuscarMedico().GetEspecialidadFromCosulta(consulta);
        ArrayList<String> especialidad = new ArrayList<>();
        //Esta sentencia para asegurarnos de que obtuvimos el tipo de consulta, si es nulo generaremos un error para que 
        //Nos retorne que no pudo ingresar la cita
        if(tipo!=null){
            especialidad.add(tipo);
        }else{
            registro=null;
        }
        
        Cita cita = new Cita();
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        if(cita.SubirArchivoParametros(registro, pacienteIngresar, false, codigoMedico, especialidad, fecha, hora, consulta, null)){
            %>
                <h1 style="text-align:center">REGISTRO CITA:</h1>
                <p class="text-justify"> Se ingreso una nueva cita <%=registro%> el <%=fecha%> a la hora de <%=hora%>, se ha registrado la cita </p>
            <%
        }else{
            %>
                <h1 style="text-align:center">NO SE REGISTRO LA CITA:</h1>
                <p class="text-justify"> Ocurrio un error al ingresar la cita, el doctor consultado no puede manejar el tipo de consulta o no esta en su hora de trabajo</p>
            <%
        }
            %>
            <a href=<%=retornar%>>Continuar</a>
    </body>
</html>
