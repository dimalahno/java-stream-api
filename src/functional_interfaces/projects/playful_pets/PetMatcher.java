package functional_interfaces.projects.playful_pets;

import java.util.List;

@FunctionalInterface
public interface PetMatcher {

    List<Pet> match(Pet pet);

    default Pet first(Pet pet)
    {
        int index = Pet.pets.indexOf(pet);
        return index > -1? Pet.pets.get(index) : null;
    }
}
