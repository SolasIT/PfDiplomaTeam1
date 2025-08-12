package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SettleOrEvictUserTest extends BaseTest{
    @Test(description = "Проверка нажатия Push To API без ввода данных",
            testName = "Проверка нажатия Push To API без ввода данных")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка нажатия Push To API без ввода данных")
    public void checkPushButton(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
settleOrEvictUserPage.openPage()
        .clickPushButton()
        .getStatusText();
    assertEquals("Status: Incorrect input data", settleOrEvictUserPage.getStatusText());
    }

    @Test(description = "Проверка нажатия Push To API с заполнением UserID",
            testName = "Проверка нажатия Push To API с заполнением UserID")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка нажатия Push To API с заполнением UserID")
    public void checkInputOnlyUserId(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        settleOrEvictUserPage.openPage()
                .increaseUserIdNumber()
                .clickPushButton()
                .getStatusText();
        assertEquals("Status: Incorrect input data", settleOrEvictUserPage.getStatusText());
    }

    @Test(description = "Проверка нажатия Push To API с заполнением HouseID",
            testName = "Проверка нажатия Push To API с заполнением HouseID")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка нажатия Push To API с заполнением HouseID")
    public void checkInputOnlyHouseId(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        settleOrEvictUserPage.openPage()
                .increaseHouseIdNumber()
                .clickPushButton()
                .getStatusText();
        assertEquals("Status: Incorrect input data", settleOrEvictUserPage.getStatusText());
    }

    @Test(description = "Проверка нажатия Push To API с заполнением UserId и HouseID",
            testName = "Проверка нажатия Push To API с заполнением UserId и HouseID")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка нажатия Push To API с заполнением UserId и HouseID")
    public void checkInputUserIdAndHouseId(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        settleOrEvictUserPage.openPage()
                .increaseHouseIdNumber()
                .increaseHouseIdNumber()
                .clickPushButton()
                .getStatusText();
        assertEquals("Status: Incorrect input data", settleOrEvictUserPage.getStatusText());
    }

    @Test(description = "Проверка успешного заселения",
            testName = "Проверка успешного заселения")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка успешного заселения")
    public void checkSuccessfulSettle(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        settleOrEvictUserPage.openPage()
                .sentUserIdNumber("101")
                .sentHouseIdNumber("101")
                .setSettleOption()
                .clickPushButton()
                .getStatusText();
        assertEquals("Status: Successfully pushed, code: 200", settleOrEvictUserPage.getStatusText());
    }

    @Test(description = "Проверка успешного выселения",
            testName = "Проверка успешного выселения")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle Or Evict")
    @Description("Проверка успешного выселения")
    public void checkSuccessfulEvict(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        settleOrEvictUserPage.openPage()
                .sentUserIdNumber("101")
                .sentHouseIdNumber("101")
                .setEvictOption()
                .clickPushButton()
                .getStatusText();
        assertEquals("Status: Successfully pushed, code: 200", settleOrEvictUserPage.getStatusText());
    }
}