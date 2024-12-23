package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> nullFields = new ArrayList<>();;
        try {
            var fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);
                    var value = field.get(address);
                    if(Objects.isNull(value)) {
                        nullFields.add(field.getName());
                    }
                }
            }
        } catch (SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return nullFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> report = new HashMap<>();
        try {
            var fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                List<String> errors = new ArrayList<>();
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)){
                    var value = field.get(address);
                    if(Objects.isNull(value)) {
                        errors.add("can not be null");
                    }

                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength MinLengthInfo = field.getAnnotation(MinLength.class);
                    int minLength = MinLengthInfo.minLength();
                    var value = field.get(address);
                    if(value.toString().length() < minLength) {
                        errors.add("length less than " + minLength  );
                    }

                }
                if(!errors.isEmpty()) {
                    report.put(field.getName(), errors);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return report;
    }
}
// END