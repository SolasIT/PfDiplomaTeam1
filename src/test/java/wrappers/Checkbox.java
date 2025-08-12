package wrappers;

import static com.codeborne.selenide.Selenide.$x;

public class Checkbox {

    String label; // Текст у чекбокса

    public Checkbox(String label) {
        this.label = label;
    }

    public void activateCheckbox() {
        $x(String.format("//div[contains(text(),'%s')]/input", label)).click();
    }
}