package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    public static final Long ID_VALUE = 3L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(2);
    public static final Long UOM_ID = 1L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertNotNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
    }

    @Test
    void convertWithNullUOM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }
}