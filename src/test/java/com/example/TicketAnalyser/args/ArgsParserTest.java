package com.example.TicketAnalyser.args;

import com.example.TicketAnalyser.exception.args.FileArgumentIsEmptyException;
import com.example.TicketAnalyser.util.args.ArgsParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArgsParserTest {

    @Mock
    private ApplicationArguments args;

    @InjectMocks
    private ArgsParser argsParser;

    @Test
    public void testGetOptionFilePath_WhenFileOptionIsAbsent_ReturnsEmptyOptional() {
        when(args.containsOption("f")).thenReturn(false);

        Optional<Path> result = argsParser.getOptionFilePath(args);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetOptionFilePath_WhenFileOptionIsPresentAndNotEmpty_ReturnsPath() {
        List<String> filePaths = Collections.singletonList("test.txt");
        when(args.containsOption("f")).thenReturn(true);
        when(args.getOptionValues("f")).thenReturn(filePaths);

        Optional<Path> result = argsParser.getOptionFilePath(args);

        assertTrue(result.isPresent());
        assertEquals(Path.of("test.txt"), result.get());
    }

    @Test
    public void testGetOptionFilePath_WhenFileOptionIsPresentButEmpty_ThrowsException() {
        List<String> filePaths = Collections.singletonList("");
        when(args.containsOption("f")).thenReturn(true);
        when(args.getOptionValues("f")).thenReturn(filePaths);

        assertThrows(FileArgumentIsEmptyException.class, () -> {
            argsParser.getOptionFilePath(args);
        });
    }

    @Test
    public void testGetOptionFilePath_WhenFileOptionHasNoValue_ThrowsException() {
        when(args.containsOption("f")).thenReturn(true);
        when(args.getOptionValues("f")).thenReturn(Collections.emptyList());

        assertThrows(FileArgumentIsEmptyException.class, () -> {
            argsParser.getOptionFilePath(args);
        });
    }
}
