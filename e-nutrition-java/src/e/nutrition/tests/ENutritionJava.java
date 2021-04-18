package e.nutrition.tests;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Secretaire;
import e.nutrition.Services.ServiceChallenge;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceMedicament;
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
          FicheConsultation f1 = new FicheConsultation(54,null,150,150,"nossé","dénutrition","modéré");
      FicheConsultation f2 = new FicheConsultation(53,null,250,150,"nossé","patient à risque de dénutrition",null);
         ServiceFicheConsultation sf = new ServiceFicheConsultation();
           ServiceMedicament sm = new ServiceMedicament();
        
        
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
        
        
        
       System.out.println("____________________________Delete____________________________________________________________________________________");
        
        sf.Delete(f1);
        
        
        
         System.out.println("____________________________Update Fiche____________________________________________________________________________________");
        
        sf.Update(f2);
           
      
        System.out.println("____________________________Display medicaments____________________________________________________________________________________");
        
         List aaa1 = sm.Display();
        
        System.out.println(aaa1);
        
        
        
    }
    
}
