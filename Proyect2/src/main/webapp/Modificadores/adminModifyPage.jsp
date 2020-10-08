<%-- 
    Document   : adminModifyPage
    Created on : Oct 1, 2020, 7:04:33 AM
    Author     : camran1234
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="SQL.LectorUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    LectorUsuarios lector = new LectorUsuarios();
    ArrayList<String> lista;
    String opcion = request.getParameter("opcionModificarDato");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Page</title>
    </head>
    <body>
        <form method ="GET" action="../Modificadores/adminModifySelect.jsp">
        <%--Utilizaremos esta etiqueta para poder usarla como indicador de que tabla buscar--%>
        <h1 nombre="etiqueta" value=<%=opcion%> style="text-align:center"><%=opcion%></h1>    
        <select name="opcionCodigo" style="margin:0px auto; display:block;" required>
<%  switch(opcion){
        case "medico":
            %>
            <%
            lista = lector.GetCodigoMedicos();
            
            for(int indexLista=0;indexLista<lista.size();indexLista++){
                %>
            <option value="<%=lista.get(indexLista)%>"><%=lista.get(indexLista)%></option>
            <%
            }
                %>
            </select><br>
            <input type="submit" name = "boton"value="Seleccionar Medico" style="margin:0px auto; display:block;"/><br>
                <%
            break;    
        case "laboratorista":
            %>
            <%
            lista = lector.GetCodigoLaboratoristas();    
            for(int indexLista=0;indexLista<lista.size();indexLista++){
                %>
            <option value="<%=lista.get(indexLista)%>"><%=lista.get(indexLista)%></option>
            <%
            }
                %>
            </select><br>
            <input type="submit" name = "boton"value="Seleccionar Laboratorista" style="margin:0px auto; display:block;"/><br>
                <%
            break;    
        case "paciente":
            %>
            <%
            lista = lector.GetCodigoPaciente();
                
            for(int indexLista=0;indexLista<lista.size();indexLista++){
                %>
            <option value="<%=lista.get(indexLista)%>"><%=lista.get(indexLista)%></option>
            <%
            }
                %>
            </select><br>
            <input type="submit" name = "boton"value="Seleccionar Paciente" style="margin:0px auto; display:block;"/><br>
                <%
            break;    
        case "examenLaboratorio":
            %>
            <%
            lista = lector.GetCodigoExamenes();
                
            for(int indexLista=0;indexLista<lista.size();indexLista++){
            %>
            <option value="<%=lista.get(indexLista)%>"><%=lista.get(indexLista)%></option>
            <%
            }
                %>
            </select><br>
            <input type="submit" name = "boton"value="Seleccionar Examen" style="margin:0px auto; display:block;"/><br>
                <%
            break;
        case "consulta":
            %>
            <%
            lista = lector.GetCodigoConsultas();
            
            for(int indexLista=0;indexLista<lista.size();indexLista++){
                %>
            <option value="<%=lista.get(indexLista)%>"><%=lista.get(indexLista)%></option>
            <%
            }
                %>
            </select><br>
            <input type="submit" name = "boton"value="Seleccionar Consulta" style="margin:0px auto; display:block;"/><br>
                <%
            break;
    }
    %>
        </form>
    <a href="../HTML/startSession.jsp">Regresar</a>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
