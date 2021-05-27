package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "TeaSpoon";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
//        given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);
//        when
        UnitOfMeasureCommand command = converter.convert(uom);
//        then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}