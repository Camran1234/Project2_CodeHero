<%-- 
    Document   : BuscarMedicoByName
    Created on : Oct 2, 2020, 6:16:49 PM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="SQL_Reportes.OpcionesBuscarMedico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Obtenemos el nombre del medico que buscara, si por defecto es nullo entonces mostrara una tabla con todos los datos
    session.setAttribute("urlRetornoCita", "../Data/BuscarMedicoByName.jsp");
    String nombreMedico = request.getParameter("buscar");
    if(nombreMedico==null){
        nombreMedico = "";
    }
    ArrayList<String> listaMedicosTuplasByNombre = new OpcionesBuscarMedico().SearchMedicByName(nombreMedico);
    
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <h3> CONSULTAS:</h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">Codigo</th>
              <th scope="col">Tipo</th>
              <th scope="col">Costo</th>
            </tr>
          </thead>
          <tbody>
              <%
                  ArrayList<String> resultadoConsultas = new OpcionesBuscarMedico().GetTuplasConsultas();
                  if(resultadoConsultas!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<resultadoConsultas.size();indexResultados++){
                        %>
                        <td><%=resultadoConsultas.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%3==0) && (indexResultados != (resultadoConsultas.size()-1)) && (indexResultados!=0)){
                        %>
                        </tr><!-- Agregamos una tupla mas -->
                        <tr>
                        <%
                        }if(indexResultados == (resultadoConsultas.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table></br>
        
        <form method="get" action="../Data/BuscarMedicoByName.jsp">        
            <div class="form-group">
                    <h3>Buscar Medico Por Nombre:</h3>
                    <label>Nombre Medico</label>
                    <input type="text" name="buscar" palceholder="Ingrese nombre medico para buscar"/>
                    <input type="submit" value="Buscar"/>
                </div>
        </form>
        
        <%@ include file = "../HTML/FormIngresarCitas.jsp"%>
        
        <h3> MEDICOS: </h3>
        <table class="table table-borderless table-dark">
          <thead>
            <tr>
              <th scope="col">Codigo</th>
              <th scope="col">Nombre</th>
              <th scope="col">Correo_Electronico</th>
              <th scope="col">Hora Entrada</th>
              <th scope="col">Hora Salida</th>
              <th scope="col">Fecha Inicio</th>
              <th scope="col">Especialidades</th>
            </tr>
          </thead>
          <tbody>
              <%
                  if(listaMedicosTuplasByNombre!=null){
                      %>
                      <tr>
                      <%
                    for(int indexResultados=0;indexResultados<listaMedicosTuplasByNombre.size();indexResultados++){
                        %>
                        <td><%=listaMedicosTuplasByNombre.get(indexResultados)%></td>
                        <%
                        if(((indexResultados+1)%7==0) && (indexResultados != (listaMedicosTuplasByNombre.size()-1)) && (indexResultados!=0)){
                        %>
                        </tr><!-- Agregamos una tupla mas -->
                        <tr>
                        <%
                        }if(indexResultados == (listaMedicosTuplasByNombre.size()-1)){
                        %>
                      </tr>
                        <%
                        }
                    }
                  }
                  %>
          </tbody>
        </table></br>

<%--Esto es el codigo que indica el cambio de paginas y la pesta;a de abajo--%>        
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Anterior</a>
              </li>
              <li class="page-item active" aria-current="page">
                <span class="page-link">
                  1
                  <span class="sr-only">(current)</span>
                </span>
              </li>    
              <li class="page-item"><a class="page-link" href="../Data/BuscarMedicoByEspecialidad.jsp">2</a></li>
              <li class="page-item"><a class="page-link" href="../Data/BuscarMedicoByDate.jsp">3</a></li>
              <li class="page-item">
                <a class="page-link" href="../Data/BuscarMedicoByEspecialidad.jsp">Siguiente</a>
              </li>
            </ul>
        </nav>
       
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>   
    </body>
</html>
