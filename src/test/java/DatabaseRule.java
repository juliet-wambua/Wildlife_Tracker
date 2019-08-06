import models.Database;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        Database.sql2otest = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringaschool", "1543");  //Those with linux or windows use two strings for username and password
    }

    @Override
    protected void after() {
        try(Connection con = Database.sql2otest.open()) {
            String deletePersonsQuery = "DELETE FROM animal *;";
            con.createQuery(deletePersonsQuery).executeUpdate();
        }
    }

}