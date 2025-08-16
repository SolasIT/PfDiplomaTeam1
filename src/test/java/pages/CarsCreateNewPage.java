package pages;

import com.codeborne.selenide.Selenide;
import dto.ui.Car;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class CarsCreateNewPage extends BasePage {
    private final By PUSH_TO_API_BUTTON = withText("PUSH");
    private final By INPUT_ENGINE_TYPE = byId("car_engine_type_send");
    private final By INPUT_MARK = byId("car_mark_send");
    private final By INPUT_MODEL = byId("car_model_send");
    private final By INPUT_PRICE = byId("car_price_send");
    private final By STATUS_MESSAGE = byCssSelector(".status");
    private final By NEW_ID = byCssSelector(".newId");

    @Override
    @Step("Открытие страницы Cars Create New")
    public CarsCreateNewPage open() {
        try {
            log.info("Opening Create New Car page");
            Selenide.open(BASE_URL + "#/create/cars");
            return this;
        } catch (Exception e) {
            log.error("Failed to open page: {}", e.getMessage());
            Assert.fail("Failed to open page: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Step("Проверка открытия Cars Create New")
    public CarsCreateNewPage isPageOpened() {
        try {
            $(PUSH_TO_API_BUTTON).shouldBe(visible, Duration.ofSeconds(10));
            log.info("CarsCreateNewPage is opened successfully");
            return this;
        } catch (Exception e) {
            log.error("Page not opened: {}", e.getMessage());
            Assert.fail("Page not opened: " + e.getMessage());
            return null;
        }
    }

    @Step("Создание карточки машины с данными: {car}")
    public CarsCreateNewPage createCar(Car car) {
        try {
            log.info("Starting car creation process");
            fillCarData(car);
            submitForm();
            processResponse(car);
            return this;
        } catch (Exception e) {
            log.error("Error during car creation: {}", e.getMessage());
            Assert.fail("Error during car creation: " + e.getMessage());
            return null;
        }
    }

    private void fillCarData(Car car) {
        if (car.getEngineType() != null) {
            $(INPUT_ENGINE_TYPE).setValue(car.getEngineType());
            log.info("Entered Engine Type: {}", car.getEngineType());
        }
        if (car.getMark() != null) {
            $(INPUT_MARK).setValue(car.getMark());
            log.info("Entered Mark: {}", car.getMark());
        }
        if (car.getModel() != null) {
            $(INPUT_MODEL).setValue(car.getModel());
            log.info("Entered Model: {}", car.getModel());
        }
        if (car.getPrice() != 0) {
            $(INPUT_PRICE).setValue(String.valueOf(car.getPrice()));
            log.info("Entered Price: {}", car.getPrice());
        }
    }

    private void submitForm() {
        $(PUSH_TO_API_BUTTON).click();
        log.info("Clicked PUSH button");
        $(STATUS_MESSAGE).shouldBe(visible, Duration.ofSeconds(5));
    }

    private void processResponse(Car car) {
        String status = $(STATUS_MESSAGE).getText();
        car.setStatus(status);
        log.info("Received status: {}", status);

        if (status.equals("Status: Successfully pushed, code: 201")) {
            String idText = $(NEW_ID).shouldBe(visible).getText();
            int id = Integer.parseInt(idText.replace("New car ID: ", ""));
            car.setId(id);
            log.info("New Car ID: {}", id);
        }
    }
}