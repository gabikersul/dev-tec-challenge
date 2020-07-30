package DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileInDAO {
    public List<Path> readFolder() {
        List<Path> paths = new ArrayList<>();
        try {
            paths = Files.walk(Paths.get("./data/in"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    public List<String> readAllLines(Path path) {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(String.format("./data/in/%s", path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
}
