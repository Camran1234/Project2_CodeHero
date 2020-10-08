<%-- 
    Document   : AdminMenuPage
    Created on : Sep 30, 2020, 8:00:38 AM
    Author     : camran1234
--%>
<h3 style="text-align:center">ADMINISTRADOR</h3>

    <form method ="post" action="../Data/AdminAddPage.jsp">    
        <div class="form-group">
        <h4 style="text-align:center">Agregar Dato</h4>
        <select name="opcionIngresarDato" style="margin:0px auto; display:block;" required>
                    <option value="medico">Medico</option>
                    <option value="laboratorista">Laboratorista</option>
                    <option value="paciente">Paciente</option>
                    <option value="examenLaboratorio">Examen Laboratorio</option>
        </select>
        <input class="form-control" type="submit" style="margin:0px auto; display:block;" name="Agregar" value = "Agregar Dato" size="50%" required/> <br>
        </div>
        </form>            

<form method="post" action="../Modificadores/AdminModifyPage.jsp">
    <div class="form-group">
    <h4 style="text-align:center">Modificar Dato</h4>
    <select name="opcionModificarDato" style="margin:0px auto; display:block;" required>
                    <option value="medico">Medico</option>
                    <option value="laboratorista">Laboratorista</option>
                    <option value="paciente">Paciente</option>
                    <option value="examenLaboratorio">Examen Laboratorio</option>    
                    <option value="consulta">Costo Consulta</option>
    </select>
    <input type="submit" class="form-control" style="margin:0px auto; display:block;" name="Modificar" value="Modificar Dato" size="50%" required/><br>
    </div>
    
</form>
        
<form method="post" action="../Reportes/MedicosConMasInformesA.jsp">
    <div class="form-group">
        <h4 style="text-align:center">Reportes</h4>
        <input type="submit" class="form-control"  value="Ir"/>
    </div>
    </form>
<a href="../menuInicio.jsp">Regresar</a>
        
    
