<%-- 
    Document   : PacienteMenuPage
    Created on : Oct 2, 2020, 5:29:10 PM
    Author     : camran1234
--%>

<%                
    //Colocamos la url donde redireccionara en el formulario de registrar examen
    //String url3 = "./Data/CrearOrden.jsp";
    //session.setAttribute("paginaRedireccionar", url2);
    
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
    

    
<a href="../menuInicio.jsp">Regresar</a>
