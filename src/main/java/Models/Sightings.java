package Models;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sightings implements AnimalInterface{
    private String name;
    private String location;
    private int animalId;
    private Timestamp timestamp;
    private int id;
    public Sightings(String name, String location, int animalId) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name mate");
        }
        if (location.equals("")) {
            throw new IllegalArgumentException("Please enter a location mate");
        }
        this.name = name;
        this.location = location;
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public AnimalsAbstract getAnimal() {
        String sql = "SELECT * FROM animal WHERE id = :id";
        try(Connection con = DB.sql2o.open()){
            AnimalsAbstract myAnimal = con.createQuery(sql)
                    .addParameter("id",this.animalId)
                    .executeAndFetchFirst(AnimalsAbstract.class);
            return myAnimal;
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (name, location, animalId, timestamp) VALUES (:name, :location, :animalId, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("location", this.location)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate()
                    .getKey();
        }
    }
    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Object)){
            return false;
        }
        Sightings myAnimal = (Sightings) otherSighting;
        return this.getName().equals(myAnimal.getName())&&
                this.getLocation().equals(myAnimal.getLocation())&&
                this.getId()==myAnimal.getId() ;
    }
    public static List<Sightings> all(){
        String sql = "SELECT * FROM sighting;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }
    public static Sightings find(int id){
        String sql = "SELECT * FROM sighting WHERE id = :id";
        try(Connection con = DB.sql2o.open()) {
            Sightings sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return sighting;
        }
    }
    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sighting WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}



