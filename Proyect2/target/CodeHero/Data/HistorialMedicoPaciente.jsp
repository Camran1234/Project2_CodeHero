<%-- 
    Document   : HistorialMedicoPaciente
    Created on : Oct 1, 2020, 3:48:44 PM
    Author     : camran1234
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="SQL_Reportes.OpcionsEspecialesPaciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String codigoPaciente = request.getParameter("opcionIngresarDato");
    OpcionsEspecialesPaciente reportesP = new OpcionsEspecialesPaciente();
    ArrayList<String> resultados = null;
    ArrayList<String> citas = null;
    if(reportesP.EstablecerHistorialMedico(codigoPaciente)){
        resultados = reportesP.GetResultados();
        citas = reportesP.GetCitas();
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>historialMedico Page</title>
    </head>
    <body>
    
        <h3> Resultados: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">Fecha</th>
              <th scope="col">Hora</th>
              <th scope="col">Paciente</th>
              <th scope="col">Medico</th>
              <th scope="col">Examen</th>
              <th scope="col">Laboratorista</th>
            </tr>
          </thead>
          <tbody>
              <%
                  if(resultados!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<resultados.size();indexResultados++){
                        %>
                        <td><%=resultados.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%6==0) && (indexResultados != (resultados.size()-1)) && (indexResultados!=0)){
                        %>
                        </tr><!-- Agregamos una tupla mas -->
                        <tr>
                        <%
                        }if(indexResultados == (resultados.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table>
          
          <br><br><!-- Espacio para la otra tabla -->  
          
          <h3> Citas </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">Fecha</th>
              <th scope="col">Hora</th>
              <th scope="col">Paciente</th>
              <th scope="col">Medico</th>
              <th scope="col">Titulo</th>
              <th scope="col">Consulta</th>
            </tr>
          </thead>
          <tbody>
              <%
                  if(resultados!=null){
                      %>
                      <tr>
                      <%
                    for(int indexCitas=0;indexCitas<citas.size();indexCitas++){
                        %>
                        <td><%=citas.get(indexCitas)%></td>
                        <%
                        if(((indexCitas+1)%6==0) && (indexCitas != (resultados.size()-1)) && (indexCitas!=0)){
                        %>
                      </tr><!-- Agregamos una tupla mas -->
                      <tr>
                        <%
                        }if(indexCitas == (citas.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table>
        
    <a href="../HTML/StartSession.jsp">Regresar</a>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
