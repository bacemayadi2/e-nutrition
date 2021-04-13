package e.nutrition.tests;

import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Secretaire;
import e.nutrition.Services.ServiceChallenge;
import e.nutrition.Utils.DataSource;
import e.nutrition.Services.ServiceNutritionniste;
import e.nutrition.Services.ServicePatient;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ALADIN
 */
public class ENutritionJava 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException 
    {
        
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        DataSource ds = DataSource.getInstance();
        
        Nutritionniste s1 = new Nutritionniste(73);
        
        
        ServiceNutritionniste sn = new ServiceNutritionniste();
        ServicePatient sp = new ServicePatient();
        ServiceChallenge sc = new ServiceChallenge();

        System.out.println("____________________________Delete____________________________________________________________________________________");
        
        sn.Delete(s1);
        
        System.out.println("____________________________Display Patients____________________________________________________________________________________");
        
        sp.Display();
        
        System.out.println("____________________________Display Challenges____________________________________________________________________________________");
        
        List al = sc.Display();
        
        System.out.println(al);
        
    }
    
}
