<%-- 
    Document   : AdminModifyPaciente
    Created on : Oct 1, 2020, 7:07:21 AM
    Author     : camran1234
--%>

<%@page import="SQL.LectorTuplas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String codigoP = request.getParameter("opcionCodigo");
    ArrayList<String> tuplaCodigoP = new LectorTuplas().GetTuplaPaciente(codigoP);
    %>
<form action="../AddModifyData" method="post"><!-- Esta parte mostrara una interfaz del medico -->
                <h1>Paciente</h1>
                <label>
                    Codigo: 
                    <input type="text"  name="codigoM" value="<%= codigoP %>" readonly/> <br>
                </label>
                <label>
                    Nombre: 
                    <input type="text"  name="nombre" placeholder="Ingrese nombre paciente" required/> <br>
                </label>
                <label>
                    Password: 
                    <input type="text"  name="password" placeholder="Ingrese password paciente" required/> <br>
                </label>
                <label>
                    Sexo:
                    <input type="text"  name="sexo" placeholder="Ingrese genero paciente" required/> <br>
                </label>
                <label>
                    DPI: 
                    <input type="text"  name="dpi" placeholder="Ingrese DPI paciente" required/> <br>
                </label>
                <label>
                    Fecha Nacimiento:
                    <input type="date" name="fechaNacimiento" value="2000-01-01" min="1900-01-01" max="2050-01-01">
                </label>
                <label>
                    Telefono: 
                    <input type="text"  name="telefono" placeholder="Ingrese telefono paciente" required/> <br>
                </label>
                <label>
                    Peso:
                    <input type="text"  name="peso" placeholder="Ingrese peso paciente" required/> <br>
                </label>
                <label>
                    Tipo Sangre:
                    <input type="text"  name="sangre" placeholder="Ingrese tipo sangre paciente" required/> <br>
                </label>
                <label>
                    Correo Electronico:  
                    <input type="text"  name="correo" placeholder="Ingrese correo paciente" required/> <br>
                </label>
                <input type="submit"  name = "boton" value = "Modificar Paciente"/> <br>
</form>

<h3> Datos en el Sistema </h3> 
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Codigo</th>  
      <th scope="col">Nombre</th>
      <th scope="col">Password</th>
      <th scope="col">Sexo</th>
      <th scope="col">DPI</th>
      <th scope="col">Fecha Nacimiento</th>
      <th scope="col">Telefono</th>
      <th scope="col">Peso</th>
      <th scope="col">Tipo Sangre</th>
      <th scope="col">Correo Electronico</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <%
        for(int indexTupla=0;indexTupla<tuplaCodigoP.size();indexTupla++){
            %><td><%=tuplaCodigoP.get(indexTupla)%></td><%
        }  
          %>
    </tr>
    <%
        
        %>
  </tbody>
</table>

