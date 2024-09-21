package exercise;

import java.util.Map;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> initialMap = storage.toMap();
        for (Entry<String, String> entry: initialMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            storage.set(value, key);
            storage.unset(key);
        }
    }
}
// END