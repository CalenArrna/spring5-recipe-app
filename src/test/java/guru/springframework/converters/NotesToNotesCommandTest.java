package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "chop-chop";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testWithNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testWithEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
//          given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);
//          when
        NotesCommand command = converter.convert(notes);
//          then
        assertNotNull(command);
        assertEquals(ID_VALUE,command.getId());
        assertEquals(RECIPE_NOTES,command.getRecipeNotes());
    }
}