<%-- 
    Document   : PacienteMenuPage
    Created on : Oct 2, 2020, 5:29:10 PM
    Author     : camran1234
--%>

<%@page import="SQL.LectorUsuarios"%>
<%@page import="java.util.ArrayList"%>
<%                
    //Colocamos la url donde redireccionara en el formulario de registrar examen
    String url3 = "./Data/CrearOrden.jsp";
    session.setAttribute("paginaRedireccionar", url3);
    session.setAttribute("selectMedico", true );
    ArrayList<String> listaDatos ;
    LectorUsuarios lectorNuevoNuevo = new LectorUsuarios();
    %>

<form method="post" action="../Data/HistorialMedicoPaciente.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Ver Historia Medico</h4>
        <label>Codigo: </label>
        <input type="text" class="form-control" name="opcionIngresarDato" value="<%=(String)session.getAttribute("codigoSession")%>" readonly/>
    </div>
    <input type="submit" class="ex1" style="margin:0px auto; display:block;" name="boton" value="Ver historial" size="50%" required/><br>
</form>
    
<form method="post" action="../Data/BuscarMedicoByName.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Asignar Cita</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
</form>
    
<form method="post" action="../Data/PacientesMostrarPendientes.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Ver Citas y Examenes Pendientes</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
</form>
    
    <form method="post" action="../Reportes/ReporteUltimosExamenesP.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Reportes</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
    </form>
    
    <form method="post" action="../UploadData" >
    <div class="form-group">
        <h4 style="text-align:center">Subir Requerimiento Examen</h4>
        <label> Pacientes Asignados</label>
        <input class="form-control" type="text"  name="opcionListaR" value="<%=session.getAttribute("codigoSession")%>">
   
    </div>
        <div class="form-group">
        <label> Examenes</label>
        <select name="opcionExamenesR" class="form-group"  required>
                <%
                    listaDatos = lectorNuevoNuevo.GetCodigoExamenes();
                    
                    if(listaDatos.size()!=0){
                        for(String listaMedico:listaDatos){
                        %>
                        <option value="<%=listaMedico%>"><%=listaMedico%></option>
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
        <label> Medico</label>
        <select name="opcionMedico" class="form-group"  required>
                <%
                    listaDatos = lectorNuevoNuevo.GetCodigoMedicos();
                    
                    if(listaDatos.size()!=0){
                        for(String listaMedico:listaDatos){
                        %>
                        <option value="<%=listaMedico%>"><%=listaMedico%></option>
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

    
<a href="../menuInicio.jsp">Regresar</a>
