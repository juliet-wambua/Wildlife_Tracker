package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void animal_InstantiatesCorrectly_true(){
        Animal myAnimal = new Animal("Elephant","young","weak","");
        assertEquals(true, myAnimal instanceof Animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithName_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("Elephant", myAnimal.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithAge_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("Elephant", myAnimal.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithHealth_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("Elephant", myAnimal.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithType_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("Elephant", myAnimal.getType());
    }


    // test to see if animal instantiates with an age and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithAge_mature(){
        Animal myAnimal = new Animal("","","","");
    }

    // test to see if animal instantiates with the health status and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithHealth_Weak(){
        Animal myAnimal = new Animal("","","","");
    }

    @Test
    public void animal_IsSavedInDatabase(){
        Animal myAnimal = new Animal("Elephant", "adult", "healthy","Big5");
        myAnimal.save();
        assertTrue(Animal.all().get(0).equals(myAnimal));
    }
    //Test to check if all instances of regular animals are made
    @Test
    public void animal_AllInstancesOfAnimalAreReturned_True(){
        Animal myAnimal1 = new Animal("Elephant", "young","healthy","Big5");
        myAnimal1.save();
        Animal myAnimal2 = new Animal("puma","mature","healthy","safe");
        myAnimal2.save();
        assertTrue(Animal.all().get(0).equals(myAnimal1));
        assertTrue(Animal.all().get(1).equals(myAnimal2));
    }
    //Animal is assigined an Id
    @Test
    public void animal_AnimalIsAssingnedAnID_getId(){
        Animal myAnimal = new Animal("puma","young","healthy","Big5");
        myAnimal.save();
        Animal testAnimalia = Animal.all().get(0);
        assertEquals(myAnimal.getId(), testAnimalia.getId());
    }

    //Test to find Animal with the Same Id
    @Test
    public void find_WillReturnAnimalWithTheSame_SecondAnimal(){
        Animal firstAnimal = new Animal("Elephant","mature","healthy","Big5");
        firstAnimal.save();
        Animal secondAnimal = new Animal("puma","young","healthy","safe");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

}