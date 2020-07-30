package com.gabikersul.ilegra.technicalchallenge.DAO;

import com.gabikersul.ilegra.technicalchallenge.exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImportDAO {
    public List<Path> readFolder() {
        List<Path> paths;
        try {
            paths = Files.walk(Paths.get("./data/in"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileException("Error reading file: " + e);
        }
        return paths;
    }

    public List<String> readAllLines(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(String.format("./data/in/%s", path)));
        } catch (IOException e) {
            throw new FileException("Error reading file: " + e);
        }
        return lines;
    }
}
