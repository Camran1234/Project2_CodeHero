<%-- 
    Document   : LaboratoristaMenuPage
    Created on : Oct 2, 2020, 1:30:27 PM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.OpcionesEspecialesLaboratorista"%>
<%@page import="SQL.LectorUsuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%                
    OpcionesEspecialesLaboratorista opcionesEspeciales = new OpcionesEspecialesLaboratorista();
    //Obtenemos la lista de codigos de examenes disponibles para el laboratorista
    ArrayList<String> listaCodigos = opcionesEspeciales.GetLaboratoristaJobs((String)session.getAttribute("codigoSession"));

    //Colocamos la url donde redireccionara en el formulario de registrar examen
    String url1 = "./Data/LaboratoristaRealizarExamen.jsp";
    session.setAttribute("paginaRedireccionar", url1);
    
    %>

<!DOCTYPE html>
<form method="post" action="../UploadData" >
    <div class="form-group">
        <h4 style="text-align:center">Confirmar Examen</h4>
        <label> Examenes Pendientes</label>
        <select class="form-group" name="opcion" required>
                <%
                    if(listaCodigos.size()!=0){
                        for(String listaCodigo:listaCodigos){
                        %>
                        <option value="<%=listaCodigo%>"><%=listaCodigo%></option>
                        <%
                        }
                    }else{
                        %>
                        <option>SIN TRABAJO</option>
                        <%
                    }
                %>
        </select>
    </div>
    <div class="form-group">
        <label>Numero Registro</label>
            <input type="text" class="form-control" name="registro" placeholder="Numero Registro Informe" required>
    </div>
    <div class="form-group">
        <label>Informe</label>
        <input type="file" class="form-control" name="file" accept=".pdf" required>
    </div>
    <div class="form-group">
        <label>Fecha</label>
        <input type="date" name="fecha" value="2000-01-01" min="1900-01-01" max="2034-01-01" required>
    </div>
    <div class="form-group">
        <label>Hora</label>
        <input type="time" name="hora" required>
    </div>
    <input type="submit" class="ex1" style="margin:0px auto; display:block;" name="boton" value="Completar Examen" size="50%" required/><br>
</form>
        
        <form method="post" action="../Reportes/ReporteExamenesRealizadosDiaL.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Reportes</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
    </form>
<a href="../menuInicio.jsp">Regresar</a>
