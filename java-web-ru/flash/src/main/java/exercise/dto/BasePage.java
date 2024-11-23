package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private String flash;
    private String flashType;

    public BasePage(String flash, String flashType) {
        this.flash = flash;
        this.flashType = flashType;
    }
}
