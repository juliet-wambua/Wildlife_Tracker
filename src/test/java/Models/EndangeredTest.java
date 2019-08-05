package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void endangeredAnimal_InstantiatesCorrectly_true(){
        Endangered myEndangeredAnimal = new Endangered("panther","young","weak","");
        assertEquals(true, myEndangeredAnimal instanceof Endangered);
    }
    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithName_panther(){
        Endangered myEndangeredAnimal = new Endangered("","","","");
        assertEquals("panther", myEndangeredAnimal.getName());
    }

    // test to see if animal instantiates with an age and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void EndangeredAnimal_InstantiatesWithAge_mature(){
        Endangered myEndangeredAnimal = new Endangered("","","","");
    }

    // test to see if animal instantiates with the health status and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void EndangeredAnimal_InstantiatesWithHealth_Weak(){
        Endangered myEndangeredAnimal = new Endangered("","","","");
    }
    //test to check if info is saved into database
    @Test
    public void EndangeredAnimal_IsSavedInDatabase(){
        Endangered myEndangeredAnimal = new Endangered("panther", "adult", "healthy","endangered");
        myEndangeredAnimal.save();
        assertTrue(myEndangeredAnimal.all().get(0).equals(myEndangeredAnimal));
    }
    //Test to find Animal with the Same Id
    @Test
    public void find_WillReturnEndangeredAnimalWithTheSame_SecondAnimal(){
        Endangered firstEndangeredAnimal = new Endangered("Elephant", "adult", "healthy","endangered");
        firstEndangeredAnimal.save();
        Endangered SecondEndangeredAnimal = new Endangered("Elephant", "adult", "healthy","endangered");
        SecondEndangeredAnimal.save();
        assertEquals(Endangered.find(SecondEndangeredAnimal.getId()), SecondEndangeredAnimal);
    }
    //Animal is assigined an Id
    @Test
    public void EndangeredAnimal_AnimalIsAssignedAnID_getId(){
        Endangered myEndangeredAnimal = new Endangered("Elephant", "adult", "healthy","endangered");
        myEndangeredAnimal.save();
        Endangered testEndangeredAnimals = Endangered.all().get(0);
        assertEquals(myEndangeredAnimal.getId(), testEndangeredAnimals.getId());
    }
    @Test
    public void EndangeredAnimal_AllInstancesOfAnimalAreReturned_True(){
        Endangered myEndangeredAnimal1 = new Endangered("Elephant", "adult", "healthy","endangered");
        myEndangeredAnimal1.save();
        Endangered myEndangeredAnimal2 = new Endangered("Black Rhino", "adult", "healthy","endangered");
        myEndangeredAnimal2.save();
        assertTrue(Endangered.all().get(0).equals(myEndangeredAnimal1));
        assertTrue(Endangered.all().get(1).equals(myEndangeredAnimal2));
    }




}