package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private final String name;
    private Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (attributes.isEmpty()) {
            return "<" + name + ">";
        }
        String mapAsString = attributes.keySet().stream()
                .map(key -> key + "=\"" + attributes.get(key) + "\"")
                .collect(Collectors.joining(" ", " ", ""));
        return "<" + name + mapAsString + ">";
    }
}
// END
