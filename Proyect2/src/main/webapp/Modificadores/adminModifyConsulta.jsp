<%-- 
    Document   : adminModifyConsulta
    Created on : Oct 1, 2020, 11:55:27 AM
    Author     : camran1234
--%>

<%@page import="SQL.LectorTuplas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String codigoC = request.getParameter("opcionCodigo");
    ArrayList<String> tuplaCodigoC = new LectorTuplas().GetTuplaConsulta(codigoC);
    %>
<form action="../addModifyData" method="post"><!-- Esta parte mostrara una interfaz del medico -->
                <h1>Examen Laboratorio</h1>
                <label>
                    Codigo: 
                    <input type="text"  name="codigoM" value="<%=codigoC%>" readonly/> <br>
                </label>
                <label>
                    Costo:
                    <input type="text"  name="costo" placeholder="Ingrese costo consulta" required/> <br>
                </label>
                <input type="submit"  name = "boton" value = "Modificar Consulta"/> <br>
</form>

<h3> Datos en el Sistema </h3> 
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Codigo</th>  
      <th scope="col">Tipo</th>
      <th scope="col">Costo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <%
        for(int indexTupla=0;indexTupla<tuplaCodigoC.size();indexTupla++){
            %><td><%=tuplaCodigoC.get(indexTupla)%></td><%
        }  
          %>
    </tr>
    <%
        
        %>
  </tbody>
</table>
