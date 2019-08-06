package interfaces;

import models.Animal;

import java.util.List;

public interface AnimalInterface {
     void saveAnimal(Animal animal);

//    List<Animal> getAllAnimals();

    Animal findById(int id);
}
