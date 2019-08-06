package models;

import interfaces.AnimalInterface;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static models.Database.sql2otest;

public class EndangeredAnimal  {
    private int animal_id;
    private String animal_name;
    private String animal_health;
    private String animal_age;
    private int id;

    public EndangeredAnimal(String animal_name,String animal_health, String animal_age ){
        this.animal_name = animal_name;
        this.animal_health = animal_health;
        this.animal_age = animal_age;
        this.id = 1;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_health() {
        return animal_health;
    }

    public void setAnimal_health(String animal_health) {
        this.animal_health = animal_health;
    }

    public String getAnimal_age() {
        return animal_age;
    }

    public void setAnimal_age(String animal_age) {
        this.animal_age = animal_age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimal)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return getAnimal_id() == that.getAnimal_id() &&
                getId() == that.getId() &&
                getAnimal_name().equals(that.getAnimal_name()) &&
                getAnimal_health().equals(that.getAnimal_health()) &&
                getAnimal_age().equals(that.getAnimal_age());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimal_id(), getAnimal_name(), getAnimal_health(), getAnimal_age(), getId());
    }

//    @Override
    public void saveAnimal(EndangeredAnimal endangeredAnimal) {
        try (Connection conn = sql2otest.open()){
            String sql = "INSERT INTO  endangered_animals(animal_name, animal_age, animal_health ) VALUES (:animal_name, :animal_age,:animal_health);";
            this.id = (int) conn.createQuery(sql, true)
                    .addParameter("animal_name", this.animal_name)
                    .addParameter("animal_age", this.animal_age)
                    .addParameter("animal_health", this.animal_health)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<EndangeredAnimal> getAllAnimals() {
        try(Connection conn = sql2otest.open()){
            String sql = "SELECT * FROM endangered_animals ORDER BY id DESC;";
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

//    @Override
    public EndangeredAnimal findById(int id) {
        String sql = "SELECT * FROM endangered_animals WHERE id=:id";
        try (Connection conn = Database.sql2otest.open()){
            EndangeredAnimal animal = conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
             return animal;
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }
    }


}
