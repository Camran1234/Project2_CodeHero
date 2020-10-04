<%-- 
    Document   : ReporteUltimosExamenes
    Created on : Oct 4, 2020, 6:55:12 AM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.PacienteQuery"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%                  
    String codigoPaciente = (String) session.getAttribute("codigoSession");
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Ultimos Examenes Paciente Page</title>
    </head>
    <body>
        <h1>5 Ultimos Examenes de Laboratorio Realizados</h1>
        <h3> Examenes: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">No. Registro</th>
              <th scope="col">Orden Medico</th>
              <th scope="col">Informe</th>
              <th scope="col">Fecha</th>
              <th scope="col">Hora</th>
              <th scope="col">Paciente</th>
              <th scope="col">Medico</th>
              <th scope="col">Examen</th>
              <th scope="col">Laboratorista Encargado</th>
            </tr>
          </thead>
          <tbody>
              <%
                  ArrayList<String> examenesPendientes = new PacienteQuery().ReporteUltimosExamenes(codigoPaciente);
                  if(examenesPendientes!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<examenesPendientes.size();indexResultados++){
                        %>
                        <td><%=examenesPendientes.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%9==0) && (indexResultados != (examenesPendientes.size()-1)) && (indexResultados!=0)){
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
        
        
        <%--Esto es para la navecgacion, y acceder a los distintos tipos de reportes--%>
        <a href="../HTML/StartSession.jsp">Regresar</a>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a class="page-link" href="../Reportes/ReporteUltimosExamenesP.jsp" tabindex="-1" aria-disabled="true">Anterior</a>
              </li>
              <li class="page-item active" aria-current="page">
                <span class="page-link">
                  1
                  <span class="sr-only">(current)</span>
                </span>
              </li>    
              <li class="page-item"><a class="page-link" href="../Reportes/ReporteConsultasMedicoEspecificoP.jsp">2</a></li>
              <li class="page-item"><a class="page-link" href="../Reportes/ReporteExamenesTipoEspecificoP.jsp">3</a></li>
              <li class="page-item"><a class="page-link" href="../Reportes/ReporteUltimasConsultasP.jsp">4</a></li>
              <li class="page-item">
                <a class="page-link" href="../Reportes/ReporteConsultasMedicoEspecificoP.jsp">Siguiente</a>
              </li>
            </ul>
        </nav>
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
