package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Joel Backschat on 28/07/2016.
 */
public class Utils {

    public static SimpleDateFormat  mZuluFormat;
    public static SimpleDateFormat  mAuthFormat;

    public static Date stringToDate(String dateString){
        try{
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            return format.parse(dateString);
        }catch(ParseException e){
            return null;
        }
    }

    public static String dateToString(Date date){
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            return format.format(date);
        }catch(Exception ex){
            return null;
        }
    }

    public static String formatString(String dateString){
        try{
            return dateToString(getDate(dateString));
        }catch (Exception e) {
            return "";
        }
    }

    public static Date getDate(String dataString) {
        try {
            if (dataString == null || dataString.isEmpty()) {
                return null;
            }
            return getZuluFormat().parse(dataString);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static DateFormat getZuluFormat() {
        if (mZuluFormat == null) {
            //Format used by SharePoint for encoding datetimes
            mZuluFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            mZuluFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return mZuluFormat;
    }

}
