package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    IngredientToIngredientCommand ingredientConverter;
    CategoryToCategoryCommand categoryConverter;
    NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null){
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setCookTime(source.getCookTime());
        command.setDifficulty(source.getDifficulty());
        command.setPrepTime(source.getPrepTime());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setImage(source.getImage());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }
        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
