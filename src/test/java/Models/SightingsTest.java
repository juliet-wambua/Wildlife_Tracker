package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Sighting_InstantiatesCorrectly_True(){
        Sightings newsighting = new Sightings("Roger","Zone 1",1);
        assertTrue( newsighting instanceof Sightings);
    }
    @Test
    public void Sighting_IsSavedOnDataBase_True(){
        Sightings newSighting = new Sightings("Roger","Zone 2",1);
        newSighting.save();
        assertTrue(Sightings.all().get(0).equals(newSighting));
    }
    @Test
    public void sighting_EachSightingIsAssignedAnId_getid(){
        Sightings newSighting = new Sightings("Roger","Zone 2",1);
        newSighting.save();
        Sightings testSighting = Sightings.all().get(0);
        assertEquals(newSighting.getId(), testSighting.getId());
    }
    @Test
    public void find_WillReturnSightingWithTheSameID_SecondSighting(){
        Sightings firstSighting = new Sightings("Roger","Zone 2",1);
        firstSighting.save();
        Sightings SecondSighting = new Sightings("Chris","Zone 5",3);
        SecondSighting.save();
        assertEquals(SecondSighting, Sightings.find(SecondSighting.getId()));
    }
    @Test
    public void Sightings_CanBeDeletedFromDataBase_true() {
        Sightings newSighting = new Sightings("Brenden","Zone 5",1);
        newSighting.save();
        newSighting.delete();
        assertEquals(0, Sightings.all().size());
    }



}