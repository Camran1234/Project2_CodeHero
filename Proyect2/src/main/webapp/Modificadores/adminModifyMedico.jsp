<%-- 
    Document   : adminModifyMedico
    Created on : Oct 1, 2020, 11:11:12 AM
    Author     : camran1234
--%>

<%@page import="SQL.LectorTuplas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String codigoM = request.getParameter("opcionCodigo");
    ArrayList<String> tuplaCodigoM = new LectorTuplas().GetTuplaMedico(codigoM);
    %>
<form action="../addModifyData" method="post"><!-- Esta parte mostrara una interfaz del medico -->
                <h1>Medico</h1>
                <label>
                    Codigo: 
                    <input type="text"  name="codigoM" value="<%=codigoM%>" readonly/> <br>
                </label>
                <label>
                    Nombre: 
                    <input type="text"  name="nombre" placeholder="Ingrese nombre medico" required/> <br>
                </label>
                <label>
                    Password: 
                    <input type="text"  name="password" placeholder="Ingrese password medico" required/> <br>
                </label>
                <label>
                    Numero Colegiado:
                    <input type="text"  name="colegiado" placeholder="Ingrese numero colegiado medico" required/> <br>
                </label>
                <label>
                    DPI: 
                    <input type="text"  name="dpi" placeholder="Ingrese DPI medico" required/> <br>
                </label>
                <label>
                    Telefono: 
                    <input type="text"  name="telefono" placeholder="Ingrese telefono medico" required/> <br>
                </label>
                <label>
                    Correo Electronico:  
                    <input type="text"  name="correo" placeholder="Ingrese correo medico" required/> <br>
                </label>
                <label>
                    Horario Entrada: 
                    <input id="appt-time" type="time" name="horaEntrada" >
                </label>`
                <label>
                    Horario Salida:  
                    <input id="appt-time" type="time" name="horaSalida" >
                </label>
                <label>
                    Fecha donde empezo el trabajo:
                    <input type="date" name="fechaTrabajo" value="2000-01-01" min="1900-01-01" max="2030-01-01">
                </label>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Especialidades</label>
                    <textarea class="form-control" name="especialidades" rows="4" placeholder="Ingrese especialidades en este cuadro, cada especialidad 
                              en diferente fila"></textarea>
                </div>
                <input type="submit"  name = "boton" value = "Modificar Medico"/> <br>
</form>

<h3> Datos en el Sistema </h3> 
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Codigo</th>  
      <th scope="col">Nombre</th>
      <th scope="col">Password</th>
      <th scope="col">Numero Colegiado</th>
      <th scope="col">DPI</th>
      <th scope="col">Telefono</th>
      <th scope="col">Correo Electronico</th>
      <th scope="col">Horario Entrada</th>
      <th scope="col">Horario Salida</th>
      <th scope="col">Fecha Inicio</th>
      <th scope="col">Especialidades</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <%
        for(int indexTupla=0;indexTupla<tuplaCodigoM.size();indexTupla++){
            %><td><%=tuplaCodigoM.get(indexTupla)%></td><%
        }  
          %>
    </tr>
    <%
        
        %>
  </tbody>
</table>