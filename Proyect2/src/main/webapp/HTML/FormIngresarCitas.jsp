<%-- 
    Document   : FormIngresarCitas
    Created on : Oct 2, 2020, 6:50:17 PM
    Author     : camran1234
--%>

<%@page import="SQL_Reportes.OpcionesBuscarMedico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SQL.LectorUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LectorUsuarios lector = new LectorUsuarios();
    String pacienteCodigo = (String) session.getAttribute("codigoSession");
    %>
    
    <form method="post" action="../Data/IngresarCita.jsp">
        <h4>Registrar Cita</h4>
        <div class="form-group">
            
         <div class="form-group">
                <label>Paciente</label>
                <input type="text" class="form-control" name="nombrePaciente" value=<%=pacienteCodigo%>  readonly />
        </div>
                <label>Codigo Medico</label>
                <select name="opcionListaMedico" class="form-group"  required>
                        <%
                            ArrayList<String> listaMedicosCodigos = lector.GetCodigoMedicos();
                            if(listaMedicosCodigos.size()!=0){
                                for(String listaMedicosCodigo:listaMedicosCodigos){
                                %>
                                <option value="<%=listaMedicosCodigo%>"><%=listaMedicosCodigo%></option>
                                <%
                                }
                            }else{
                                %>
                                <option>SIN MEDICOS DISPONIBLES</option>
                                <%
                            }

                        %>
                </select>
        </div>
        <div class="form-group">
                <label>Nombre Registro Cita</label>
                <input type="text" class="form-control" name="registro" placeholder="Numero Registro Cita" required>
        </div>
        <div class="form-group">
                <label>Fecha Cita</label>
                <input type="date" name="fecha" value="2000-01-01" min="1900-01-01" max="2050-01-01" required>
        </div>
        <div class="form-group">
                <label>Hora Cita</label>
                <input type="time" name="hora" required>
        </div>
        <div class="form-group">
                <label>Consulta</label>
                <select name="opcionListaConsulta" class="form-group"  required>
                        <%
                            ArrayList<String> listaConsultaCodigos = lector.GetCodigoConsultas();
                            if(listaConsultaCodigos.size()!=0){
                                for(String listaConsultaCodigo:listaConsultaCodigos){
                                %>
                                <option value="<%=listaConsultaCodigo%>"><%=listaConsultaCodigo%></option>
                                <%
                                }
                            }else{
                                %>
                                <option>SIN CONSULTAS DISPONIBLES</option>
                                <%
                            }

                        %>
                </select>
                <input type="submit" value="Pedir Cita">
        </div>
        <a href="../HTML/StartSession.jsp">Regresar</a>        
                
</form>
