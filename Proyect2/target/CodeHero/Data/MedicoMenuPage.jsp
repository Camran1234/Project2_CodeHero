<%@page import="java.util.ArrayList"%>
<%@page import="SQL.LectorUsuarios"%>
<style>
input.ex1 {
  padding: 10px;
}        
<%                
    LectorUsuarios lector = new LectorUsuarios();
    ArrayList<String> listaPacientes;
    //Colocamos la url donde redireccionara en el formulario de registrar examen
    String url2 = "./Data/CrearOrden.jsp";
    session.setAttribute("paginaRedireccionar", url2);
    %>
</style>
<h3 style="text-align:center">MEDICO</h3>

    <form method ="post" action="../Data/HistorialMedicoPaciente.jsp">    
        <h5 style="text-align:center">Ver Historial Medico</h5>
        <h6 style="text-align:center"> Paciente</h6>
        <select name="opcionIngresarDato" style="margin:0px auto; display:block;" required>
            <%
                listaPacientes = lector.GetCodigoPaciente();
                for(String listaPaciente:listaPacientes){
                    %>
                    <option value="<%=listaPaciente%>"><%=listaPaciente%></option>
                    <%
                }
            %>
        </select>
        <input class="ex1" type="submit" style="margin:0px auto; display:block;" name="boton" value = "Mostrar" size="50%" required/> <br>
    </form>            

<form method="post" action="../Data/ConfirmarCita.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Subir Informe Cita</h4>
        <label> Citas Asignadas</label>
        <select class="form-group" name="opcionCitas" required>
                <%
                    ArrayList<String> listaCitas = lector.GetCodigoCitas((String)session.getAttribute("codigoSession"));
                    if(listaCitas.size()!=0){
                        for(String listaCita:listaCitas){
                        %>
                        <option value="<%=listaCita%>"><%=listaCita%></option>
                        <%
                        }
                    }else{
                        %>
                        <option>SIN CITAS</option>
                        <%
                    }
                    
                %>
        </select>
    </div>
    <div class="form-group">
        <label>Numero Registro</label>
            <input type="text" class="form-control" name="registro" placeholder="Numero Registro Informe" required>
    </div>
    <div class="form-group">
        <label>Descripcion</label>
        <input type="textarea" class="form-control" name="descripcion" placeholder="Numero Registro Informe" required>
    </div>
    <div class="form-group">
        <label>Fecha</label>
        <input type="date" name="fecha" value="2000-01-01" min="1900-01-01" max="2050-01-01" required>
    </div>
    <div class="form-group">
        <label>Hora</label>
        <input type="time" name="hora" required>
    </div>
    <input type="submit" class="ex1" style="margin:0px auto; display:block;" name="boton" value="Completar Cita" size="50%" required/><br>
</form> 
        
<form method="post" action="../UploadData" >
    <div class="form-group">
        <h4 style="text-align:center">Subir Requerimiento Examen</h4>
        <label> Pacientes Asignados</label>
        <select name="opcionListaR" class="form-group"  required>
                <%
                    listaPacientes = lector.GetCodigoPaciente();
                    
                    if(listaPacientes.size()!=0){
                        for(String listaPaciente:listaPacientes){
                        %>
                        <option value="<%=listaPaciente%>"><%=listaPaciente%></option>
                        <%
                        }
                    }else{
                        %>
                        <option>SIN PACIENTES</option>
                        <%
                    }
                    
                %>
        </select>
    </div>
        <div class="form-group">
        <label> Examenes</label>
        <select name="opcionExamenesR" class="form-group"  required>
                <%
                    listaPacientes = lector.GetCodigoExamenes();
                    
                    if(listaPacientes.size()!=0){
                        for(String listaPaciente:listaPacientes){
                        %>
                        <option value="<%=listaPaciente%>"><%=listaPaciente%></option>
                        <%
                        }
                    }else{
                        %>
                        <option>SIN EXAMENES</option>
                        <%
                    }
                    
                %>
        </select>
    </div>
        <div class="form-group">
        <label>Orden</label>
        <input class="form-control" type="file"  name="file" accept=".pdf">
    </div>
    <div class="form-group">
        <label>Numero Registro</label>
            <input class="form-control" type="text"  name="registroR" placeholder="Numero Registro Informe" required>
    </div>
    <div class="form-group">
        <label>Fecha</label>
        <input type="date" name="fechaR" value="2000-01-01" min="1900-01-01" max="2050-01-01" required>
    </div>
    <div class="form-group">
        <label>Hora</label>
        <input type="time" name="horaR" required>
        <input type="submit" class="ex1" style="margin:0px auto; display:block;" name="boton3" value="Completar Orden" size="50%" required/><br>
    </div>  
    
</form>
        
<form method="post" action="../Reportes/ReporteCitasAgendadasM.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Reportes</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
</form>
<a href="../menuInicio.jsp">Regresar</a>
