<%-- 
    Document   : AdminModifyExamen
    Created on : Oct 1, 2020, 11:48:17 AM
    Author     : camran1234
--%>

<%@page import="SQL.LectorTuplas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String codigoE = request.getParameter("opcionCodigo");
    ArrayList<String> tuplaCodigoE = new LectorTuplas().GetTuplaExamen(codigoE);
    %>
<form action="../AddModifyData" method="post"><!-- Esta parte mostrara una interfaz del medico -->
                <h1>Examen Laboratorio</h1>
                <label>
                    Codigo: 
                    <input type="text"  name="codigoM" value="<%=codigoE%>" readonly/> <br>
                </label>
                <label>
                    Nombre: 
                    <input type="text"  name="nombre" placeholder="Ingrese nombre examen" required/> <br>
                </label>
                <label>
                    Requerimiento_Orden 
                    <select name="requerimientoOrden"  required>
                        <option value="TRUE">SI</option>
                        <option value="FALSE">NO</option>
                    </select>
                </label>
                <label>
                    Descripcion:
                    <input type="text"  name="descripcion" placeholder="Ingrese descripcion examen"  required/> <br>
                </label>
                <label>
                    Costo: 
                    <input type="text"  name="costo" placeholder="Ingrese Costo examen" required/> <br>
                </label>
                <label>
                    Informe
                    <input type="text"  name="informe" placeholder="Ingrese formato informe examen" required/> <br>
                </label>
                <input type="submit"  name = "boton" value = "Modificar Examen"/> <br>
</form>

<h3> Datos en el Sistema </h3> 
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Codigo</th>  
      <th scope="col">Nombre</th>
      <th scope="col">Requerimiento_Orden</th>
      <th scope="col">Descripcion</th>
      <th scope="col">Costo</th>
      <th scope="col">Informe</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <%
        for(int indexTupla=0;indexTupla<tuplaCodigoE.size();indexTupla++){
            %><td><%=tuplaCodigoE.get(indexTupla)%></td><%
        }  
          %>
    </tr>
    <%
        
        %>
  </tbody>
</table>
