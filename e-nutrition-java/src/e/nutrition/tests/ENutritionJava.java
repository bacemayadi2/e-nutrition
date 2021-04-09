package e.nutrition.tests;

import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Secretaire;
import e.nutrition.Utils.DataSource;
import e.nutrition.Services.ServiceNutritionniste;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        sn.Delete(s1);
//        Nutritionniste n1 = new Nutritionniste(secretaires, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse)
        
//        try
//        {
//            String className = "com.mysql.jdbc.Driver";
//            Class.forName(className);
//            System.out.println("Success !!!");
//        }
//        catch(ClassNotFoundException ex)
//        {
//            System.out.println("Failed !!!");
//            System.out.println(ex.getMessage());
//        }
    }
    
}
