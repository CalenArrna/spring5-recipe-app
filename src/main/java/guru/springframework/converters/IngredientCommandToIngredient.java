package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final RecipeRepository repository;

    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasureConverter;

    public IngredientCommandToIngredient(RecipeRepository repository, UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasureConverter) {
        this.repository = repository;
        this.unitOfMeasureCommandToUnitOfMeasureConverter = unitOfMeasureCommandToUnitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) return null;

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(unitOfMeasureCommandToUnitOfMeasureConverter.convert(source.getUom()));
        Optional<Recipe> recipe = repository.findById(source.getRecipeId());
        recipe.ifPresent(ingredient::setRecipe);

        return ingredient;
    }
}
