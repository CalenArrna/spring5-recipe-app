package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

}
