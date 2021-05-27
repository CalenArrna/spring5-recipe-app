package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NotesToNotesCommand());
    }


    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }
    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setServings(SERVINGS);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setSource(SOURCE);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setUrl(URL);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID_2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command = converter.convert(recipe);

        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2,command.getCategories().size());
        assertEquals(2,command.getIngredients().size());


    }
}