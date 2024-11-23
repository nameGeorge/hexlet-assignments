package exercise.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private String flash;
    private String flashType;

    public BasePage() {}

    public BasePage(String flash, String flashType) {
        this.flash = flash;
        this.flashType = flashType;
    }
}
