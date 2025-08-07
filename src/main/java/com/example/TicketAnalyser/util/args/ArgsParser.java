package com.example.TicketAnalyser.util.args;

import com.example.TicketAnalyser.exception.args.FileArgumentIsEmptyException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ArgsParser {
    private final String fileOptionName = "f";

    public Optional<Path> getOptionFilePath(ApplicationArguments args) {
        Path fileOptionValue = null;
        if (args.containsOption(fileOptionName)) {
            checkIfFileOptionNotEmpty(args);
            fileOptionValue = getFileOptionValue(args);
        }
        return Optional.ofNullable(fileOptionValue);
    }

    private void checkIfFileOptionNotEmpty(ApplicationArguments args) {
        List<String> optionValues = args.getOptionValues(fileOptionName);
        if (optionValues.isEmpty() || optionValues.get(0).isEmpty()) {
            throw new FileArgumentIsEmptyException();
        }
    }

    private Path getFileOptionValue(ApplicationArguments args) {
        List<String> optionValues = args.getOptionValues(fileOptionName);
        return Path.of(optionValues.get(0));
    }

}
