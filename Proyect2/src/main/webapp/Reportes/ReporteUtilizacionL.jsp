<%-- 
    Document   : ReporteUtilizacionL
    Created on : Oct 4, 2020, 10:38:23 AM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.LaboratoristaQuery"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String codigoLaboratorista = (String) session.getAttribute("codigoSession");
    String fechaInicial =  request.getParameter("fechaInicial");
    String fechaFinal =  request.getParameter("fechaFinal");
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title> Reporte de utilización de cada día de trabajo Page</title>
    </head>
    <body>
        <h1>Reporte De Utilización De Cada Día De Trabajo</h1>
        
        <form method="get" action="../Reportes/ReporteUtilizacionL.jsp">        
            <div class="form-group">
                    <h3>Buscar Por Intervalo Tiempo:</h3>
                    <label>Fecha Inicio</label>
                    <input type="date" name="fechaInicial" value= <%=fechaInicial%> min="1900-01-01" max="2100-01-01" required>
                    <label>Fecha Final</label>
                    <input type="date" name="fechaFinal" value=<%=fechaFinal%> min="1900-01-01" max="2100-01-01" required>
                    <input type="submit" value="Buscar"/>
                </div>
        </form>
        
        <h3> Examenes: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">No. Registro</th>
              <th scope="col">Orden</th>
              <th scope="col">Informe</th>
              <th scope="col">Fecha</th>
              <th scope="col">Hora</th>
              <th scope="col">Paciente</th>
              <th scope="col">Examen</th>
              <th scope="col">Medico</th>
            </tr>
          </thead>
          <tbody>
              <%
                  //Solo devolver las consultas (citas en progreso)
                  ArrayList<String> examenesPendientes = new LaboratoristaQuery().ReporteExamenesRealizados(codigoLaboratorista, fechaInicial, fechaFinal);
                  if(examenesPendientes!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<examenesPendientes.size();indexResultados++){
                        %>
                        <td><%=examenesPendientes.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%8==0) && (indexResultados != (examenesPendientes.size()-1)) && (indexResultados!=0)){
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
        
        
        <%--Estos son los enlaces para cambiar de paginas--%>
        <a href="../HTML/StartSession.jsp">Regresar</a>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              
              <li class="page-item">
                <a class="page-link" href="../Reportes/ReporteFechasCargadasL.jsp">Anterior</a>
              </li>
              
              <li class="page-item"><a class="page-link" href="../Reportes/ReporteExamenesRealizadosDiaL.jsp">1</a></li>
              
              <li class="page-item"><a class="page-link" href="../Reportes/ReporteFechasCargadasL.jsp">2</a></li>
              
              <li class="page-item active" aria-current="page">
                <span class="page-link">
                  3
                  <span class="sr-only">(current)</span>
                </span>
              </li>    
              
              <li class="page-item"><a class="page-link" href="../Reportes/ReportesLaboratoristaDiaL.jsp">4</a></li>
              <li class="page-item">
                <a class="page-link" href="../Reportes/ReportesLaboratoristaDiaL.jsp">Siguiente</a>
              </li>
            </ul>
        </nav>
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
