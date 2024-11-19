package exercise.dto.articles;

import io.javalin.validation.ValidationError;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildArticlePage {
    private String title;
    private String content;
    private Map<String, List<ValidationError<Object>>> errors;
}