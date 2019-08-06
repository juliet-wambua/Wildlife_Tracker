import static spark.Spark.*;

import models.Animal;
import models.EndangeredAnimal;
import models.Sighting;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.*;

import java.util.HashMap;
import java.util.Map;


public class App {

    public static void main(String[] args) {

        Connection conn;
        staticFileLocation("/public");

        get("/", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            model.put("allEndangered", EndangeredAnimal.getAllAnimals());
            model.put("allAnimals", Animal.getAllAnimals());
            model.put("allSightings", Sighting.allSightings());
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/dashboard", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            model.put("allAnimals", Animal.getAllAnimals());
            model.put("allEndangered", EndangeredAnimal.getAllAnimals());
            model.put("allSightings", Sighting.allSightings());
            return new ModelAndView(model,"dashboard.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new-animal", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"new-animal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-animal",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            Animal newAnimal = new Animal(id,name);
            newAnimal.saveAnimal(newAnimal);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        post("/new-sighting", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("selectedAnimal"));
            String location = req.queryParams("location");
            String rangerName = req.queryParams("ranger");
            Sighting newSighting = new Sighting(id,location,rangerName);
            newSighting.saveSightedAnimal(newSighting);
            model.put("newSighting",newSighting.findAnimalById(newSighting.getId()));
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-endangered", (req,res)->{
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimal newEndangered = new EndangeredAnimal(name,health,age);
            newEndangered.saveAnimal(newEndangered);
            model.put("newEndangered",newEndangered);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
