package Models;

import org.sql2o.Connection;

import java.util.List;

public class Animal extends AnimalsAbstract  {
    private static final String ANIMAL_TYPE = "Big5";
    public Animal(String name, String age, String health, String type){

        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name");
        }
        if (age.equals("")) {
            throw new IllegalArgumentException("Please enter the age of the animal");
        }
        if (health.equals("")) {
            throw new IllegalArgumentException("Please enter the health status of the animal");
        }
        this.name = name;
        this.age = age;
        this.health = health;
        this.type = ANIMAL_TYPE;

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
    @Override
    public boolean equals(Object otherAnimal){
        if(otherAnimal == null){
            return false;
        }
        AnimalsAbstract myAnimal = (AnimalsAbstract) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getType().equals(myAnimal.getType())&&
                this.getId()==myAnimal.getId() ;

    }
    public static List<Animal> all(){
        String sql = "SELECT * FROM animals WHERE type='Big5'";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }


}
