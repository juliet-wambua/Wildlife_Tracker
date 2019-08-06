package models;


import org.sql2o.Connection;

import java.sql.ResultSet;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private String animal_location;
    private String ranger_name;
    private int animal_id;
    private int id;
    private DateTimeFormatter time;
//    private static ArrayList<Animal> allAnimals = new ArrayList<>();

    public Sighting(int animalId,String location, String rangerName){
        this.animal_location = location;
        this.ranger_name = rangerName;
        this.animal_id = animalId;
        this.id = 0;
    }
    public static Sighting setUpSighting() {
        return new Sighting(1, "Zone-A", "Nea");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRanger_name() {
        return ranger_name;
    }

    public void setRanger_name(String ranger_name) {
        this.ranger_name = ranger_name;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_location() {
        return animal_location;
    }

    public void setAnimal_location(String animal_location) {
        this.animal_location = animal_location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return animal_id == sighting.animal_id &&
                getId() == sighting.getId() &&
                animal_location.equals(sighting.animal_location) &&
                ranger_name.equals(sighting.ranger_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal_location, ranger_name, animal_id, getId());
    }

    public void saveSightedAnimal(Sighting sighting) {
        try (Connection conn = Database.sql2otest.open()){
            String sql = "INSERT INTO  sightings(animal_id, animal_location, ranger_name ) VALUES (:animal_id, :animal_location, :ranger_name);";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("animal_location", this.animal_location)
                    .addParameter("ranger_name", this.ranger_name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> allSightings() {
        try(Connection conn = Database.sql2otest.open()){

            String sql = "SELECT * FROM sightings ORDER BY id DESC;";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public Sighting findAnimalById(int id) {
        try (Connection conn = Database.sql2otest.open()){
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }
    }


}
