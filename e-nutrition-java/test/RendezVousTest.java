
import e.nutrition.Models.RendezVous;
import e.nutrition.Services.RendezVousService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RendezVousTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();//output de test
    
    private final PrintStream originalOut = System.out; // contenu system.out
    

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent)); // pour lieer l'output system au printstream de outcontent
       
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
       
    }

    @InjectMocks
    RendezVousService rdvService;

    @Test //methode de test//
    public void should_add_rendezvous_when_object_is_valid() {//test d'integration
        //given//
        RendezVous rdv = new RendezVous();
        rdv.setDate(new Date());
        rdv.setDescription("description rendezVous");

        //when//
        rdvService.Add(rdv);
        //then//

        Assert.assertTrue(outContent.toString().contains("Rendez-vous ajoutée !")); 
        
    }
    @Test 
    public void should_print_hello(){ //test unitaire
        
        //given
        String name = "abdelhamid";
        //when
        
        rdvService.printHello(name);
        //then
    Assert.assertTrue(outContent.toString().contains("hello "+name));
    
}

}
