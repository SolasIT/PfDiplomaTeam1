package tests;

import org.testng.annotations.Test;

public class ReadAllTest extends BaseTest {

    @Test(description = "Проверка сортровки кнопки ID по возростанию")
    public void sortIdUP() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .log.info(Open Read all);
    }
}
