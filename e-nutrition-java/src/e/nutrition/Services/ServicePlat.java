/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Composition;
import e.nutrition.Models.EtapeDePreparation;
import e.nutrition.Models.Plat;
import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServicePlat implements IService<Plat>{

    Connection cnx = DataSource.getInstance().getCnx();
    ServiceNourriture sN=new ServiceNourriture();
    ServiceAliment sA=new ServiceAliment();
    ServiceTag sT=new ServiceTag();
    
    @Override
    public void Add(Plat p) {
           try
        {
        sN.Add(p);
                //start fille 
            
            String reqfille="  INSERT INTO plat (id, description, nbrportion) VALUES ((SELECT LAST_INSERT_ID()), ?, ?)";
            PreparedStatement fille = cnx.prepareStatement(reqfille);
            fille.setString(1, p.getDescription());
            fille.setInt(2, p.getNbrportion());
            fille.executeUpdate();
            System.out.println("plat ajouter !!");
            //fin fille 
                 Iterator<EtapeDePreparation>  iteratorEtape= p.getEtapesDePreparation().iterator();
                 int j=0;
            while(iteratorEtape.hasNext())
           {     
               EtapeDePreparation etape= iteratorEtape.next();
               String reqEtape="INSERT INTO etape_de_preparation (ordre, duree, description, plat_id) VALUES (?, ?, ?, (SELECT LAST_INSERT_ID())) ";
               PreparedStatement etapestatement = cnx.prepareStatement(reqEtape);
               etapestatement.setInt(1, j);
               etapestatement.setInt(2, etape.getDuree());
               etapestatement.setString(3, etape.getDescription());
               etapestatement.executeUpdate();
               j++;
           }
            
            Iterator<Composition>  iteratorCompo= p.getCompostions().iterator();
            while(iteratorCompo.hasNext())
           {     
               Composition compostion= iteratorCompo.next();
               String reqEtape="INSERT INTO composition (poid, aliment_id, plat_id) VALUES (?, ?, (SELECT MAX(id) from plat )) ";
               PreparedStatement compostionStatement = cnx.prepareStatement(reqEtape);
               compostionStatement.setFloat(1, compostion.getPoid());
               compostionStatement.setInt(2,compostion.getAliment().getId());
               compostionStatement.executeUpdate();
           }
      
        }
        catch(SQLException e)
        {
            System.out.println("plat non ajouter !!");
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public void Delete(Plat p) {
 try
        {
            String etapereq = "DELETE FROM etape_de_preparation WHERE plat_id=?";
            PreparedStatement etapesat = cnx.prepareStatement(etapereq);
            etapesat.setInt(1, p.getId());
            etapesat.executeUpdate();
            
            String compositionreq = "DELETE FROM composition WHERE plat_id=?";
            PreparedStatement compositionsat = cnx.prepareStatement(compositionreq);
            compositionsat.setInt(1, p.getId());
            compositionsat.executeUpdate();
            
            
            String fillereq = "DELETE FROM plat WHERE id=?";
            PreparedStatement fille = cnx.prepareStatement(fillereq);
            fille.setInt(1, p.getId());
            fille.executeUpdate();
            sN.Delete(p);//delete from o=mother
         //delete tag
        }
               catch(SQLException e)
        {
            System.out.println("plat non supprimer !!");
            System.out.println(e.getMessage());
        }    }

    @Override
    public void Update(Plat p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Plat> Display() {
    ObservableList<Plat> oblist = FXCollections.observableArrayList();

    try
          {
              String req = " SELECT t1.id AS id, t1.nom AS nom, t1.lipides AS lipides, t1.glucides AS glucides, t1.proteines AS proteines, t1.poid AS poid, t0.description AS description, t0.nbrportion AS nbrportion, t1.nutritionniste_id AS nutritionniste_id, t1.dtype FROM plat t0 INNER JOIN nourriture t1 ON t0.id = t1.id "    ;
              PreparedStatement ps = cnx.prepareStatement(req);
              ResultSet rs = ps.executeQuery();

              while(rs.next())
              {
                Plat p=new Plat(rs.getString("description"), rs.getInt("nbrportion"),rs.getInt("id"), rs.getString("nom"), rs.getFloat("lipides"), rs.getFloat("glucides"), rs.getFloat("proteines"), rs.getFloat("poid"), rs.getInt("nutritionniste_id"));
                String reqetape = "SELECT t0.id AS id, t0.ordre AS ordre, t0.duree AS duree, t0.description AS description, t0.plat_id AS plat_id FROM etape_de_preparation t0 WHERE t0.plat_id = ?"    ;
                PreparedStatement etapeStatement = cnx.prepareStatement(reqetape);
                etapeStatement.setInt(1, p.getId());
                ResultSet rsetape = etapeStatement.executeQuery();

                while(rsetape.next())
                {
                    p.ajouterEtapeDePreparation(new EtapeDePreparation(rsetape.getInt("id"), rsetape.getInt("ordre"), rsetape.getInt("duree"), rsetape.getString("description")));
                }
                
                String reqcompostion = "SELECT t0.id AS id, t0.poid AS poid, t0.aliment_id AS aliment_id, t0.plat_id AS plat_id FROM composition t0 WHERE t0.plat_id = ? "    ;
                PreparedStatement pscompostion = cnx.prepareStatement(reqcompostion);
                pscompostion.setInt(1, p.getId());
                ResultSet rsCompostion = pscompostion.executeQuery();
                   while(rsCompostion.next())
                {
                  {
                      p.ajouterCompostions(new Composition(rsCompostion.getInt("id"),sA.rechercherExactAlimentId(rsCompostion.getInt("aliment_id")) ,p )); //new Plat(rsplat.getInt("id"), rsplat.getString("description"), rsplat.getString("nbrportion"))
                  }
                }
              sT.Display("tagnourriture", p.getId()).forEach((tag)-> {
               p.ajoutertag((TagNourriture)tag);
                });
              
              oblist.add(p);

              }
          }
          catch (SQLException e)
          {
              System.out.println("error");
              System.err.println(e.getMessage());
          }
          return oblist;   
      }    


}
