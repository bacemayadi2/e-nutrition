/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.BlogPost;
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
public class Blog_Post_Service {
    
    private DataSource dataSource;
    private Statement statement;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    
    public Blog_Post_Service() {
        dataSource = DataSource.getInstance();
    }
    
     public void Add_Blog_Post(BlogPost blog) throws SQLException {
        this.dataSource = DataSource.getInstance();
        preparedStatement = dataSource.getCnx().prepareStatement("INSERT INTO pidev3a.`blog_post` ( `title` , `body` ,  `date`,`nb_raiting`) VALUES (? ,? , ? , ? );");
        preparedStatement.setString(1, blog.getTitle());
        preparedStatement.setString(2, blog.getBody());
        preparedStatement.setDate(3, new java.sql.Date(blog.getDate().getTime()));
        preparedStatement.setDouble(4, (double)0);
        preparedStatement.executeUpdate();

    }
     public List<BlogPost> Get_All_Blog_And_Posts() throws SQLException{
         List<BlogPost> list = new ArrayList<>();
        String sql = "select * from  pidev3a.blog_post";
        preparedStatement = dataSource.getCnx().prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        

        while (resultSet.next()) {
            BlogPost blog = new BlogPost();
            blog.setId(resultSet.getInt("id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setBody(resultSet.getString("body"));
            blog.setDate(resultSet.getDate("date"));
            blog.setNb_raiting(resultSet.getDouble("nb_raiting"));
            list.add(blog);
        }

        return list;
     }
      public void Update_Blog_Post(int id , BlogPost blog) throws SQLException{
        String sql ="update pidev3a.blog_post set title = ? , body=? , date=?  where id = '"+id+"' ";
        preparedStatement= dataSource.getCnx().prepareStatement(sql);
        
        preparedStatement.setString(1, blog.getTitle());
        preparedStatement.setString(2, blog.getBody());
        preparedStatement.setDate(3,new java.sql.Date(blog.getDate().getTime()));
    
         
        
        preparedStatement.executeUpdate();
    }
      public int Delete_Blog_Post(int id ) throws SQLException{
        String sql = "delete from pidev3a.blog_post where id = '"+id+"'";
        statement = dataSource.getCnx().prepareStatement(sql);
        statement.executeUpdate(sql);
        return id;
    }

   public Boolean setRating(int i, float r) throws SQLException {
        try {

             

            String sql = "update pidev3a.blogpost set nb_raiting ='" + r + " ' where id='" + i + "' ";

            statement = dataSource.getCnx().prepareStatement(sql);
            statement.executeQuery(sql);

            System.out.println(statement);
            return true;
        } catch (SQLException ex) {
             
        }
        return false;
    }

    public float GETRate(int i) throws SQLException {
         
        preparedStatement = dataSource.getCnx().prepareStatement(" SELECT nb_raiting FROM pidev3a.blogpost WHERE id ='" + i + "'  ");

        float r = 0;
        try {
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if (resultSet != null) {
                while (resultSet.next()) {

                     
                    r = resultSet.getFloat("nb_raiting");
                     

                    return r;
                }
            }

        } catch (SQLException ex) {
             
        }
        return 0;
    }
}
