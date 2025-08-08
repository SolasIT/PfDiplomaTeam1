package wrappers;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class Checkbox {

    String label; // Текст у чекбокса

    public Checkbox(String label) {
        this.label = label;
    }

    public void activateCheckbox() {
        $(withText(String.format("%s", label))).ancestor("div").find("input").click();
    }
}
