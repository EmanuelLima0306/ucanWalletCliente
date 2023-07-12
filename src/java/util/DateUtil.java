/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author emanuellima
 */
public class DateUtil {
    
    public static Date stringToDate(String dataString){
        String formato = "yyyy-MM-dd";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            Date data = sdf.parse(dataString);
            return data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
