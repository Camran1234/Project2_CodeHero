<%-- 
    Document   : adminModifyLaboratorista
    Created on : Oct 1, 2020, 11:18:09 AM
    Author     : camran1234
--%>

<%@page import="SQL.LectorTuplas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String codigoL = request.getParameter("opcionCodigo");
    ArrayList<String> tuplaCodigoL = new LectorTuplas().GetTuplaLaboratorista(codigoL);
    %>
<form action="../addModifyData" method="post"><!-- Esta parte mostrara una interfaz del medico -->
                <h1>Laboratorista</h1>
                <label>
                    Codigo: 
                    <input type="text"  name="codigoM" value="<%=codigoL%>" readonly/> <br>
                </label>
                <label>
                    Nombre: 
                    <input type="text"  name="nombre" placeholder="Ingrese nombre laboratorista" required/> <br>
                </label>
                <label>
                    Password: 
                    <input type="text"  name="password" placeholder="Ingrese password laboratorista" required/> <br>
                </label>
                <label>
                    Numero Registro de Salud
                    <input type="text"  name="registro" placeholder="Ingrese numero registro laboratorista" required/> <br>
                </label>
                <label>
                    DPI: 
                    <input type="text"  name="dpi" placeholder="Ingrese DPI laboratorista" required/> <br>
                </label>
                <label>
                    Telefono: 
                    <input type="text"  name="telefono" placeholder="Ingrese telefono laboratorista" required/> <br>
                </label>
                <label>
                    Examen especializado:  
                    <input type="text"  name="examenTrabajo" placeholder="Ingrese examen laboratorista" required/> <br>
                </label>
                <div>
                    <label>
                        Dias Trabajados:
                        <input type="checkbox" name="Lunes" value="Lunes" />Lunes
                        <input type="checkbox" name="Martes" value="Martes" />Martes
                        <input type="checkbox" name="Miercoles" value="Miercoles" />Miercoles
                        <input type="checkbox" name="Jueves" value="Jueves" />Jueves
                        <input type="checkbox" name="Viernes" value="Viernes" />Viernes
                        <input type="checkbox" name="Sabado" value="Sabado" />Sabado
                        <input type="checkbox" name="Domingo" value="Domingo" />Domingo
                        <br /> 
                    </label>
                </div>
                <label>
                    Correo Electronico:  
                    <input type="text"  name="correo" placeholder="Ingrese correo laboratorista" required/> <br>
                </label>
                <label>
                    Fecha donde inicio el trabajo: 
                    <input type="date" name="fechaInicio" value="2000-01-01" min="1900-01-01" max="2030-01-01">
                </label>
                <input type="submit"  name = "boton" value = "Modificar Laboratorista"/> <br>
</form>

<h3> Datos en el Sistema </h3> 
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Codigo</th>  
      <th scope="col">Nombre</th>
      <th scope="col">Password</th>
      <th scope="col">Numero Registro Ministerio Salud</th>
      <th scope="col">DPI</th>
      <th scope="col">Telefono</th>
      <th scope="col">Examen trabajo</th>
      <th scope="col">Dias Trabajo</th>
      <th scope="col">Correo Electronico</th>
      <th scope="col">Fecha Inicio</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <%
        for(int indexTupla=0;indexTupla<tuplaCodigoL.size();indexTupla++){
            %><td><%=tuplaCodigoL.get(indexTupla)%></td><%
        }  
          %>
    </tr>
    <%
        
        %>
  </tbody>
</table>
