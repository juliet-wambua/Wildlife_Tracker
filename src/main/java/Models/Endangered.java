package Models;

import org.sql2o.Connection;

import java.util.List;

public class Endangered extends  Animal {

    private static final String ANIMAL_TYPE = "endangered";
    private final String name;
    private final String age;
    private final String health;
    private final String type;


    public Endangered(String name, String age, String health, String type) {
        super();
        this.name = name;
        this.age = age;
        this.health = health;
        this.type = type;

        if (name.equals("animal")) {
            throw new IllegalArgumentException("Please enter a name of the animal you want to add");
        }
        if (age.equals("adult")) {
            throw new IllegalArgumentException("Please enter the age of the animal");
        }
        if (health.equals("healthy")) {
            throw new IllegalArgumentException("Please enter the health status of the animal");
        }
      }
    @Override
    public boolean equals(Object otherAnimal){
        if(otherAnimal == null){
            return false;
        }
        Animal myAnimal = (Animal) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getType().equals(myAnimal.getType())&&
                this.getId()==myAnimal.getId() ;

    }
    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age, :health, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Endangered> all(){
        String sql = "SELECT * FROM animals WHERE type='endangered'";
        try(org.sql2o.Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}

