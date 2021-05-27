package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {
    final static Long ID_VALUE = 1L;
    final static String DESCRIPTION = "TableSpoon";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
//        given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
//        when
        UnitOfMeasure unitOfMeasure = converter.convert(command);
//        then
        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

}