package com.example.TicketAnalyser.util.file;

import com.example.TicketAnalyser.exception.file.InvalidFileException;
import com.example.TicketAnalyser.util.args.ArgsParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketResourceLoader {

    @Value("classpath:${json.file.path}")
    private Resource classpathTicketResource;

    private final ArgsParser argsParser;

    public InputStream loadResource(ApplicationArguments args) {
        Optional<Path> filePath = argsParser.getOptionFilePath(args);
        return filePath
                .map(this::loadFile)
                .orElseGet(this::loadDefault);
    }

    private InputStream loadFile(Path filePath) {
        try {
            return new FileInputStream(filePath.toString());
        } catch (FileNotFoundException e) {
            throw new InvalidFileException(filePath.toString(), e);
        }
    }

    private InputStream loadDefault() {
        try {
            return classpathTicketResource.getInputStream();
        } catch (IOException e) {
            throw new InvalidFileException(classpathTicketResource.getFilename(), e);
        }
    }
}
