package pages;

public abstract class BasePage {
    protected final String BASE_URL = "http://82.142.167.37:4881/";

    public BasePage() {
    }

    public abstract BasePage open();
    public abstract BasePage isPageOpened();
}