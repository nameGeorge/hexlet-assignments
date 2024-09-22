package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        String json = car.serialize();
        try {
            Files.write(path, json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) throws Exception {
        try {
            String json = Files.readString(path);
            return Car.unserialize(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
