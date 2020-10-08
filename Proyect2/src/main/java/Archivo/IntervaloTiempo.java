/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class IntervaloTiempo {

    private Date horaInicial;
    private Date horaFinal;
    private final SimpleDateFormat formatDate=new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat formatCalendar=new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Establece dos objetos tipo date segun la fecha enviado
     * se vuelven nulos si no son aceptados
     * @param horaInicial
     * @param horaFinala
     */
    public IntervaloTiempo (String horaInicial, String horaFinal){
        try {
            this.horaInicial = (Date) formatDate.parse(horaInicial);
            this.horaFinal = (Date) formatDate.parse(horaFinal);
        } catch (ParseException ex) {
            horaInicial=null;
            horaFinal=null;
        }
    }
    
    /**
     * Retorna verdadero si el string enviado se encuentra entre los dos formatos de hora
     * Tambien verifica si la cita se realizara para mayor de la fecha actual
     * @param hora
     * @return
     */
    public boolean IsInDateRange(String hora, String fecha){
        try {
            Date date = new Date();
            Date fechaDate = (Date) formatCalendar.parse(fecha);
            Date newDate = (Date) formatDate.parse(hora);
            if( newDate.after(horaInicial) && newDate.before(horaFinal) && (fechaDate.after(date) || fechaDate.equals(date))){
                return true;
            }else{
                return false;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public Date returnHoraInicial(){
        return this.horaInicial;
    }
    
    public Date returnHoraFinal(){
        return this.horaFinal;
    }
    
    
    
}
