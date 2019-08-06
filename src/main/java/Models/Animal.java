package Models;

import org.sql2o.Connection;

import java.util.List;

public class Animal {
    public String name;
    public String age;
    public String health;
    public String type;
    public int id;

    public Animal() {

    }

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }
    public String getHealth() {
        return health;
    }
    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (null == otherAnimal) {
            return false;
        }
        Animal myAnimal = (Animal) otherAnimal;
        return this.getName().equals(myAnimal.getName()) &&
                this.getType().equals(myAnimal.getType()) &&
                this.getId() == myAnimal.getId() &&
                this.getAge().equals(myAnimal.getAge()) &&
                this.getHealth().equals(myAnimal.getHealth());
    }
    public void save() {
        try (org.sql2o.Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age, :health, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static Animal find(int id) {
        String sql = "SELECT * FROM animals WHERE id = :id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);

        }

    }

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

    public static List<Animal> all(){
        String sql = "SELECT * FROM animals WHERE type='Big5'";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

}
