/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import com.codename1.io.File;
import e.nutrition.entities.tags.Tag;

//import java.sql.Date;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.net.Urls;


/**
 *
 * @author bacem
 */
public class ContenuMultimedia 
{
    private int id;
    private String nomFile;
    public final String dtype="contenumultimedia";
    private String Description;
    private Date updatedAt;
    private File file;
    private List<Tag> tags =new ArrayList() ;

    public ContenuMultimedia(int id, String nomFile, String Description, Date updatedAt) {
        this.id = id;
        this.nomFile = nomFile;
        this.Description = Description;
        this.updatedAt = updatedAt;
    }

    public ContenuMultimedia(int id, String nomFile, String Description, Date updatedAt, File file) {
        this.id = id;
        this.nomFile = nomFile;
        this.Description = Description;
        this.updatedAt = updatedAt;
        this.file = file;
    }
    

    public  ContenuMultimedia( String Description,File file) {
        this.Description = Description;
        
    }

    public ContenuMultimedia() {
    }


    public String getNomFile() {
        return nomFile;
    }

    public void setNomFile(String nomFile) {
        this.nomFile = nomFile;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    public String getDtype() {
        return dtype;
    }

         
    public void ajoutertag(Tag t)
    {
        if (t != null)
        {
            tags.add(t);
        }
    } 
    public void supprimertag (Tag t)
    {
        if (t != null)
        {
            tags.remove(t);
        }
    }
    
     /*    public int sendFileToHTTP(File file) {
        int responseCode = 0;
        String idMultimedi = null;
        System.setProperty("webdriver.chrome.driver","../Ressources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver =new ChromeDriver(options);
             WebElement fileInput;
             WebElement buttonAjouter;
             WebElement fileid;

             

        driver.get("http://localhost:8000/ajouterfichier");
        String actualTitle = driver.getTitle();
           if (actualTitle.contentEquals("Hello")){
            System.out.println("Test Passed!");
            fileInput = driver.findElement(By.id("contenu_multimedia_fileMultimedia_file"));
            fileInput.sendKeys(file.getPath());
            buttonAjouter =driver.findElement(By.id("contenu_multimedia_Ajouter"));
            buttonAjouter.click();
            fileid = driver.findElement(By.id("fileid"));
              idMultimedi = fileid.getText();

         //   fileInput.click();
        } else {
            System.out.println("Test Failed");
        }
           
                 driver.close();

        
  
   return Integer.parseInt(idMultimedi);
    }
/*
 public void deleteMultimedia(int id )
 {   
     int responseCode = 0;
        try {
            System.out.println(file);
            Urls url = new Urls("http://127.0.0.1:8000/deletemultimedia/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
            //Set Request Type to POST
            conn.setRequestMethod("POST");
            //Send text data
            conn.setRequestProperty("Content-Type", "text/plain");

            

            System.out.println(conn.getResponseMessage());
            //Fetch Response Code
            responseCode = conn.getResponseCode();

   
        } catch(Exception e) {
            e.printStackTrace();
        }
    
            //Return the response code
             System.out.println(responseCode);
    }

    
         */   
}