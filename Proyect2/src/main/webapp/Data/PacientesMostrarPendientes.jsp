<%-- 
    Document   : PacientesMostrarPendientes
    Created on : Oct 3, 2020, 8:32:22 AM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.OpcionsEspecialesPaciente"%>
<%@page import="SQL_Reportes.OpcionesBuscarMedico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Pendientes Page</title>
    </head>
    <body>
        <h3> Citas Pendientes: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">No. Registro</th>
              <th scope="col">Fecha Cita</th>
              <th scope="col">Hora Cita</th>
              <th scope="col">Medico</th>
              <th scope="col">Consulta</th>
            </tr>
          </thead>
          <tbody>
              <%
                  String paciente = (String) session.getAttribute("codigoSession");
                  ArrayList<String> citasPendientes = new OpcionsEspecialesPaciente().GetCitasPendientes(paciente);
                  if(citasPendientes!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<citasPendientes.size();indexResultados++){
                        %>
                        <td><%=citasPendientes.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%5==0) && (indexResultados != (citasPendientes.size()-1)) && (indexResultados!=0)){
                        %>
                        </tr><!-- Agregamos una tupla mas -->
                        <tr>
                        <%
                        }if(indexResultados == (citasPendientes.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table></br>
        
        <h3> Examenes Pendientes: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">No. Registro</th>
              <th scope="col">Orden Medico</th>
              <th scope="col">Hora</th>
              <th scope="col">Fecha</th>
              <th scope="col">Examen</th>
              <th scope="col">Medico</th>
            </tr>
          </thead>
          <tbody>
              <%
                  ArrayList<String> examenesPendientes = new OpcionsEspecialesPaciente().GetExamenesPendientes(paciente);
                  if(examenesPendientes!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<examenesPendientes.size();indexResultados++){
                        %>
                        <td><%=examenesPendientes.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%6==0) && (indexResultados != (examenesPendientes.size()-1)) && (indexResultados!=0)){
                        %>
                        </tr><!-- Agregamos una tupla mas -->
                        <tr>
                        <%
                        }if(indexResultados == (examenesPendientes.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table></br>
    
        <a href="../HTML/StartSession.jsp">Regresar</a>
     
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
