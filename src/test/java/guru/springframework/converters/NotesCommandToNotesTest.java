package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    static final Long ID_VALUE = 1L;
    static final String NOTES = "command notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testWithNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testWithEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(NOTES);

        Notes notes = converter.convert(command);

        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}