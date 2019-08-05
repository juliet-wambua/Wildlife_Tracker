package Models;

import org.sql2o.Connection;

import java.util.List;

public class Endangered extends  AnimalsAbstract {
    private static final String ANIMAL_TYPE = "endangered";
    public Endangered(String name, String age, String health, String type) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter a name of the animal you want to add");
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
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Object)){
            return false;
        }
        AnimalsAbstract myAnimal = (AnimalsAbstract) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getType().equals(myAnimal.getType())&&
                this.getId()==myAnimal.getId() ;

    }
    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animal (name, age, health, type) VALUES (:name, :age, :health, :type);";
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
        String sql = "SELECT * FROM animal WHERE type='endangered'";
        try(org.sql2o.Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}

