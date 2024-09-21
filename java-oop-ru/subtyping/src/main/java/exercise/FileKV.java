package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String path;
    private String json;

    public FileKV(String path, HashMap<String, String> initialMap) {
        this.path = path;
        this.json = Utils.serialize(initialMap);
        Utils.writeFile(path, json);
    }

    @Override
    public void set(String key, String value) {
        this.json = Utils.readFile(path);
        Map<String, String> current = toMap();
        current.put(key, value);
        this.json = Utils.serialize(current);
        Utils.writeFile(path, json);
    }

    @Override
    public void unset(String key) {
        this.json = Utils.readFile(path);
        Map<String, String> current = toMap();
        current.remove(key);
        this.json = Utils.serialize(current);
        Utils.writeFile(path, json);
    }

    @Override
    public String get(String key, String defaultValue) {
        this.json = Utils.readFile(path);
        Map<String, String> current = toMap();
        return current.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(this.json);
    }
}
// END