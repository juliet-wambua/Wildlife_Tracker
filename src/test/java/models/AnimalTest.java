package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalTest {


    @Test
    public void getAnimal_age() {
        Animal newAnimal = Animal.setUpNewAnimal();
        assertEquals("young",newAnimal.getAnimal_age());
    }

    @Test
    public void setanimal_age() {
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.setAnimal_age("Young");
        assertEquals("Young",newAnimal.getAnimal_age());
    }


    @Test
    public void setId() {
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.setId(3);
        assertEquals(3,newAnimal.getId());
    }

    @Test
    public void getName() {
        Animal newAnimal = Animal.setUpNewAnimal();
        assertEquals("Koala Bear",newAnimal.getAnimal_name());
    }

    @Test
    public void setName() {
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.setAnimal_name("Hyena");
        assertEquals("Hyena",newAnimal.getAnimal_name());
    }

    @Test
    public void equals() {
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.saveAnimal(newAnimal);
        assertNotEquals(newAnimal,newAnimal.findById(newAnimal.getId()));

    }

    @Test
    public void saveAnimal() {
        Animal newAnimal = Animal.setUpNewAnimal();
        EndangeredAnimal newDanger = new EndangeredAnimal("Gazelle","Healthy","young");
        newDanger.saveAnimal(newDanger);
        int initialId = newAnimal.getId();
        newAnimal.saveAnimal(newAnimal);
        assertNotEquals(initialId,newAnimal.getId());
    }
    @Test
    public void getAllAnimals() throws Exception{
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.saveAnimal(newAnimal);

        assertEquals(newAnimal.getId(),Animal.getAllAnimals().size());
    }

    @Test
    public void findAnimalById() {
        Animal newAnimal = Animal.setUpNewAnimal();
        newAnimal.saveAnimal(newAnimal);
        assertEquals(newAnimal.getId(), newAnimal.findById(newAnimal.getId()).getId());
    }


}