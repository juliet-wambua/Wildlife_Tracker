package interfaces;

import models.Sighting;

import java.util.List;

public interface SightingInterface {

    void addSighting(Sighting sighting);

    List<Sighting> allSightings();

    default Sighting findById(int id) {
        return null;
    }

}
