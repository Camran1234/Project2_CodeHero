<%-- 
    Document   : InicioMenu
    Created on : Oct 2, 2020, 10:00:13 AM
    Author     : camran1234
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
input.ex1 {
  padding: 15px;
}        
</style>
    
<form method = "POST" action="./UploadData" enctype="multipart/form-data">
    <input class="ex1" type="file" name = "file" accept='.xml' required<br>
    <input class="ex1" type="submit" value = "Cargar"/> <br>
    </br></br></br>
</form>

<div id="options" >
    <form method ="post" action="HTML/StartSession.jsp">
        <h3 style="text-align:center">INGRESAR SESION</h3>
        <input class="ex1" type="text" style="margin:0px auto; display:block;" name="codigo" placeholder="Ingrese su codigo de usuario" size="50%" required/> <br>
        <input class="ex1" type="password" style="margin:0px auto; display:block;" name="password" placeholder="Ingrese su password de usuario" size="50%" required/> <br>
        <input class="ex1" type="submit" style="margin:0px auto; display:block;" value = "Acceder"/> <br>
    </form>        
</div>