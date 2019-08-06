package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void getId() {
        Sighting newSighting = Sighting.setUpSighting();
        int initialId = newSighting.getId();
        newSighting.saveSightedAnimal(newSighting);
        assertNotEquals(initialId,newSighting.getId());

    }

    @Test
    public void getAnimal_id() {
       Sighting newSighting = Sighting.setUpSighting();
       newSighting.getId();
        assertEquals(newSighting.getAnimal_id(),newSighting.getAnimal_id());
    }

    @Test
    public void getAnimal_location() {
        Sighting newSighting = Sighting.setUpSighting();
        assertEquals("Zone-A",newSighting.getAnimal_location());
    }

    @Test
    public void equals1() {
        Sighting oldSighting = Sighting.setUpSighting();
        oldSighting.saveSightedAnimal(oldSighting);
        assertEquals(oldSighting, oldSighting.findAnimalById(oldSighting.getId()));
    }

    @Test
    public void saveSightedAnimal() {
        Sighting newSighting = Sighting.setUpSighting();
        Sighting another = new Sighting(23,"test","Arnold");
        int initialId = newSighting.getId();
        newSighting.saveSightedAnimal(newSighting);
        another.saveSightedAnimal(another);
        assertNotEquals(initialId,another.getId());
    }

    @Test
    public void findAnimalById() {
        Sighting oldSighting = Sighting.setUpSighting();
        oldSighting.saveSightedAnimal(oldSighting);
        assertEquals(oldSighting, oldSighting.findAnimalById(oldSighting.getId()));
    }

    @Test
    public void allSightings() throws Exception{
        Sighting oldSighting = Sighting.setUpSighting();
        oldSighting.saveSightedAnimal(oldSighting);
        assertTrue( Sighting.allSightings().contains(oldSighting));
    }

}