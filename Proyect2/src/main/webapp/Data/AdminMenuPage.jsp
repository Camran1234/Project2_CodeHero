<%-- 
    Document   : AdminMenuPage
    Created on : Sep 30, 2020, 8:00:38 AM
    Author     : camran1234
--%>
<style>
input.ex1 {
  padding: 15px;
}        
</style>
<h3 style="text-align:center">ADMINISTRADOR</h3>

    <form method ="post" action="../Data/AdminAddPage.jsp">    
        <select name="opcionIngresarDato" style="margin:0px auto; display:block;" required>
                    <option value="medico">Medico</option>
                    <option value="laboratorista">Laboratorista</option>
                    <option value="paciente">Paciente</option>
                    <option value="examenLaboratorio">Examen Laboratorio</option>
        </select>
        <input class="ex1" type="submit" style="margin:0px auto; display:block;" name="Agregar" value = "Agregar Dato" size="50%" required/> <br>
    </form>            

<form method="post" action="../Modificadores/AdminModifyPage.jsp">
    <select name="opcionModificarDato" style="margin:0px auto; display:block;" required>
                    <option value="medico">Medico</option>
                    <option value="laboratorista">Laboratorista</option>
                    <option value="paciente">Paciente</option>
                    <option value="examenLaboratorio">Examen Laboratorio</option>    
                    <option value="consulta">Costo Consulta</option>
    </select>
    <input type="submit" class="ex1" style="margin:0px auto; display:block;" name="Modificar" value="Modificar Dato" size="50%" required/><br>
</form>
        
<form method="post" action="../HTML/StartSession.jsp">
    <input class="ex1" type="submit" style="margin:0px auto; display:block;" value = "Reportes"/> <br>
</form>
<a href="../menuInicio.jsp">Regresar</a>
        
    
