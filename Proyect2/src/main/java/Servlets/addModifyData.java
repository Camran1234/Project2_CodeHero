/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Datos.ExamenLaboratorio;
import Datos.Laboratorista;
import Datos.Medico;
import Datos.Paciente;
import SQL.ModificadorUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camran1234
 */
@WebServlet("/addModifyData")
public class addModifyData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * This method will be used for uploading data to 
     * a sql database
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Analizamos el valor del boton para saber que dato subiremos
            //luego empezamos a generar metodos de subida de esos archivos
            //y luego 
            String opcionSubida = request.getParameter("boton");
            ModificadorUsuario modificador = new ModificadorUsuario();
            switch(opcionSubida){
                case "Modificar Examen":
                    String codigo = request.getParameter("codigoM");
                    String nombre = request.getParameter("nombre");
                    String requerimientoOrden = request.getParameter("requerimientoOrden");
                    Boolean order=false;
                    if(requerimientoOrden.equalsIgnoreCase("TRUE")){
                        order = true;
                    }else if (requerimientoOrden.equalsIgnoreCase("FALSE")){
                        order = false;
                    }
                    String descripcion = request.getParameter("descripcion");
                    String costo = request.getParameter("costo");
                    String informe = request.getParameter("informe");
                    if(modificador.ModificarExamenLaboratorio(codigo, nombre, order, descripcion, Double.parseDouble(costo), informe)){
                        out.println("<h1> Se Actualizo el examen de codigo "+codigo+"</h1>");
                    }else{
                        out.println("<h1> Formatos Incorrectos en el examen de codigo "+codigo+"</h1>");
                    }
                    break;    
                case "Modificar Laboratorista":
                    String codigoL = request.getParameter("codigoM");
                    String nombreL = request.getParameter("nombre");
                    Long dpiL = Long.parseLong(request.getParameter("dpi"));
                    int telefonoL = Integer.parseInt(request.getParameter("telefono"));
                    String examenL = request.getParameter("examenTrabajo");
                    String correoL = request.getParameter("correo");
                    ArrayList<String> diasTrabajo = new ArrayList<>();
                    if(request.getParameter("Lunes") !=null){
                        diasTrabajo.add(request.getParameter("Lunes"));
                    }if(request.getParameter("Martes") !=null){
                        diasTrabajo.add(request.getParameter("Martes"));
                    }if(request.getParameter("Miercoles") !=null){
                        diasTrabajo.add(request.getParameter("Miercoles"));
                    }if(request.getParameter("Jueves") !=null){
                        diasTrabajo.add(request.getParameter("Jueves"));
                    }if(request.getParameter("Viernes") !=null){
                        diasTrabajo.add(request.getParameter("Viernes"));
                    }if(request.getParameter("Sabado") !=null){
                        diasTrabajo.add(request.getParameter("Sabado"));
                    }if(request.getParameter("Domingo") !=null){
                        diasTrabajo.add(request.getParameter("Domingo"));
                    }
                    String trabajoL = request.getParameter("fechaInicio");
                    String passwordL = request.getParameter("password");
                    String registroL = request.getParameter("registro");
                    if(modificador.ModificarLaboratorista(codigoL, nombreL, dpiL, telefonoL, examenL, correoL, diasTrabajo, trabajoL,passwordL, registroL)){
                        out.println("<h1> Se Actualizo el laboratorista de codigo "+codigoL+"</h1>");
                    }else{
                        out.println("<h1> Formatos Incorrectos en el laboratorista de codigo "+codigoL+"</h1>");
                    }
                    break;    
                case "Modificar Medico":
                    String codigoM = request.getParameter("codigoM");
                    String nombreM = request.getParameter("nombre");
                    Long dpiM = Long.parseLong(request.getParameter("dpi"));
                    int telefonoM = Integer.parseInt(request.getParameter("telefono"));
                    String colegiadoM = request.getParameter("colegiado");
                    ArrayList<String> especialidades = new ArrayList<>();
                    String especialidad = request.getParameter("especialidades");
                    String especialidadAux="";
                    for(int index=0;index<especialidad.length();index++){
                        if(especialidad.charAt(index) != '\n'){
                            especialidadAux += especialidad.charAt(index);
                        }
                        if(especialidad.charAt(index) == '\n' || (index==especialidad.length()-1)){
                            especialidades.add(especialidadAux);
                            especialidadAux = "";
                        }
                    }
                    String horarioInicio = request.getParameter("horaEntrada");
                    String horarioSalida = request.getParameter("horaSalida");
                    String fecha = request.getParameter("fechaTrabajo");
                    String correoM = request.getParameter("correo");
                    String passwordM = request.getParameter("password");
                    if(modificador.ModificarMedico(codigoM, nombreM, dpiM, telefonoM, colegiadoM, especialidades, horarioInicio, horarioSalida,
                            passwordM, correoM, fecha)){
                        out.println("<h1> Se Actualizo el medico de codigo "+codigoM+"</h1>");
                    }else{
                        out.println("<h1> Formatos Incorrectos en el medico de codigo "+codigoM+"</h1>");
                    }
                    break;    
                case "Modificar Paciente":
                    String codigoP = request.getParameter("codigoM");
                    String nombreP = request.getParameter("nombre");
                    String passwordP = request.getParameter("password");
                    String sexoP = request.getParameter("sexo");
                    Long dpiP = Long.parseLong(request.getParameter("dpi"));
                    int telefonoP = Integer.parseInt(request.getParameter("telefono"));
                    String fechaNacimientoP = request.getParameter("fechaNacimiento");
                    Double pesoP = Double.parseDouble(request.getParameter("peso"));
                    String tipoSangreP = request.getParameter("sangre");
                    String correoP = request.getParameter("correo");
                    if(modificador.ModificarPaciente(codigoP, nombreP, dpiP, telefonoP, sexoP, fechaNacimientoP, tipoSangreP, pesoP, passwordP, correoP)){
                        out.println("<h1> Se Actualizo el paciente de codigo "+codigoP+"</h1>");
                    }else{
                        out.println("<h1> Formatos Incorrectos en el paciente de codigo "+codigoP+"</h1>");
                    }
                    break;
                case "Modificar Consulta":
                    String codigoC = request.getParameter("codigoM");
                    String costoC = request.getParameter("costo");
                    if(modificador.ModificarConsulta(codigoC, Double.parseDouble(costoC))){
                        out.println("<h1> Se Actualizo el paciente de codigo "+codigoC+"</h1>");
                    }else{
                        out.println("<h1> Formatos Incorrectos en el paciente de codigo "+codigoC+"</h1>");
                    }
                    break;
            }
            out.println("<a href="+"./Data/adminMenuPage.jsp"+">Regresar</a>");
        }catch(Exception ex){   
            try (PrintWriter out = response.getWriter()) {
                out.println("<h1> Formatos Incorrectos en el paciente</h1>");
                out.println("<a href="+"./Data/adminMenuPage.jsp"+">Regresar</a>");
            }
        }
        
    }

}
