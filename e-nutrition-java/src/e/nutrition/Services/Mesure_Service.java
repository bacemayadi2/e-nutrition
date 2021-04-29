/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;


import e.nutrition.Models.Mesure;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ayoub
 */
public class Mesure_Service {
     private DataSource dataSource;
    private Statement statement;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    
    public Mesure_Service(){
        dataSource = DataSource.getInstance();
    }
     public void Add_Mesure(Mesure mesure ) throws SQLException {
        this.dataSource = DataSource.getInstance();
        
        preparedStatement = dataSource.getCnx().prepareStatement("INSERT INTO pidev3a.mesure ( `poids` , `taille` ,  `date_mesure` , `patient_id` ) VALUES (?,? ,? , "+61+" );");
        preparedStatement.setFloat(1, mesure.getPoids());
        preparedStatement.setFloat(2, mesure.getTaille());
        preparedStatement.setDate(3, new java.sql.Date(mesure.getDate_mesure().getTime()));
        preparedStatement.executeUpdate();

    }
    
     public List<Mesure> Get_All_Mesure() throws SQLException{
         List<Mesure> list = new ArrayList<>();
        String sql = "select * from  pidev3a.mesure";
        preparedStatement = dataSource.getCnx().prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        

        while (resultSet.next()) {
            Mesure mesure = new Mesure();
            mesure.setId(resultSet.getInt("id"));
            mesure.setPoids(resultSet.getFloat("poids"));
            mesure.setTaille(resultSet.getFloat("taille"));
            mesure.setDate_mesure(resultSet.getDate("date_mesure"));
           
            list.add(mesure);
        }

        return list;
     }
      public void Update_Mesure(int id , Mesure mesure) throws SQLException{
        String sql ="update pidev3a.mesure set poids = ? , taille=? , date_mesure=?  where id = '"+id+"' ";
        preparedStatement= dataSource.getCnx().prepareStatement(sql);
        
        preparedStatement.setFloat(1, mesure.getPoids());
        preparedStatement.setFloat(2, mesure.getTaille());
        preparedStatement.setDate(3,new java.sql.Date(mesure.getDate_mesure().getTime()));
  
        preparedStatement.executeUpdate();
    }
      public int Delete_Mesure(int id ) throws SQLException{
        String sql = "delete from pidev3a.mesure where id = '"+id+"' ";
        statement = dataSource.getCnx().prepareStatement(sql);
        statement.executeUpdate(sql);
        return id;  
    }
     
      
      public String Get_Imc_Details( float taille , float poids ){
           float current_user_imc = poids / ((taille*taille) / (100 * 100));
           String res = "IMC: " + String.valueOf(current_user_imc) + " "; 
           if(current_user_imc < 18.5){
               res += "Insuffisance pondérale";
           }
           else if((current_user_imc > 18.5) && (current_user_imc < 24.9)){
               res += "Poids Normal";
           }
           else if((current_user_imc > 25) && (current_user_imc < 29.9)){
               res += "Surpoids";
           }
           else {
               res += "Obésité Morbide";
           }
           return res; 
      }  
}
