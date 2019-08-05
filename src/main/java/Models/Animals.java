package Models;

import org.sql2o.Connection;

public class AnimalsAbstract {
    protected String name;
    protected String age;
    protected String health;
    protected String type;
    protected int id;

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
        if (!(otherAnimal instanceof Object)) {
            return false;
        }
        AnimalsAbstract myAnimal = (AnimalsAbstract) otherAnimal;
        return this.getName().equals(myAnimal.getName()) &&
                this.getType().equals(myAnimal.getType()) &&
                this.getId() == myAnimal.getId() &&
                this.getAge() == myAnimal.getAge() &&
                this.getHealth().equals(myAnimal.getHealth());
    }
    public void save() {
        try (org.sql2o.Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (name, age, health, type) VALUES (:name, :age, :health, :type);";
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
    public static AnimalsAbstract find(int id) {
        String sql = "SELECT * FROM animal WHERE id = :id;";
        try (Connection con = DB.sql2o.open()) {
            AnimalsAbstract myAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(AnimalsAbstract.class);
            return myAnimal;

        }


    }
}
